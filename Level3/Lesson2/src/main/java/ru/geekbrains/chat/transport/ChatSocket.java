package ru.geekbrains.chat.transport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatSocket {
  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;

  public ChatSocket(ServerSocket serverSocket) throws IOException {
    socket = serverSocket.accept();
    in = new DataInputStream(socket.getInputStream());
    out = new DataOutputStream(socket.getOutputStream());
  }

  public ChatSocket(String host, int port) throws IOException {
    socket = new Socket(host, port);
    in = new DataInputStream(socket.getInputStream());
    out = new DataOutputStream(socket.getOutputStream());
  }
  public String readUTF() throws IOException {
    return in.readUTF();
  }
  public ChatMessage readMessage() throws IOException {
    return new ChatMessage(in.readUTF());
  }
  public void writeUTF(String message) throws IOException {
    out.writeUTF(message);
  }
}
