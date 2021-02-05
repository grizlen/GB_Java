package ru.geekbrains.hw2_1;

public class AbstractUnut implements Unit{
  private int runLimit, jumpLimit;
  private int runVitality;
  private boolean isFail;

  protected void init(int runLimit, int jumpLimit){
    this.runLimit = runLimit;
    this.jumpLimit = jumpLimit;
    reset();
  }
  @Override
  public boolean run(int value) {
    if (runVitality < value){
      System.out.println("не пробежал " + value + " метров");
      isFail = true;
      return false;
    }
    runVitality -= value;
    System.out.println("пробежал " + value + " метров");
    return true;
  }
  @Override
  public boolean jump(int value) {
    if (jumpLimit < value){
      System.out.println("не перепрыгнул " + value + " сантиметров");
      isFail = true;
      return false;
    }
    System.out.println("перепрыгнул " + value + " сантиметров");
    return true;
  }

  @Override
  public void reset() {
    runVitality = runLimit;
    isFail = false;
  }

  @Override
  public boolean getIsFail() {
    return isFail;
  }

}
