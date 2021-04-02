package ru.geekbrains.chat4.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.function.Consumer;

public class ClientFrame extends JFrame implements ActionListener, ChatFrame {
  private final JTextArea messageArea;
  private final JTextField inputField;
  private JPanel inputPanel;
  private final Consumer consumer;

  public ClientFrame(Consumer consumer) throws HeadlessException{
    this.consumer = consumer;
    setTitle("Chat 4");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setBounds(0, 0, 500, 500);
    setLayout(new BorderLayout());
    messageArea = new JTextArea();
    messageArea.setEditable(false);
    add(new JScrollPane(messageArea), BorderLayout.CENTER);
    inputField = new JTextField();
    inputField.addActionListener(this);
    JButton submitButton = new JButton(">>>");
    submitButton.addActionListener(this);
    inputPanel = new JPanel(new BorderLayout());
    inputPanel.add(inputField, BorderLayout.CENTER);
    inputPanel.add(submitButton, BorderLayout.EAST);
    add(inputPanel, BorderLayout.SOUTH);
    setVisible(true);
    inputField.grabFocus();
    addWindowListener(new WindowListener() {
      @Override
      public void windowOpened(WindowEvent e) {}
      @Override
      public void windowClosing(WindowEvent e) {
        consumer.accept("-q");
      }
      @Override
      public void windowClosed(WindowEvent e) {}
      @Override
      public void windowIconified(WindowEvent e) {}
      @Override
      public void windowDeiconified(WindowEvent e) {}
      @Override
      public void windowActivated(WindowEvent e) {}
      @Override
      public void windowDeactivated(WindowEvent e) {}
    });
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    consumer.accept(inputField.getText());
    inputField.setText("");
    inputField.grabFocus();
  }

  @Override
  public void append(String text) {
    messageArea.append(text + "\n");
  }

  @Override
  public void appendlist(List<String> stringList) {
    stringList.forEach(this::append);
  }

  @Override
  public void disconnect() {
    append("connection lost.");
    inputPanel.setVisible(false);
  }
}
