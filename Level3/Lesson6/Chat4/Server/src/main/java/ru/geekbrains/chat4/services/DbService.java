package ru.geekbrains.chat4.services;

import java.sql.*;

public class DbService {
  private Connection connection;

  public DbService(String url) throws ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    try {
      connection = DriverManager.getConnection(url);
      try (Statement statement = connection.createStatement()) {
        statement.execute("CREATE TABLE if not exists 'entries' (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "login text NOT NULL UNIQUE," +
            "password text NOT NULL," +
            "nick text NOT NULL UNIQUE);");
      } catch (SQLException e) {
        throw new RuntimeException("Init db fail.", e);
      }
    } catch (Exception e) {
      throw new RuntimeException("database connection fail.", e);
    }
  }

  public void close() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public boolean addEntry(String login, String password, String nick) {
    String query = String.format("INSERT INTO entries (login, password, nick) VALUES('%s', '%s', '%s');", login, password, nick);
    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate(query) == 1;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public String getNick(String login, String password){
    String query = String.format("SELECT DISTINCT nick FROM entries WHERE (login = '%s') AND (password = '%s');", login, password);
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      return rs.next() ? rs.getString(1) : null;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public boolean setNick(String login, String nick){
    String query = String.format("UPDATE entries SET nick = '%s' WHERE login = '%s'", nick ,login);
    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate(query) == 1;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
}
