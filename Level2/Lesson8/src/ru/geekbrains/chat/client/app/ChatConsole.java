package ru.geekbrains.chat.client.app;

import java.util.Scanner;

public class ChatConsole implements ClientEventListener {
  private final ChatClient chatClient;

  public ChatConsole() {
    chatClient = new ChatClient("localhost", 8989);
    new Thread(()->{
      Scanner scanner = new Scanner(System.in);
      while (true) {
        chatClient.sendMessage(scanner.nextLine());
      }
    }).start();
    chatClient.start(this);
  }

  @Override
  public void onReceive(String message) {
    if (message.equals("-close")){
      System.exit(0);
    }
    System.out.println(message);
  }
}
