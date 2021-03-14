package ru.geekbrains.chat.server;

import ru.geekbrains.chat.server.auth.AuthService;
import ru.geekbrains.chat.transport.ChatSocket;

import java.net.ServerSocket;

public class ChatServer {
  public static void main(String[] args) {
    new ChatServer();
  }

  private AuthService authService;

  public AuthService getAuthService() {
    return authService;
  }

  public ChatServer() {
    try {
      authService = new AuthService();
      ServerSocket socket = new ServerSocket(8989);
      while (true) {
        System.out.println("Server: wait connection...");
        ChatSocket clientSocket = new ChatSocket(socket);
        System.out.println("Server: client connected " + clientSocket);
        new ClientHandler(this, clientSocket);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean subscribe(ClientHandler client){
    if (authService.isClientLogged(client.getAuthEntry())){
      client.sendMessage(client.getNick() + " already logged");
      return false;
    }
    System.out.println(client.getNick() + " logged in.");
    broadcastMessage(client.getNick() + " logged in.");
    authService.putClientLogged(client);
    return true;
  }

  public void unSubscribe(ClientHandler client){
    authService.removeClientLogged(client);
    System.out.println(client.getNick() + " logged out.");
    broadcastMessage(client.getNick() + " logged out.");
  }

  public void broadcastMessage(String message){
    authService.getLoggedClientHandlers().forEach(clientHandler -> clientHandler.sendMessage(message));
  }

  public boolean privateMessage(String nick, String message){
    ClientHandler clientHandler = authService.getClientLoggedByNick(nick);
    if (clientHandler == null){return false;}
    clientHandler.sendMessage(message);
    return true;
  }
  public void changeNick(ClientHandler client, String newNick){
    String oldNick = client.getAuthEntry().getNick();
    if (authService.changeNick(client.getAuthEntry(), newNick)){
      broadcastMessage(oldNick + " renamed to " + newNick);
    } else {
      client.sendMessage(newNick + " is already used.");
    }
  }
}
