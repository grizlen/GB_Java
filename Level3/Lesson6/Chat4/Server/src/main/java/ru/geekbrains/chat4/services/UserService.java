package ru.geekbrains.chat4.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.chat4.server.ClientHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
  private  final Logger LOGGER = LogManager.getLogger(UserService.class);
  private  final Map<UserEntry, ClientHandler> loggedUsers = new ConcurrentHashMap<>();
  private final DbService dbService;

  public UserService() throws ClassNotFoundException {
    dbService = new DbService("jdbc:sqlite:chat_db");
  }

  public void close() {
    dbService.close();
  }

  public UserEntry subscribe(ClientHandler clientHandler, String login, String password) {
    String nick = dbService.getNick(login, password);
    if (nick != null) {
      if (findLogged(nick) != null) {
        clientHandler.sendInfo(login + " already logged.");
        return null;
      }
      UserEntry entry = new UserEntry(login, password, nick);
      LOGGER.info(nick + " logged in.");
      broadcastInfo(nick + " logged in.");
      loggedUsers.put(entry, clientHandler);
      return entry;
    }
    clientHandler.sendInfo(login + "not found.");
    return null;
  }

  public void unSubscribe(ClientHandler clientHandler) {
    UserEntry entry = clientHandler.getUserEntry();
    if (entry != null) {
      loggedUsers.remove(clientHandler.getUserEntry());
      LOGGER.info(clientHandler.getNick() + " logged out.");
      broadcastInfo(clientHandler.getNick() + " logged out.");
    }
  }

  public UserEntry registerUser(ClientHandler clientHandler, String login, String password, String nick) {
    if (dbService.addEntry(login, password, nick)) {
      UserEntry entry = new UserEntry(login, password, nick);
      LOGGER.info("New user " + nick + " logged in.");
      broadcastInfo(nick + " logged in.");
      loggedUsers.put(entry, clientHandler);
      return entry;
    }
    clientHandler.sendInfo("This login or nickname already used");
    return null;
  }

  private ClientHandler findLogged(String nick) {
    for (ClientHandler value : loggedUsers.values()) {
      if (value.getNick().equals(nick)) { return value; }
    }
    return null;
  }

  public void broadcastMessage(String message) {
    LOGGER.info("Send: " + message);
    loggedUsers.values().forEach(handler -> handler.send(message));
  }

  public void broadcastInfo(String message) {
    loggedUsers.values().forEach(handler -> handler.send("-i " + message));
  }

  public void privateMessage(ClientHandler clientHandler, String nick, String message) {
    ClientHandler dest = findLogged(nick);
    if (dest != null) {
      LOGGER.info(String.format("Send from: %s to: %s %s" + clientHandler.getNick(), dest.getNick(), message));
      dest.send(clientHandler.getNick() + ": " + message);
      clientHandler.send(String.format("to %s: %s", dest.getNick(), message));
    }
    else {clientHandler.sendInfo(nick + " not found"); }
  }

  public void changeNick(ClientHandler clientHandler, String newNick) {
    String oldNick = clientHandler.getNick();
    if (dbService.setNick(clientHandler.getUserEntry().getLogin(), newNick)){
      clientHandler.getUserEntry().setNick(newNick);
      broadcastMessage(String.format("-i %s renamed to %s", oldNick, newNick));
    } else {clientHandler.sendInfo(newNick + " already used"); }
  }
}
