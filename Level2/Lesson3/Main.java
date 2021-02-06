package ru.geekbrains.hw3;

import ru.geekbrains.hw3.point1.WordCounter;
import ru.geekbrains.hw3.point2.PhoneBook;

public class Main {
  public static void main(String[] args) {
//    point 1
    System.out.println("Пункт 1");
    String[] words = {
        "sunday",     "sunday",   "sunday",   "sunday",
        "monday",     "monday",   "monday",
        "tuesday",    "tuesday",  "tuesday",
        "wednesday",  "wednesday",
        "thursday",   "thursday",
        "friday",     "friday",
        "saturday",   "saturday", "saturday", "saturday"
    };
    new WordCounter(words).showInfo();
//    point 2
    System.out.println("Пункт 2");
    PhoneBook book = new PhoneBook();
    book.add("Иванов", "+7 111 22 33 001");
    book.add("Иванов", "+7 111 22 33 002");
    book.add("Петров", "+7 111 22 33 003");
    System.out.print(book.get("ХЗ"));
    System.out.print(book.get("Иванов"));
    System.out.print(book.get("Петров"));
  }
}
