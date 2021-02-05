package ru.geekbrains.hw2;

public class MyArraySizeException extends Exception{
  public MyArraySizeException(int s){
    super("Array size not " + s);
  }
  public MyArraySizeException(int i, int s){
    super(String.format("Subarray %d size not %d", i, s));
  }
}