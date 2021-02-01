package ru.geekbrains.hw2_1;

public interface Unit {
  boolean run(int value);
  boolean jump(int value);
  void reset();
  boolean getIsFail();
}
