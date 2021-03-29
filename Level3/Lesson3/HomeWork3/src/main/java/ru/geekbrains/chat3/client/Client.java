package ru.geekbrains.chat3.client;

import ru.geekbrains.chat3.client.GUI.ChatFrame;
import ru.geekbrains.chat3.client.history.History;
import ru.geekbrains.chat3.transport.ChatSocket;

import java.io.IOException;

public class Client implements ClientEventListener{
  private ChatFrame chatFrame;
  private ChatSocket socket;
  private History history;

  public Client(String host, int port, String logfile) {
    try {
      chatFrame = new ChatFrame(this);
      socket = new ChatSocket(host, port);
      history = new History(logfile, chatFrame);
      start();
    } catch (Exception e) {
      chatFrame.append("-close");
    }
  }

  private void start() {
    while (true){
      try {
        onReceive(socket.readUTF());
      } catch (IOException e) {
        throw new RuntimeException("Client.receiveMessage", e);
      }
    }
  }

  private void sendMessage(String message) {
    try {
      socket.writeUTF(message);
    } catch (IOException e) {
      throw new RuntimeException("Client.sendMessage", e);
    }
  }

  @Override
  public void onReceive(String message) {
    if (message.equals("-q") | message.equals("-close")){
      history.close();
    } else {
      history.write(message);
    }
    chatFrame.append(message);
  }

  @Override
  public void doSubmit(String message) {
    try {
      sendMessage(message);
    } catch (Exception e) {
      chatFrame.append("Connection lost.");
      e.printStackTrace();
    }
  }
}
