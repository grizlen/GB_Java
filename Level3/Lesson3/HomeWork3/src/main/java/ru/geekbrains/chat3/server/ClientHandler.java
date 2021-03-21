package ru.geekbrains.chat3.server;

import ru.geekbrains.chat3.service.UserEntry;
import ru.geekbrains.chat3.service.UserService;
import ru.geekbrains.chat3.transport.ChatMessage;
import ru.geekbrains.chat3.transport.ChatSocket;

import java.io.IOException;

public class ClientHandler {
  private final Server server;
  private final ChatSocket socket;
  private final UserService userService;
  private UserEntry userEntry;
  private boolean isStarted = true;

  public ClientHandler(Server server, ChatSocket socket) throws IOException {
    this.server = server;
    this.socket = socket;
    this.userService = server.getUserService();
//    checkTimeOut(30000);
    new Thread(this::listen).start();
  }

  private void checkTimeOut(long t) {
    new Thread(() -> {
      try {
        Thread.sleep(t);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (userEntry == null) {
        sendMessage("Time out.");
        close();
      }
    }).start();
  }

  private void listen() {
    while (isStarted){
      try {
        if (userEntry == null) {
          doAuth();
        } else {
          doWork();
        }
      } catch (Exception e) {
        e.printStackTrace();
        isStarted = false;
      }
    }
  }

  private void doAuth() {
    try {
      while (isStarted) {
        ChatMessage message = socket.readMessage();
        switch (message.getCmd()){
          case ChatMessage.AUTH:
            userEntry = userService.subscribe(this, message.getParam(1), message.getParam(2));
            break;
          case ChatMessage.REG:
            userEntry = userService.regiserUser(this, message.getParam(1), message.getParam(2), message.getParam(3));
            if (userEntry == null) {
              sendMessage("This login or nickname already used");
            }
            break;
          default: sendMessage("invalid command: " + message.getParam(0));
        }
        if (userEntry != null) {
          sendMessage("Login OK");
          return;
        }
      }
    } catch (IOException e) {
      isStarted = false;
    }
  }

  private void doWork() {
    try {
      while (isStarted) {
        ChatMessage message = socket.readMessage();
        switch (message.getCmd()){
          case ChatMessage.MESSAGE:
            userService.broadcastMessage(getNick() +": " + message.getParam(0));
            break;
          case ChatMessage.PRIVATE:
            userService.privateMessage(message.getParam(1), getNick() +": " + message.getParam(2));
            break;
          case ChatMessage.NICK:
            if (!userService.nickChange(userEntry, message.getParam(1))){
              sendMessage(message.getParam(1) + " used.");
            }
            break;
          case ChatMessage.EXIT:
            userService.unSubscribe(this);
            close();
            return;
          default: sendMessage("invalid command: " + message.getParam(0));
        }
      }
    } catch (IOException e) {
      userService.unSubscribe(this);
      isStarted = false;
    }

  }

  private void close() {
    this.isStarted = false;
    sendMessage("-close");
  }

  public void sendMessage(String message) {
    try {
      socket.writeUTF(message);
    } catch (IOException e) {
      throw new RuntimeException("ClientHandler.sendMessage", e);
    }
  }

  public UserEntry getUserEntry() {
    return userEntry;
  }

  public String getNick(){
    return userEntry == null ? "" : userEntry.getNick();
  }
}
