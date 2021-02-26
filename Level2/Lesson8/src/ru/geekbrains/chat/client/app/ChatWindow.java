package ru.geekbrains.chat.client.app;

import ru.geekbrains.chat.client.gui.ChatFrame;

public class ChatWindow implements ClientEventListener {
  private ChatFrame chatFrame;
  private ChatClient chatClient;

  public ChatWindow() {
    chatFrame = new ChatFrame(this);
    chatClient = new ChatClient("localhost", 8989);
    chatClient.start(this);
  }

  @Override
  public void onReceive(String message) {
    chatFrame.append(message);
  }

  @Override
  public void doSubmit(String message) {
    chatClient.sendMessage(message);
  }
}
