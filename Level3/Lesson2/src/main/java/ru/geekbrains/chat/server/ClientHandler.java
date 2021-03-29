package ru.geekbrains.chat.server;

import ru.geekbrains.chat.server.auth.AuthEntry;
import ru.geekbrains.chat.transport.ChatMessage;
import ru.geekbrains.chat.transport.ChatSocket;

import java.io.IOException;

public class ClientHandler {
  private static final long AUTH_TIME = 120000;

  private final ChatServer server;
  private final ChatSocket socket;
  private AuthEntry authEntry;
  private boolean isStarted = true;

  public AuthEntry getAuthEntry() {
    return authEntry;
  }

  public String getNick(){
    return authEntry == null ? "": authEntry.getNick();
  }

  public ClientHandler(ChatServer server, ChatSocket socket) {
    this.server = server;
    this.socket = socket;
    try {
      new Thread(()->{
        try {
          Thread.sleep(AUTH_TIME);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (authEntry == null) {
          sendMessage("Time out.");
          close();
        }
      }).start();
      doAuth();
      new Thread(()->listen()).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void doAuth() throws IOException {
    while (isStarted) {
      sendMessage("type \"-auth login password\" or \"-reg login password nick\"");
      ChatMessage message = socket.readMessage();
      if (!isStarted) {return;}
      switch (message.getCmd()){
        case ChatMessage.AUTH:
          if ((authEntry = server.getAuthService().findEntry(message.getParam(1), message.getParam(2))) == null) {
            sendMessage("Invalid login/password");
          }
          break;
        case ChatMessage.REG:
          if ((authEntry = server.getAuthService().regEntry(message.getParam(1), message.getParam(2), message.getParam(3))) == null) {
            sendMessage("login or nickname is already used.");
          }
          break;
        default:
          sendMessage("Invalid command.");
      }
      if (authEntry != null && server.subscribe(this)){
        sendMessage("login ok");
        return;
      }
    }
  }

  private void listen(){
    try {
      while (isStarted){
        ChatMessage message = socket.readMessage();
        switch (message.getCmd()){
          case ChatMessage.MESSAGE:
            server.broadcastMessage(getNick() + ": " + message.getParam(0));
            break;
          case ChatMessage.NICK:
            server.changeNick(this, message.getParam(1));
            break;
          case ChatMessage.PRIVATE:
            if (!server.privateMessage(message.getParam(1), getNick() + ": " + message.getParam(2))){
              sendMessage(message.getParam(1) + " not found");
            }
            break;
          case ChatMessage.EXIT:
            server.unSubscribe(this);
            close();
            return;
          default: sendMessage("invalid command: " + message.getParam(0));
        }
      }
    } catch (IOException e) {
      server.unSubscribe(this);
      isStarted = false;
    }
  }
  public void sendMessage(String msg){
    try {
      socket.writeUTF(msg);
    } catch (IOException e) {
      throw new RuntimeException("ClientHandler.sendMessage", e);
    }
  }
  private void close(){
    isStarted = false;
    sendMessage("-close");
  }
}
