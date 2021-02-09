package ru.geekbrains.hw4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatSender extends JPanel implements ActionListener {
  private JTextField textField;
  private ChatTarget target;

  public ChatSender(ChatTarget target) {
    this.target = target;
    setLayout(new BorderLayout());
    textField = new JTextField();
    textField.addActionListener(this);
    add(textField, BorderLayout.CENTER);
    JButton button = new JButton(">>>");
    button.addActionListener(this);
    add(button, BorderLayout.EAST);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!textField.getText().isEmpty()) {
      target.receiveText(textField.getText());
      textField.setText("");
    }
  }
}
