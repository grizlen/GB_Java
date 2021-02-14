package ru.geekbrains.hw5;

import java.util.Arrays;

public class Main {
  static final int size = 1000000;
  static final int h = size / 2;
  static float[] arr = new float[size];
  public static void main(String[] args) {
    Arrays.fill(arr, 1);
    method1();
    Arrays.fill(arr, 1);
    method2();
  }
  private static void method1(){
    System.out.println("method1");
    long a = System.currentTimeMillis();
    work(arr);
    System.out.println(System.currentTimeMillis() - a);
  }
  private static void method2(){
    System.out.println("method2");
    long a = System.currentTimeMillis();
    float[] a1 = new float[h];
    float[] a2 = new float[h];
    System.arraycopy(arr, 0, a1, 0, h);
    System.arraycopy(arr, h, a2, 0, h);
    Thread t1 = new Thread(() -> work(a1));
    t1.start();
    Thread t2 = new Thread(() -> work(a2));
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.arraycopy(a1, 0, arr, 0, h);
    System.arraycopy(a2, 0, arr, h, h);
    System.out.println(System.currentTimeMillis() - a);
  }
  private static void work(float[] array){
//    System.out.println(Thread.currentThread().getName() + " start");
    for (int i = 0; i < array.length; i++) {
      array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i /5) * Math.cos(0.4f + i / 2));
    }
//    System.out.println(Thread.currentThread().getName() + " stop");
  }
}
