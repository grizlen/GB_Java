package ru.geekbrains.chat4.services;

import java.util.Objects;

public class UserEntry {
  private final String login;
  private String password, nick;

  public UserEntry(String login, String password, String nick) {
    this.login = login;
    this.password = password;
    this.nick = nick;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserEntry userEntry = (UserEntry) o;
    return Objects.equals(login, userEntry.login);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login);
  }
}
