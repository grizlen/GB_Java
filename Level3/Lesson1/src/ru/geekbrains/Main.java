package ru.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Integer[] arr = {1, 2 , 3 ,4, 5};
    HwArrays.swapArrayElements(arr, 0 ,4);
    System.out.println(Arrays.toString(arr));

    ArrayList<Integer> lst = HwArrays.toArraylist(arr);
    System.out.println(lst.toString());

    Box<Apple> a1 = new Box<>(new Apple[]{new Apple(), new Apple(), new Apple()});
    Box<Apple> a2 = new Box<>(new Apple[]{new Apple(), new Apple()});
    System.out.printf("a1 weight = %f; a2 weight = %f\n", a1.getWeight(), a2.getWeight());

    a1.moveTo(a2);
    System.out.printf("a1 weight = %f; a2 weight = %f\n", a1.getWeight(), a2.getWeight());

    Box<Orange> o1 = new Box<>(new Orange[]{new Orange(), new Orange(), new Orange()});
    Box<Orange> o2 = new Box<>(new Orange[]{new Orange(), new Orange()});
    System.out.printf("o1 weight = %f; o2 weight = %f\n", o1.getWeight(), o2.getWeight());
  }
}
