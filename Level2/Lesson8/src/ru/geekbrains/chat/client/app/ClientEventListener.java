package ru.geekbrains.chat.client.app;

public interface ClientEventListener {
  void onReceive(String message);
  default void doSubmit(String message){}
}
