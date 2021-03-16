package ru.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;

public class HwArrays {
  public static <T extends Object> void swapArrayElements(T[] arr, int e1, int e2) throws ArrayIndexOutOfBoundsException{
    T tmp = arr[e1];
    arr[e1] = arr[e2];
    arr[e2] = tmp;
  }

  public static <T extends Object> ArrayList<T> toArraylist(T[] arr){
    return new ArrayList<>(Arrays.asList(arr));
  }
}
