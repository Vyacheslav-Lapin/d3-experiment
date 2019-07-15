init:
	touch .git/info/exclude

#	maven-wrapper
	mvn -N io.takari:maven:wrapper -Dmaven=3.6.1
	rm mvnw.cmd
	chmod +x ./mvnw
	echo "/.mvn" >> ./.git/info/exclude
	echo "/mvnw*" >> ./.git/info/exclude

#	checkstyler
	curl -O https://raw.githubusercontent.com/checkstyle/checkstyle/master/src/main/resources/google_checks.xml
	echo "/google_checks.xml" >> ./.git/info/exclude

# jenv
	jenv local `cat pom.xml | xml sel -N pom=http://maven.apache.org/POM/4.0.0 -t -v /pom:project/pom:properties/pom:java.version`

uninit:
	rm -rf .git .mvn mvnw .idea d3-experiment.iml google_checks.xml .java-version

build:
	./mvnw verify
	chmod +x ./target/d3-experiment-0.0.1-SNAPSHOT.jar

run:
	./mvnw spring-boot:run -Dspring.profiles.active=local
#	./target/d3-experiment-0.0.1-SNAPSHOT.jar
#	java -jar --enable-preview ./target/d3-experiment-0.0.1-SNAPSHOT.jar

jshell: build
	jshell --enable-preview --start PRINTING --start JAVASE --class-path `mvn dependency:build-classpath | grep -A1 'Dependencies classpath' | tail -1`:./target/classes:./target/testClasses

archetype: clear uninit
	./mvnw archetype:create-from-project -Darchetype.properties=archetype.properties
#	cd target/generated-sources/archetype/.idea && rm workspace.xml usage.statistics.xml tasks.xml
	make init
#	idea ./target/generated-sources/archetype/pom.xml
	cd target/generated-sources/archetype && ./../../../mvnw clean install

clone: archetype
	cd .. && mvn archetype:generate \
		-DarchetypeGroupId=ru.vlapin.projects \
		-DarchetypeArtifactId=monolith-archetype \
		-DartifactId=monolith-example \
		-DarchetypeVersion=0.0.1-SNAPSHOT \
		-DgroupId=ru.vlapin.projects \
		-Dpackage=ru.vlapin.projects.monolith \
		-Dversion=0.0.1-SNAPSHOT \
		-DinteractiveMode=false

	idea ./../monolith-example/pom.xml

test:
	./mvnw test

update:
	./mvnw versions:update-parent versions:update-properties versions:display-plugin-updates

delombok:
	./mvnw clean
	ln -s ./java ./src/main/lombok
	ln -s ./java ./src/test/lombok
	mkdir -p ./target/generated-sources/delombok ./target/generated-test-sources/delombok
	./mvnw lombok:delombok lombok:testDelombok
	rm ./src/main/lombok ./src/test/lombok

clear:
	./mvnw clean
	touch ./../monolith-example && rm -r ./../monolith-example


.DEFAULT_GOAL := build-run
build-run: update build run
