package ru.geekbrains.hw6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;

  public static void main(String[] args) {
    new Client();
  }
  public Client() {
    try {
      socket = new Socket("127.0.0.1", 8989);
      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());
      out.writeUTF("client: " + socket);
      new Thread(()->{incomingListener();}).start();
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
          if (line.equals("/!exit")) {
            break;
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
