package ru.geekbrains.homework;

public class Main {
    byte byteVar = 127;
    short shortVar = 32767;
    int intVar = 3;
    long longVar = 4;
    float floatVar = 1.5f;
    double doubleVar = 3.1415926;
    char charVar = 'A';
    boolean booleanVar = true;
    String stringVar = "Lesson 1";
    public static void main(String[] args) {
        Main m = new Main();
        m.method8(0);
        m.method8(100);
        m.method8(400);
        m.method8(2020);
        m.method8(2000);
        m.method8(1900);
        m.method8(1996);
    }
    private float method3(float a, float b, float c, float d){
        return a * (b + (c / d));
    }
    private boolean method4(int a, int b){
        int sum = a + b;
        return sum > 9 & sum < 21;
    }
    private void method5(int a){
        if (a < 0) System.out.println("отрицательное (" + a + ")");
        else System.out.println("положительное (" + a + ")");
    }
    private boolean method6(int a){
        return a < 0;
    }
    private void method7(String n){
        System.out.println("Привет, " + n);
    }
    private void method8(int y){
        if ((y % 4 == 0) && (y % 400 == 0) || (y % 100 != 0))
            System.out.println("год: " + y + " високосный.");
        else
            System.out.println("год: " + y + " не високосный.");

//        boolean result;
//        if (y % 400 == 0) result = true;
//        else if (y % 100 == 0) result = false;
//        else result = (y % 4 == 0);
//        if (result) System.out.println("год: " + y + " високосный.");
//        else System.out.println("год: " + y + " не високосный.");
    }
}
