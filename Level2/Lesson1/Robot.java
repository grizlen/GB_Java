package ru.geekbrains.hw2_1;

public class Robot extends AbstractUnut{
  public Robot(int runLimit, int jumpLimit) {
    init(runLimit, jumpLimit);
  }
  @Override
  public String toString() {
    return "робот";
  }
}
