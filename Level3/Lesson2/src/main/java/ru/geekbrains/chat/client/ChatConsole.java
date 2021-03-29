package ru.geekbrains.chat.client;

import java.util.Scanner;

public class ChatConsole implements ClientEventListener {

  public ChatConsole() {
    try {
      ChatClient chatClient = new ChatClient("localhost", 8989);
      new Thread(() -> {
        Scanner scanner = new Scanner(System.in);
        while (true) {
          chatClient.sendMessage(scanner.nextLine());
        }
      }).start();
      chatClient.start(this);
    }catch (Exception e){
      System.out.println("Connection lost.");
      System.exit(0);
    }
  }

  @Override
  public void onReceive(String message) {
    if (message.equals("-close")){
      System.exit(0);
    }
    System.out.println(message);
  }
}
