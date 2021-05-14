package ru.geekbrains;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable{
  private static int CARS_COUNT;
  private volatile static boolean win = false;
  static {
    CARS_COUNT = 0;
  }
  private Race race;
  private int speed;
  private String name;
  private CyclicBarrier barrier;

  public int getSpeed() {
    return speed;
  }

  public String getName() {
    return name;
  }

  public void setBarrier(CyclicBarrier barrier) {
    this.barrier = barrier;
  }

  public Car(Race race, int speed) {
    this.race = race;
    this.speed = speed;
    CARS_COUNT++;
    this.name = "Участник #" + CARS_COUNT;
  }

  @Override
  public void run() {
    try {
      System.out.println(this.name + " готовится");
      Thread.sleep(500 + (int)(Math.random() * 800));
      System.out.println(this.name + " готов");
      barrier.await();
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (int i = 0; i < race.getStages().size(); i++) {
      race.getStages().get(i).go(this);
    }
    if (!win){
      win = true;
      System.out.println(name + " win!");
    }
    try {
      barrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}
