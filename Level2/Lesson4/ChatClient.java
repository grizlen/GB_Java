package ru.geekbrains.hw4;

import javax.swing.*;
import java.awt.*;

public class ChatClient extends JPanel implements ChatTarget{
  private JTextArea textArea;
  public ChatClient() {
    setLayout(new BorderLayout());
    textArea = new JTextArea();
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane, BorderLayout.CENTER);
  }

  @Override
  public void receiveText(String message) {
    textArea.append(">>>\n");
    textArea.append(message);
    textArea.append("\n");
  }
}
