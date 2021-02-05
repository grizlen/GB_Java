package ru.geekbrains.hw2;

public class MyArrayDataException
    extends Exception {
  public MyArrayDataException(int x, int y, String v){
    super(String.format("Can not convert to int ([%d, %d] = %s)", x, y, v));
  }
}