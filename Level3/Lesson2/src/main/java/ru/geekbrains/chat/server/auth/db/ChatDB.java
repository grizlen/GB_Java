package ru.geekbrains.chat.server.auth.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDB {
  private Connection connection = null;

  public ChatDB(String url) throws ClassNotFoundException {
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

  public List<String[]> select(String fields, String condition){
    String query = "SELECT ";
    query += (fields.isBlank() ? "*" : fields) + " FROM entries";
    query += condition.isBlank() ? ";" : (" WHERE " + condition + ";");
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      if (rs.isClosed()) {return null;}
      int cols = rs.getMetaData().getColumnCount();
      List<String[]> result = new ArrayList<>();
      while (rs.next()){
        String[] line = new String[cols];
        for (int i = 0; i < cols; line[i] = rs.getString(++i));
        result.add(line);
      }
      return result;
    } catch (SQLException e) {
      throw new RuntimeException("Error in: " + query, e);
    }
  }

  public int uppend(String login, String password, String nick){
    String query = String.format("INSERT INTO entries (login, password, nick) VALUES('%s', '%s', '%s');", login, password, nick);
    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate(query);
    } catch (SQLException e) {
      throw new RuntimeException("Error in: " + query, e);
    }
  }

  public int update(String login, String password, String nick){
    String query = String.format("UPDATE entries SET password = '%s', nick = '%s' WHERE login = '%s';", password, nick, login);
    try (Statement statement = connection.createStatement()) {
      return statement.executeUpdate(query);
    } catch (SQLException e) {
      throw new RuntimeException("Error in: " + query, e);
    }
  }
}
