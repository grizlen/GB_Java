package ru.geekbrains.hw2_1;

public class Team {
  public static int NUM_UNITS = 4;
  private String name;
  private final Unit[] units = new Unit[NUM_UNITS];

  public Team(String name, Unit u1, Unit u2, Unit u3, Unit u4) {
    this.name = name;
    units[0] = u1;
    units[1] = u2;
    units[2] = u3;
    units[3] = u4;
  }
  public void showInfo(){
    System.out.println(this + ":");
    for (int i = 0; i < NUM_UNITS; i++) {
      System.out.printf("\t %d %s\n", i, units[i]);
    }
  }
  public void showRsults(){
    System.out.println(this + " дошли до финиша:");
    for (int i = 0; i < NUM_UNITS; i++) {
      if (units[i].getIsFail()) continue;
      System.out.printf("\t %d %s\n", i, units[i]);
    }
  }
  public Unit getUnit(int u){return units[u];}

  @Override
  public String toString() {
    return "Команда " + name;
  }
}
