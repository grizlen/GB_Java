package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
	    selectGame();
        System.out.println("good bye.");
	    scanner.close();
    }
    static int scanInt(){
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.println("Требуется число");
        }
        return scanner.nextInt();
    }
    static int scanInt(int max){
        int result = scanInt();
        while (result < 0 || result > max){
            System.out.println("число должно быть от 0 до " + max);
            result = scanInt();
        }
        return result;
    }
    static void selectGame(){
        int result;
        do {
            System.out.println("Выберите игру 1 - числа, 2 - слова, 0 - выход");
            result = scanInt();
            switch (result){
                case 0: return;
                case 1: gameNumbers(); break;
                case 2: gameWords(); break;
                default: System.out.println("не правильный выбор 1 - числа, 2 - слова, 0 - выход");
            }
        } while (true);
    }
    static void gameNumbers(){
        System.out.println("числа");
        int rpt;
        do {
            gameNumbersMain();
            System.out.println("повторить? 1 - да, 0 - нет");
            rpt = scanInt(1);
        } while (rpt == 1);
    }
    static void gameNumbersMain(){
        System.out.println("Отгадайте число от 0 до 9 за 3 попытки");
        int num = new Random().nextInt(10);
//        System.out.println(num);
        int stage = 1;
        int result;
        do {
            System.out.println("попытка " + stage++);
            result = scanInt(9);
            if (result > num) System.out.println("ваше число больше");
            else if (result < num) System.out.println("ваше число меньше");
            else break;
        }while (stage < 4);
        if (result == num) System.out.println("вы выигали");
        else System.out.println("вы проиграли");
    }
    static String[] words =  {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    static void printWords(){
        for (String s : words){
            System.out.print(s + " ");
        }
        System.out.println();
    }
    static void gameWords(){
        System.out.println("слова");
        int rpt;
        do {
            gameWordsMain();
            System.out.println("повторить? 1 - да, 0 - нет");
            rpt = scanInt(1);
        } while (rpt == 1);
    }
    static String scanWord(){
        String result = scanner.next();
        return result.toLowerCase();
    }
    static boolean checkWord(String a, String b){
        if (a.compareTo(b) == 0) return true;
        boolean check = true;
        for (String s : words){
            if (s.compareTo(a) == 0){
                check = false;
                break;
            }
        }
        if (check) System.out.println("такого слова вообще нет в списке");
        return false;
    }
    static void printHint(String a, String b){
        boolean check;
        char c;
        for (int i = 0; i < 15; i++){
            check = i < a.length() && i < b.length();
            if (check){
                c = a.charAt(i);
                c = (c == b.charAt(i)) ? c : '#';
            }
            else c = '#';
            System.out.print(c);
        }
        System.out.println();
    }
    static void gameWordsMain(){
        System.out.println("отгадайте слово из набора:");
        printWords();
        String str = words[new Random().nextInt(words.length)];
//        System.out.println(str);
        String result;
        do {
            result = scanWord();
            if (checkWord(result, str)) break;
            printHint(result, str);
        } while (true);
        System.out.println("вы выиграли");
    }
}
