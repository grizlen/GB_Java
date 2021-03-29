package ru.geekbrains.chat3.server;

import ru.geekbrains.chat3.service.UserService;
import ru.geekbrains.chat3.transport.ChatSocket;

import java.net.ServerSocket;

public class Server {
  private UserService userService;

  public Server(int port) {
    try {
      userService = new UserService();
      ServerSocket socket = new ServerSocket(port);
      System.out.println("Server: started");
      while (true) {
        System.out.println("Server: wait connection...");
        ChatSocket clientSoket = new ChatSocket(socket);
        System.out.println("Server: client connected " + clientSoket);
        new ClientHandler(this, clientSoket);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      userService.close();
    }
  }

  public UserService getUserService() {
    return userService;
  }
}
