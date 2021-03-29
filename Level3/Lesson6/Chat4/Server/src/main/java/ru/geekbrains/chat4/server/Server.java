package ru.geekbrains.chat4.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.chat4.services.UserService;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
  private static final int PORT = 8989;
  private static final Logger LOGGER = LogManager.getLogger(Server.class);

  private UserService userService;

  public static void main(String[] args) {
    new Server();
  }

  public Server() {
    ExecutorService clients = Executors.newCachedThreadPool();
    try (ServerSocket socket = new ServerSocket(PORT)){
      userService = new UserService();
      LOGGER.info("server started.");
      while (!socket.isClosed()){
        ClientHandler clientHandler = new ClientHandler(socket, userService);
        clients.execute(clientHandler);
      }
      clients.shutdown();
    } catch (Exception e) {
      LOGGER.fatal(e);
    } finally {
      clients.shutdown();
      userService.close();
    }
  }
}
