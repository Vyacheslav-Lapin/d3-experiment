# @author Vyacheslav Lapin aka "C'est la vie". 2019 (c) http://vlapin.ru
# This Makefile is writen as command-line Project API for Java Maven multi-module
# projects with Lombok annotation processor. Required software list:
# - Maven, Git, JDK
# - JEnv - for *nix/Mac only!
# - XMLStarlet Toolkit
# - XsltProc

# For Windows users:
# - Comment or delete the JEnv block in "init" task
# - Delete "./" substring in some commands, like "./mvnw verify" - it should looks like "mvnw verify"
# - Replace "ln" with "mklink" command (see https://stackoverflow.com/questions/17246558/what-is-the-windows-equivalent-to-the-ln-s-target-folder-link-folder-unix-s)

#--------------------------
#Variables:

# Project GroupId
PG=`cat pom.xml | xml sel -N pom=http://maven.apache.org/POM/4.0.0 -t -v /pom:project/pom:groupId`

# Main package
MP=`cat pom.xml | xml sel -N pom=http://maven.apache.org/POM/4.0.0 -t -v /pom:project/pom:groupId | tr '.' '/'`

# Project Artifact id
PA=`cat pom.xml | xml sel -N pom=http://maven.apache.org/POM/4.0.0 -t -v /pom:project/pom:artifactId`

# Java Version
JV=`cat pom.xml | xml sel -N pom=http://maven.apache.org/POM/4.0.0 -t -v /pom:project/pom:properties/pom:java.version`

# Maven Version
MV=3.6.3

# Lombok Version
LV=`cat pom.xml | xml sel -N pom=http://maven.apache.org/POM/4.0.0 -t -v /pom:project/pom:properties/pom:lombok.version`

#--------------------------
#Tasks:

init:
	git init
	touch .git/info/exclude

#	maven-wrapper
	mvn -N io.takari:maven:wrapper -Dmaven=3.6.3
	rm mvnw.cmd
	chmod +x ./mvnw
	echo "\n/.mvn\n/mvnw*\n" >> .git/info/exclude

#	jenv
	jenv local openjdk64-`cat pom.xml | xml sel -N pom=http://maven.apache.org/POM/4.0.0 -t -v /pom:project/pom:properties/pom:java.version`
	echo "\n/.java-version\n" >> .git/info/exclude

#	checkstyler
	curl -O https://raw.githubusercontent.com/checkstyle/checkstyle/master/src/main/resources/google_checks.xml
	echo "\n/google_checks.xml\n" >> .git/info/exclude

uninit:
	rm -rf .mvn mvnw* google_checks.xml .git/info/exclude

reboot: clear uninit init

uninit-full: clear uninit
	rm -rf .idea d3-experiment.iml .git

reboot-full: uninit-full init
	echo "\n/.idea/\n/d3-experiment.iml\n/out/\n/classes/\n" >> .git/info/exclude
	git add src .editorconfig .gitignore Makefile pom.xml README.md
	idea pom.xml

jshell:
	jshell --enable-preview --start PRINTING --start JAVASE --class-path `mvn dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1`

build:
	./mvnw verify
	chmod +x ./target/d3-experiment-0.0.1-SNAPSHOT.jar

run:
	./mvnw spring-boot:start -Dspring.profiles.active=local
#	./target/d3-experiment-0.0.1-SNAPSHOT.jar
#	java -jar --enable-preview ./target/d3-experiment-0.0.1-SNAPSHOT-jar-with-dependencies.jar

effective-pom:
	./mvnw help:effective-pom

clear:
	./mvnw clean

test: clear
	./mvnw test

update:
	./mvnw versions:update-parent versions:update-properties versions:display-plugin-updates

delombok: clear
#	./mvnw lombok:delombok
	mkdir -p ./target/generated-sources/delombok
	java -cp `./mvnw dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1` \
		lombok.launch.Main delombok ./src/main/java \
		-d ./target/generated-sources/delombok

test-delombok: delombok
	./mvnw lombok:testDelombok

git-fork-init: init
	git remote add upstream git://github.com/Vyacheslav-Lapin/d3-experiment.git
	git fetch upstream

#branch name
B=feature
git-branch:
	git checkout -b $(B)
	git push -u origin $(B)

.DEFAULT_GOAL := build-run
build-run: update build run
