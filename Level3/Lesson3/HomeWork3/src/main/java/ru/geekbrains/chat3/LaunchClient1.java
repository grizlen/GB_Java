package ru.geekbrains.chat3;

import ru.geekbrains.chat3.client.Client;

import java.io.IOException;

public class LaunchClient1 {
  public static void main(String[] args) throws IOException {
    new Client("localhost", 8989, "log1.txt");
  }
}
