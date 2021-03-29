package ru.geekbrains.chat3.client.GUI;

import ru.geekbrains.chat3.client.ClientEventListener;
import ru.geekbrains.chat3.client.history.History;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChatFrame extends JFrame {
  private final ChatMessagePanel messagePanel;
  private final ChatInputPanel inputPanel;

  public ChatFrame(ClientEventListener eventListener) throws HeadlessException {
    setTitle("Chat");
    setBounds(0, 0, 500, 500);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    messagePanel = new ChatMessagePanel();
    add(messagePanel, BorderLayout.CENTER);
    inputPanel = new ChatInputPanel(eventListener);
    add(inputPanel, BorderLayout.SOUTH);
    setVisible(true);
    addWindowListener(new WindowListener() {
      @Override
      public void windowClosing(WindowEvent e) {
        eventListener.doSubmit("-q");
      }

      @Override
      public void windowOpened(WindowEvent e) {}
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
  public void append(String text){
    if (text.equals("-close")){
      inputPanel.setVisible(false);//Enabled(false);
    }
    messagePanel.append(text);}
}
