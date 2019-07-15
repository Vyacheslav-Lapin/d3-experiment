package ru.vlapin.experiments.d3experiment;

import java.sql.Connection;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@AllArgsConstructor
public class PooledConnection implements Connection {

  @Delegate(excludes = AutoCloseable.class)
  Connection connection;


  @Override
  public void close() throws SQLException {
    //jkhsdkjhfsg
  }
}
