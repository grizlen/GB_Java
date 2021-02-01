package ru.geekbrains.hw2_1;

public class Track implements Barrier{
  private final int lenght;

  public Track(int lenght) {
    this.lenght = lenght;
  }

  @Override
  public boolean doIt(Unit unit) {
    return unit.run(lenght);
  }
}
