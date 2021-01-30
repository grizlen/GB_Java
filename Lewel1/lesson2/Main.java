package com.company;

public class Main {
    public static void main(String[] args) {
        Dz_1();
        Dz_2();
        Dz_3();
        Dz_4();
        Dz_5();
        System.out.println(Dz_6(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));
        System.out.println(Dz_6(new int[]{1, 2, 2, 1, 2, 2, 10, 1}));
        int[] ar = {1, 2, 3, 4, 5};
        Dz_7(ar, 1);
        Dz_7(ar, -2);
        Dz_7(ar, 1);
    }
    static void Dz_1(){
        int[] ar = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < ar.length; i++){
            ar[i] = (ar[i] == 0) ? 1: 0;
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
    static void Dz_2(){
        int[] ar = new int[8];
        for (int i = 0; i < ar.length; i++){
            ar[i] = i * 3;
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
    static void Dz_3(){
        int[] ar = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < ar.length; i++){
            if (ar[i] < 6) ar[i] *= 2;
            System.out.print(ar[i] + " ");
        }
        System.out.println();
    }
    static void Dz_4(){
        int[][] ar = new int[5][5];
        for (int i = 0; i < ar.length; i++){
            ar[i][i] = 1;
            ar[i][ar[i].length - i - 1] = 1;
            for (int j = 0; j < ar[i].length; j++)
                System.out.print(ar[i][j] + " ");
            System.out.println();
        }
    }
    static void Dz_5(){
        int[] ar = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int imin = 0, imax = 0;
        for (int i = 0; i < ar.length; i++){
            if (ar[i] < min){
                min = ar[i];
                imin = i;
            }
            if (ar[i] > max){
                max = ar[i];
                imax = i;
            }
        }
        System.out.println("min: [" + imin + "] = " + min);
        System.out.println("max: [" + imax + "] = " + max);
    }
    static boolean Dz_6(int[] ar){
        for (int i = 1; i < ar.length; i++){
            int left = 0, right = 0;
            for (int j = 0; j < ar.length; j++){
                if (j < i) left += ar[j];
                else right += ar[j];
            }
            if (left == right) return true;
        }
        return false;
    }
    static void Dz_7(int[] ar, int shift){
        while (shift != 0){
            if (shift < 0){
                int tmp = ar[0];
                for (int i = 1; i < ar.length; i++)
                    ar[i - 1] = ar[i];
                ar[ar.length - 1] = tmp;
                shift++;
            }
            else {
                int tmp = ar[ar.length - 1];
                for (int i = ar.length - 2; i >= 0; i--)
                    ar[i + 1] = ar[i];
                ar[0] = tmp;
                shift--;
            }
        }
        for (int i = 0; i < ar.length; i++)
            System.out.print(ar[i] + " ");
        System.out.println();
    }
}
