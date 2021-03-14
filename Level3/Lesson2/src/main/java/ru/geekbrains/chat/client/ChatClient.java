package ru.geekbrains.chat.client;

import ru.geekbrains.chat.transport.ChatSocket;

import java.io.IOException;

public class ChatClient {

  private ChatSocket socket;

  public ChatClient(String host, int port) {
    try {
      socket = new ChatSocket(host, port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void start(ClientEventListener eventListener){
    while (true){
      try {
        eventListener.onReceive(socket.readUTF());
      } catch (IOException e) {
        throw new RuntimeException("ChatClient.receiveMessage", e);
      }
    }
  }

  public void sendMessage(String message) {
    try {
      socket.writeUTF(message);
    } catch (IOException e) {
      throw new RuntimeException("ChatClient.sendMessage", e);
    }
  }
}
