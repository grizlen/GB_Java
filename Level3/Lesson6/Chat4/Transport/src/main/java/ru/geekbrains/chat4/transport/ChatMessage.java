package ru.geekbrains.chat4.transport;

public class ChatMessage {
  public static final int
      INVALID = -1,
      MESSAGE = 0,
      INFO = 1,
      AUTH = 2,
      REG = 3,
      NICK = 4,
      PRIVATE = 5,
      EXIT = 6;
  private final String line;

  private int cmd;
  private String[] param;

  public ChatMessage(String line) {
    this.line = line;
    if (line.isBlank()){
      cmd = INVALID;
      param = new String[]{""};
    } else if (line.charAt(0) != '-'){
      cmd = MESSAGE;
      param = new String[]{line};
    } else {
      param = line.split("\\s");
      if (param[0].equals("-i") && param.length > 2) {
        cmd = INFO;
        param = line.split("\\s", 2);
      } else if (param[0].equals("-a") && param.length == 3) {
        cmd = AUTH;
      } else if (param[0].equals("-r") && param.length == 4){
        cmd = REG;
      } else if (param[0].equals("-n") && param.length == 2) {
        cmd = NICK;
      } else if (param[0].equals("-p") && param.length > 2) {
        cmd = PRIVATE;
        param = line.split("\\s", 3);
      } else if (param[0].equals("-q") && param.length == 1) {
        cmd = EXIT;
      } else {
        cmd = INVALID;
        param = new String[]{line};
      }
    }
  }

  public int getCmd() {
    return cmd;
  }

  public String getParam(int index) {
    return (index > -1 && index < param.length) ? param[index] : "";
  }

  public String getText(){ return line; }
}
