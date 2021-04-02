package ru.geekbrains.chat4.client;

import java.io.IOException;

public class Launcher2 {
  public static void main(String[] args) {
    try {
      new Client("log2.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
