import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(get4Trailing(1, 2, 4, 4, 2, 3, 4, 1, 7)));
    System.out.println(checkIs1or4(4, 5));
  }

  //2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
  // Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки.
  // Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
  // Написать набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
  public static int[] get4Trailing(int... arr) {
    if (arr.length == 0) {throw new RuntimeException("array empty"); }
    for (int i = arr.length; i > 0; i--){
      if (arr[i - 1] == 4) {
        return Arrays.copyOfRange(arr, i, arr.length);
      }
    }
    return new int[0];
  }


  //3. Написать метод, который проверяет состав массива из чисел 1 и 4.
  // Если в нем нет хоть одной четверки или единицы, то метод вернет false;
  // Написать набор тестов для этого метода (по 3-4 варианта входных данных).
  public static boolean checkIs1or4(int ... arr){
    for (int i: arr) {
      if (i == 1 || i == 4) { return true;}
    }
    return false;
  }
}
