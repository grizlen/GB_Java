package ru.geekbrains.chat.server.auth;

import ru.geekbrains.chat.server.auth.db.ChatDB;

import java.util.List;

public class AuthDB {
  private final ChatDB chatDB;

  public AuthDB() throws ClassNotFoundException {
    chatDB = new ChatDB("jdbc:sqlite:chat_db");
  }

  public AuthEntry find(String login, String password){
    try {
      List<String[]> rows = chatDB.select("", String.format("(login = '%s') AND (password = '%s')", login, password));
      if (rows != null && rows.size() == 1){
        String[] line = rows.get(0);
        return new AuthEntry(line[1], line[2], line[3]);
      }
    } catch (RuntimeException e){
      e.printStackTrace();
    }
    return null;
  }

  public AuthEntry append(String login, String password, String nick){
    try {
      if (chatDB.uppend(login, password, nick) == 1){
        return new AuthEntry(login, password, nick);
      }
    } catch (RuntimeException e){
      e.printStackTrace();
    }
    return null;
  }

  public boolean save(String login, String password, String nick){
    try {
        return chatDB.update(login, password, nick) == 1;
    } catch (RuntimeException e){
      e.printStackTrace();
    }
    return false;
  }
}
