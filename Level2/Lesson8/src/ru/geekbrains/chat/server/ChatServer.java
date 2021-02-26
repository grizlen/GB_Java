package ru.geekbrains.chat.server;

import ru.geekbrains.chat.server.auth.AuthEntry;
import ru.geekbrains.chat.server.auth.AuthService;
import ru.geekbrains.chat.transport.ChatSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
  public static void main(String[] args) {
    new ChatServer();
  }

  private final AuthService authService = new AuthService();

  // Вопрос.
  // Применение ConcurrentHashMap решит проблему синхронизации subscribe unSubscribe ?
  private final Map<AuthEntry, ClientHandler> clientHandlers = new ConcurrentHashMap<>();

  public AuthService getAuthService() {
    return authService;
  }

  public ChatServer() {
    try {
      ServerSocket socket = new ServerSocket(8989);
      while (true) {
        System.out.println("Server: wait connection...");
        ChatSocket clientSocket = new ChatSocket(socket);
        System.out.println("Server: client connected " + clientSocket);
        new ClientHandler(this, clientSocket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //synchronized ???
  public boolean subscribe(ClientHandler client){
    if (clientHandlers.get(client.getAuthEntry()) == null){
      broadcastMessage(client.getNick() + " logged in.");
      clientHandlers.put(client.getAuthEntry(), client);
      return true;
    }
    client.sendMessage(client.getNick() + " already logged");
    return false;
  }

  //synchronized ???
  public void unSubscribe(ClientHandler client){
    System.out.println(clientHandlers.size());
    clientHandlers.remove(client.getAuthEntry());
    broadcastMessage(client.getNick() + " logged out.");
    System.out.println(clientHandlers.size());
  }

  public void broadcastMessage(String message){
    for (ClientHandler clientHandler: clientHandlers.values()) {
      clientHandler.sendMessage(message);
    }
  }

  public boolean privateMessage(String nick, String message){
    AuthEntry entry = authService.findEntry(nick);
    if (entry == null){return false;}
    ClientHandler handler = clientHandlers.get(entry);
    if (handler != null){
      handler.sendMessage(message);
      return true;
    }
    return false;
  }
}
