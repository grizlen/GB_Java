package ru.geekbrains;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
  public static final int CARS_COUNT = 4;

  public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
    Race race = new Race(new Road(60), new Tunnel(), new Road(40));
    Car[] cars = new Car[CARS_COUNT];
    CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT + 1);
    Tunnel.semaphore = new Semaphore(CARS_COUNT / 2);
    for (int i = 0; i < cars.length; i++) {
      cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
      cars[i].setBarrier(barrier);
    }

    for (int i = 0; i < cars.length; i++) {
      new Thread(cars[i]).start();
    }
    barrier.await();
    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
    barrier.await();
    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
  }
}
