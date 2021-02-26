package ru.geekbrains.chat.server;

import ru.geekbrains.chat.auth.AuthEntry;
import ru.geekbrains.chat.auth.AuthServise;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
  private final AuthServise authServise = new AuthServise();
  private final Map<AuthEntry, ClientHandler> clientHandlers = new HashMap<>();
  private ServerSocket socket;

  public static void main(String[] args) {
    new Server();
  }
  public Server() {
    try {
      socket = new ServerSocket(8989);
      start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public AuthServise getAuthServise() {
    return authServise;
  }

  private void start() throws IOException {
    while (true){
      System.out.println("SERVER: wait connection");
      Socket client = socket.accept();
      System.out.println("SERVER: client connected " + client);
      new ClientHandler(this, client);
    }
  }
  public boolean subscribe(ClientHandler client){
    if (clientHandlers.get(client.getAuthEntry()) == null){
      broadcastMessage(client.getNick() + " logged in.");
      clientHandlers.put(client.getAuthEntry(), client);
      return true;
    }
    client.sendMessage(client.getNick() + " already logged");
    return false;
  }
  public void broadcastMessage(String message){
    for (ClientHandler clientHandler: clientHandlers.values()) {
      clientHandler.sendMessage(message);
    }
  }
  public boolean privateMessage(String nick, String message){
    AuthEntry entry = authServise.findEntry(nick);
    if (entry == null){return false;}
    ClientHandler handler = clientHandlers.get(entry);
    if (handler != null){
      handler.sendMessage(message);
      return true;
    }
    return false;
  }
}
