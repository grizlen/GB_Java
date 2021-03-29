package ru.geekbrains.chat3.service;

import ru.geekbrains.chat3.server.ClientHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService{
  private final Map<UserEntry, ClientHandler> loggedUsers = new ConcurrentHashMap<>();
  private final DbService dbService;

  public UserService() throws ClassNotFoundException {
    dbService = new DbService("jdbc:sqlite:chat_db");
  }

  public void close(){
    dbService.close();
  }

  public void broadcastMessage(String message) {
    loggedUsers.values().forEach(u -> u.sendMessage(message));
  }

  public void privateMessage(String nick, String message) {
    ClientHandler handler = findLogged(nick);
    if (handler != null) {
      handler.sendMessage(message);
    }
  }

  public UserEntry regiserUser(ClientHandler clientHandler, String login, String password, String nick) {
    if (dbService.addEntry(login, password, nick)) {
      UserEntry entry = new UserEntry(login, password, nick);
      System.out.println(nick + " logged in.");
      broadcastMessage(nick + " logged in.");
      loggedUsers.put(entry, clientHandler);
      return entry;
    }
    return null;
  }

  public  UserEntry subscribe(ClientHandler clientHandler, String login, String password){
    String nick = dbService.getNick(login, password);
    if (nick != null) {
      if (findLogged(nick) != null) {
        clientHandler.sendMessage(login + " already logged.");
        return null;
      }
      UserEntry entry = new UserEntry(login, password, nick);
      System.out.println(login + " logged in.");
      broadcastMessage(login + " logged in.");
      loggedUsers.put(entry, clientHandler);
      return entry;
    }
    clientHandler.sendMessage(login + " not foud.");
    return null;
  }

  public void unSubscribe(ClientHandler clientHandler) {
    loggedUsers.remove(clientHandler.getUserEntry());
    System.out.println(clientHandler.getNick() + " logged out.");
    broadcastMessage(clientHandler.getNick() + " logged out.");
  }

  public boolean nickChange(UserEntry userEntry, String newNick) {
    if (dbService.setNick(userEntry.getLogin(), newNick)) {
      String nick = userEntry.getNick();
      userEntry.setNick(newNick);
      broadcastMessage(nick + " renamed to " + newNick);
      return true;
    }
    return false;
  }

  private ClientHandler findLogged(String nick){
    for (ClientHandler ch : loggedUsers.values()) {
      if (ch.getNick().equals(nick)) { return ch; }
    }
    return null;
  }
}
