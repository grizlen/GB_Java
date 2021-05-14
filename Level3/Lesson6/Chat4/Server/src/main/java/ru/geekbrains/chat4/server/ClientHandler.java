package ru.geekbrains.chat4.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.chat4.services.UserEntry;
import ru.geekbrains.chat4.services.UserService;
import ru.geekbrains.chat4.transport.ChatMessage;
import ru.geekbrains.chat4.transport.ChatSocket;

import java.io.IOException;
import java.net.ServerSocket;

public class ClientHandler implements Runnable{
  private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);

  private final ChatSocket socket;
  private final UserService userService;
  private boolean isStarted = true;
  private UserEntry userEntry;


  public ClientHandler(ServerSocket serverSocket, UserService userService) throws IOException {
    socket = new ChatSocket(serverSocket);
    this.userService = userService;
  }

  @Override
  public void run() {
    LOGGER.info("run " + socket);
    while (isStarted){
      if (userEntry == null){
        doAuth();
      } else {
        doWork();
      }
    }
  }

  private void doAuth() {
    try {
      ChatMessage message = socket.readMessage();
      switch (message.getCmd()){
        case ChatMessage.AUTH:
          userEntry = userService.subscribe(this, message.getParam(1), message.getParam(2));
          break;
        case ChatMessage.REG:
          userEntry = userService.registerUser(this, message.getParam(1), message.getParam(2), message.getParam(3));
          break;
        default: sendInfo("Invalid command: " + message.getText());
      }
      if (userEntry != null) {
        sendInfo("Login OK");
        return;
      }
    } catch (IOException e) {
      LOGGER.error(e);
      stop();
    }
  }

  private void doWork() {
    try {
      ChatMessage message = socket.readMessage();
      switch (message.getCmd()){
        case ChatMessage.MESSAGE:
          userService.broadcastMessage(getNick() + ": " + message.getParam(0));
          break;
        case ChatMessage.PRIVATE:
          userService.privateMessage(this, message.getParam(1), message.getParam(2));
          break;
        case ChatMessage.NICK:
          userService.changeNick(this, message.getParam(1));
          break;
        case ChatMessage.EXIT:
          send("-q");
          stop();
          break;
        default: sendInfo("Invalid command: " + message.getText());
      }
    } catch (IOException e) {
      LOGGER.error(e);
      stop();
    }
  }

  public void send(String message) {
    try {
      socket.writeUTF(message);
    } catch (IOException e) {
      LOGGER.error(e);
      stop();
    }
  }

  public void sendInfo(String message) {
    send("-i " + message);
  }

  private void stop() {
    userService.unSubscribe(this);
    isStarted = false;
  }

  public String getNick(){
    return userEntry == null ? "" : userEntry.getNick();
  }

  public UserEntry getUserEntry() {
    return userEntry;
  }
}
