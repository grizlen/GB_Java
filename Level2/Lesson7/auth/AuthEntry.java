package ru.geekbrains.chat.auth;

import java.util.Objects;
import java.util.SplittableRandom;

public class AuthEntry {
  private String login, password, nick;

  public AuthEntry(String login, String password, String nick) {
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
