package ru.geekbrains.chat.server;

import ru.geekbrains.chat.auth.AuthEntry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
  private Server server;
  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;
  private AuthEntry authEntry;

  public AuthEntry getAuthEntry() {
    return authEntry;
  }

  public String getNick(){
    return authEntry == null ? "": authEntry.getNick();
  }

  public ClientHandler(Server server, Socket socket) {
    this.server = server;
    this.socket = socket;
    try {
      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());
      doAuth();
      new Thread(()->listen()).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void doAuth() throws IOException {
    while (true){
      Input input = new Input(in.readUTF());
      if (input.cmd == Input.AUTH){
        authEntry = server.getAuthServise().findEntry(input.param[1], input.param[2]);
        if (authEntry == null){
          sendMessage("Invalid login/password");
        } else if (server.subscribe(this)) {
          sendMessage("login OK");
          return;}
      }else {
        sendMessage("Invalid authentication request (-auth login password)");
      }
    }
  }
  private void listen() {
    while (true){
      try {
        Input input = new Input(in.readUTF());
        if (input.cmd == Input.PRIVATE){
          if (!server.privateMessage(input.param[1],getNick() + ": " + input.param[2])){
            sendMessage(input.param[1] + " not found");
          }
        } else if (input.cmd == Input.MESSAGE){
          server.broadcastMessage(getNick() + ": " + input.param[0]);
        }
        else {
          sendMessage("invalid comand: " + input.param[0]);
        }
      } catch (IOException e) {
        new RuntimeException("Client.listen ", e);
      }
    }
  }
  public void sendMessage(String message) {
    try {
      out.writeUTF(message);
    } catch (IOException e) {
      new RuntimeException("Client.sendMessage ", e);
    }
  }
  private class Input{
    public static final int
        INVALID = -1,
        MESSAGE = 0,
        AUTH = 1,
        PRIVATE = 2;

    private int cmd;
    private String[] param;
    public Input(String line) {
      if (line.charAt(0) != '-'){
        cmd = MESSAGE;
        param =  new String[]{line};
      }
      else{
        param = line.split("\\s");
        if (param[0].equals("-auth") && param.length == 3){
          cmd = AUTH;
        } else if (param[0].equals("-w") && param.length == 3){
          cmd = PRIVATE;
        }
        else {
          cmd = INVALID;
          param =  new String[]{line};
        }
      }
    }
  }
}
