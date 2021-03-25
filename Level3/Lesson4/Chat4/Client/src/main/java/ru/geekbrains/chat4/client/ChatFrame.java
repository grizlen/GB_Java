package ru.geekbrains.chat4.client;

import java.util.List;

public interface ChatFrame {
  void append(String text);
  void appendlist(List<String> stringList);
  void disconnect();
}
