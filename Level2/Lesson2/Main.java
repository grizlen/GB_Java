package ru.geekbrains.hw2;

public class Main {
  public static void main(String[] args) {
    sum4x4(new String[][]{
        {"11", "12", "13", "14"},
        {"21", "22", "23", "24"},
        {"31", "32", "a33", "34"},
        {"41", "42", "43", "44"}
    });
  }
  private static void sum4x4(String[][] arr){
    try {
      checkSize(arr);
      System.out.println(trySum(arr));
    } catch (MyArraySizeException | MyArrayDataException ex){
      ex.printStackTrace();
    }
  }
  private static void checkSize(String[][] arr) throws MyArraySizeException {
    if (arr.length != 4) {
      throw new MyArraySizeException("Array size not 4");
    }
    for (int i = 0; i < 4; i++){
      if (arr[i].length != 4){
        throw new MyArraySizeException(String.format("Subarray %d size not 4", i));
      }
    }
  }
  private static int trySum(String[][] arr) throws MyArrayDataException{
    int result = 0;
    for (int x = 0; x < 4; x++){
      for (int y = 0; y < 4; y++){
        try {
          result += Integer.parseInt(arr[x][y]);
        } catch (NumberFormatException ex){
          throw new MyArrayDataException(String.format("Can not convert to int ([%d, %d] = %s)", x, y, arr[x][y]), ex);
        }
      }
    }
    return result;
  }
}
