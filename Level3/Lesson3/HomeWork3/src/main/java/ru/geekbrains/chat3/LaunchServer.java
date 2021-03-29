package ru.geekbrains.chat3;

import ru.geekbrains.chat3.server.Server;

import java.io.IOException;

public class LaunchServer {
  public static void main(String[] args) throws IOException {
    new Server(8989);
  }
}
