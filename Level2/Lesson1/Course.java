package ru.geekbrains.hw2_1;

public class Course {
  private Barrier[] barriers;

  public Course(Barrier[] barriers) {
    this.barriers = barriers;
  }
  public void doIt(Team team){
    System.out.println(team + " стартует");
    for (int u = 0; u < Team.NUM_UNITS; u++){
      Unit unit = team.getUnit(u);
      System.out.printf("%d участник %s:\n", u + 1, unit);
      for (Barrier barrier: barriers){
        if (!barrier.doIt(unit)){
          break;
        }
      }
    }
  }
}
