package ru.geekbrains.hw6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
  private DataInputStream in;
  private DataOutputStream out;

  public static void main(String[] args) {
    new Server();
  }

  public Server() {
    try {
      ServerSocket socket = new ServerSocket(8989);
      System.out.println("Wait connection");
      Socket client = socket.accept();
      System.out.println("Client accepted: " + client);
      in = new DataInputStream(client.getInputStream());
      out = new DataOutputStream(client.getOutputStream());
      new Thread(() -> incomingListener()).start();
      sendRepeat();
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void incomingListener(){
    for (;;){
      try {
        System.out.println(in.readUTF());
      } catch (IOException e) {
        break;
      }
    }
  }
  private void sendRepeat(){
    Scanner scanner = new Scanner(System.in);
    for (;;) {
      if (scanner.hasNextLine()) {
        try {
          String line = scanner.nextLine();
          if (line.equals("/!exit")){
            out.writeUTF("/!shutdown");
            return;
          }
          out.writeUTF(line);
        } catch (IOException e) {
          break;
        }
      }
    }
    scanner.close();
  }
}
