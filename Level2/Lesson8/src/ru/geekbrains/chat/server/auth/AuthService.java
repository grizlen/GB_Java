package ru.geekbrains.chat.server.auth;

import java.util.Set;

public class AuthService {
  private static final Set<AuthEntry> entries = Set.of(
      new AuthEntry("l1", "p1", "nick1"),
      new AuthEntry("l2", "p2", "nick2"),
      new AuthEntry("l3", "p3", "nick3")
  );

  public AuthEntry findEntry(String nick){
    for (AuthEntry entry: entries){
      if (entry.getNick().equals(nick)){
        return entry;
      }
    }
    return null;
  }

  public AuthEntry findEntry(String login, String password){
    for (AuthEntry entry: entries){
      if (entry.checkLogin(login, password)){
        return entry;
      }
    }
    return null;
  }
}
