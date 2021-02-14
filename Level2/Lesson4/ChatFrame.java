package ru.geekbrains.hw4;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame {
  public ChatFrame() throws HeadlessException {
    setTitle("Home work 4");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setBounds(0, 0, 500, 500);

    setLayout(new BorderLayout());
    ChatClient client = new ChatClient();
    add(client, BorderLayout.CENTER);

    ChatSender sender = new ChatSender(client);
    add(sender, BorderLayout.SOUTH);
    setVisible(true);
  }
}
