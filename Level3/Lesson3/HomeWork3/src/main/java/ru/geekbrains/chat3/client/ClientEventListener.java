package ru.geekbrains.chat3.client;

public interface ClientEventListener {
  void onReceive(String message);
  default void doSubmit(String message){}
}
