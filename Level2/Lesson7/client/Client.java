package ru.geekbrains.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;

  public Client() {
    try {
      socket = new Socket("127.0.0.1", 8989);
      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());
      new Thread(()->sendProcess()).start();
      incomingListener();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void sendProcess() {
    Scanner scanner = new Scanner(System.in);
    while (true){
      sendMessage(scanner.nextLine());
    }
  }
  private void incomingListener() throws IOException {
    while (true){
      System.out.println(in.readUTF());
    }
  }
  private void sendMessage(String msg)  {
    try {
      out.writeUTF(msg);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
