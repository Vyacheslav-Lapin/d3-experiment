package ru.vlapin.experiments.d3experiment;

import java.sql.DriverManager;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

public final class SimpleConnectToDB {

  private static final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

  //language=H2
  private static final String CREATE_TABLE_SQL = "create table student (id identity, name varchar not null, group_id int)";//language=H2
  private static final String INSERT_STUDENT_SQL = "insert into student (name, group_id) values ('Вася Пупкин', 123456)";//language=H2
  private static final String GET_STUDENTS_SQL = "select id, name, group_id from student";

  private SimpleConnectToDB() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  @SneakyThrows
  public static void main(String... __) {
    @Cleanup val connection = DriverManager.getConnection(DB_URL);
    @Cleanup val statement = connection.createStatement();
    statement.executeUpdate(CREATE_TABLE_SQL);
    statement.executeUpdate(INSERT_STUDENT_SQL);
    @Cleanup val resultSet = statement.executeQuery(GET_STUDENTS_SQL);
    while (resultSet.next())
      System.out.printf("%d %s %d\n",
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getInt("group_id"));
  }
}
