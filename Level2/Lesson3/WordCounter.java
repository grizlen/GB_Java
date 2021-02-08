package ru.geekbrains.hw3.point1;

import java.util.*;

public class WordCounter {
  private String[] words;

  public WordCounter(String[] words) {
    this.words = words;
  }
  public void showInfo(){
    List<String> list = new LinkedList<>(Arrays.asList(words));
    Set<String> set = new HashSet<>(list);
    Iterator<String> iterator = set.iterator();
    while (iterator.hasNext()){
      String word;
      System.out.printf("Word: %s Repeats: %d\n", (word = iterator.next()), numEntries(list, word));
    }
  }
  private int numEntries(List<String> list, String word){
    int result = 0;
    int i;
    while ((i = list.indexOf(word)) >= 0){
      list.remove(i);
      result++;
    }
    return result;
  }
}
