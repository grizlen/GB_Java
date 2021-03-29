package ru.geekbrains.chat3.client.GUI;

import ru.geekbrains.chat3.client.ClientEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatInputPanel extends JPanel implements ActionListener {

  private ClientEventListener eventListener;
  private JTextField inputField;

  public ChatInputPanel(ClientEventListener eventListener) {
    this.eventListener = eventListener;
    setLayout(new BorderLayout());
    inputField = new JTextField();
    inputField.addActionListener(this);
    JButton submitButton = new JButton(">>>");
    submitButton.addActionListener(this);
    add(inputField, BorderLayout.CENTER);
    add(submitButton, BorderLayout.EAST);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    eventListener.doSubmit(inputField.getText());
    inputField.setText("");
    inputField.grabFocus();
  }
}
