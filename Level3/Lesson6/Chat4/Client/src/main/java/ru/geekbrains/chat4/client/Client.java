package ru.geekbrains.chat4.client;

import ru.geekbrains.chat4.client.service.History;
import ru.geekbrains.chat4.transport.ChatMessage;
import ru.geekbrains.chat4.transport.ChatSocket;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class Client implements Consumer<String> {
  private final ChatSocket socket;
  private final ChatFrame chatFrame;
  private final History history;
  private boolean isStarted = true;

  public Client(String filename) throws IOException {
    chatFrame = new ClientFrame(this);
    socket = new ChatSocket("localhost", 8989);
    history = new History(filename);
    List<String> lastLines = history.getLast();
    if (history.openWriter()){
      lastLines.forEach(history::append);
    }
    chatFrame.appendlist(lastLines);
    start();
    history.close();
  }

  private void start(){
    while (isStarted){
      try {
        receiveMessage();
      } catch (IOException e) {
        System.out.println(e.getMessage());
        stop();
      }
    }
  }

  private void receiveMessage() throws IOException {
    ChatMessage message = socket.readMessage();
    switch (message.getCmd()){
      case ChatMessage.MESSAGE:
        chatFrame.append(message.getText());
        history.append(message.getText());
        break;
      case ChatMessage.INFO:
        chatFrame.append("[info] " + message.getText());
        break;
      case ChatMessage.EXIT:
        stop();
        break;
      default:
        chatFrame.append("[error] invalid command: " + message.getText());
    }
  }

  private void stop() {
    isStarted = false;
    chatFrame.disconnect();
  }

  @Override
  public void accept(String s) {
    try {
      socket.writeUTF(s);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      stop();
    }
  }
}
