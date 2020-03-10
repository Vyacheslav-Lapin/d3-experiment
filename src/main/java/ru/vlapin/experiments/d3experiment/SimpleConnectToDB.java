package ru.vlapin.experiments.d3experiment;

import java.sql.DriverManager;
import java.sql.ResultSet;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Slf4j
@UtilityClass
public final class SimpleConnectToDB {

  private final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

  //language=H2
  private final String CREATE_TABLE_SQL = "create table student (id identity, name varchar not null, groupId int)";//language=H2
  private final String INSERT_STUDENT_SQL = "insert into student (name, groupId) values ('Вася Пупкин', 123456)";//language=H2
  private final String GET_STUDENTS_SQL = "select id, name, groupId from student";

  @SneakyThrows
  public void main(String... __) {
    @Cleanup val connection = DriverManager.getConnection(DB_URL);
    @Cleanup val statement = connection.createStatement();
    statement.executeUpdate(CREATE_TABLE_SQL);
    statement.executeUpdate(INSERT_STUDENT_SQL);
    @Cleanup val resultSet = statement.executeQuery(GET_STUDENTS_SQL);
    while (resultSet.next())
      log.info(Student.from(resultSet).toString());
  }
}

@Value
@FieldNameConstants
class Student {
  int id;
  String name;
  int groupId;

  @NotNull
  @SneakyThrows
  @Contract("_ -> new")
  public static Student from(@NotNull ResultSet resultSet) {
    return new Student(
        resultSet.getInt(Fields.id),
        resultSet.getString(Fields.name),
        resultSet.getInt(Fields.groupId));
  }
}
