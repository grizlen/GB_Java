package ru.geekbrains.chat.server.auth;

import java.util.Objects;

public class AuthEntry {
  private final String login, password;
  private String nick;

  public AuthEntry(String login, String password, String nick) {
    this.login = login;
    this.password = password;
    this.nick = nick;
  }

  public String getNick() {
    return nick;
  }

  public boolean checkLogin(String login, String password){
    return this.login.equals(login) && this.password.equals(password);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuthEntry authEntry = (AuthEntry) o;
    return Objects.equals(login, authEntry.login) && Objects.equals(password, authEntry.password) && Objects.equals(nick, authEntry.nick);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, password, nick);
  }
}
