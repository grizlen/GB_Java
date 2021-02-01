package ru.geekbrains.hw2_1;

public class Wall implements Barrier{
  private final int height;

  public Wall(int height) {
    this.height = height;
  }

  @Override
  public boolean doIt(Unit unit) {
    return unit.jump(height);
  }
}
