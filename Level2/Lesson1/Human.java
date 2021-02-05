package ru.geekbrains.hw2_1;

public class Human extends AbstractUnut{
  public Human(int runLimit, int jumpLimit) {
    init(runLimit, jumpLimit);
  }
  @Override
  public String toString() {
    return "человек";
  }
}
