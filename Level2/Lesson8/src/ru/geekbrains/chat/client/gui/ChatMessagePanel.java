package ru.geekbrains.chat.client.gui;

import javax.swing.*;
import java.awt.*;

public class ChatMessagePanel extends JPanel {
  private JTextArea textArea;

  public ChatMessagePanel() {
    setLayout(new BorderLayout());
    textArea = new JTextArea();
    textArea.setEditable(false);
    add(new JScrollPane(textArea), BorderLayout.CENTER);
  }
  public void append(String text){
    textArea.append(text + "\n");
  }
}
