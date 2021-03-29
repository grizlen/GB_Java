package ru.geekbrains.chat.server.auth;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import ru.geekbrains.chat.server.ClientHandler;

public class AuthService {
  private final Map<AuthEntry, ClientHandler> loggedClientHandlers = new ConcurrentHashMap<>();

  private final AuthDB authDB;

  public AuthService() {
    try {
      authDB = new AuthDB();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Create AuthService fail.", e);
    }
  }

  public Collection<ClientHandler> getLoggedClientHandlers() {
    return loggedClientHandlers.values();
  }

  public boolean isClientLogged(AuthEntry authEntry){
    return loggedClientHandlers.get(authEntry) != null;
  }

  public void putClientLogged(ClientHandler clientHandler){
    loggedClientHandlers.put(clientHandler.getAuthEntry(), clientHandler);
  }

  public void removeClientLogged(ClientHandler clientHandler){
    loggedClientHandlers.remove(clientHandler.getAuthEntry());
  }

  public ClientHandler getClientLoggedByNick(String nick){
    for (ClientHandler client: loggedClientHandlers.values()){
      if (client.getNick().equals(nick)){
        return client;
      }
    }
    return null;
  }

  public AuthEntry findEntry(String login, String password){
    return authDB.find(login, password);
  }

  public AuthEntry regEntry(String login, String password, String nick){
    return authDB.append(login, password, nick);
  }

  public boolean changeNick(AuthEntry authEntry, String newNick){
    if (authDB.save(authEntry.getLogin(), authEntry.getPassword(), newNick)){
      authEntry.setNick(newNick);
      return true;
    }
    return false;
  }
}
