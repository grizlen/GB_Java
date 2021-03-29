package ru.geekbrains.chat.client;

public interface ClientEventListener {
  void onReceive(String message);
  default void doSubmit(String message){}
}
