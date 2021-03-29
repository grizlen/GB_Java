package ru.geekbrains.chat.client;

import ru.geekbrains.chat.client.gui.ChatFrame;

public class ChatWindow implements ClientEventListener {
  private ChatFrame chatFrame;
  private ChatClient chatClient;

  public ChatWindow() {
    try {
      chatFrame = new ChatFrame(this);
      chatClient = new ChatClient("localhost", 8989);
      chatClient.start(this);
    } catch (Exception e) {
      chatFrame.append("-close");
    }
  }

  @Override
  public void onReceive(String message) {
    chatFrame.append(message);
  }

  @Override
  public void doSubmit(String message) {
    try {
      chatClient.sendMessage(message);
    } catch (Exception e) {
      chatFrame.append("Connection lost.");
      e.printStackTrace();
    }
  }
}
