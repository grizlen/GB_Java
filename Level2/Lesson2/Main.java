package ru.geekbrains.hw2;

public class Main {
  public static void main(String[] args) {
    sum4x4(new String[][]{
        {"11", "12", "13", "14"},
        {"21", "22", "23", "24"},
        {"31", "32", "33", "34"},
        {"41", "42", "43", "44"}
    });
  }
  private static void sum4x4(String[][] arr){
    try {
      checkSize(arr);
      System.out.println(trySum(arr));
    } catch (MyArraySizeException |
        MyArrayDataException ex){
      System.out.println(ex.getMessage());
      //ex.printStackTrace();
    }
  }
  private static void checkSize(String[][] arr)
      throws MyArraySizeException {
    if (arr.length != 4) {
      throw new MyArraySizeException(4);
    }
    for (int i = 0; i < 4; i++){
      if (arr[i].length != 4){
        throw new MyArraySizeException(i, 4);
      }
    }
  }
  private static int trySum(String[][] arr)
      throws MyArrayDataException{
    int result = 0;
    for (int x = 0; x < 4; x++){
      for (int y = 0; y < 4; y++){
        try {
          result += Integer.parseInt(arr[x][y]);
        } catch (Exception ex){
          throw new MyArrayDataException(x, y, arr[x][y]);
        }
      }
    }
    return result;
  }
}
