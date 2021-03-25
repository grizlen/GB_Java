package ru.geekbrains.chat4.client;

import java.io.IOException;

public class Launcher1 {
  public static void main(String[] args) {
    try {
      new Client("log1.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
