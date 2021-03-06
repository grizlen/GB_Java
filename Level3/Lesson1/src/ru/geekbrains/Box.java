package ru.geekbrains;

import java.util.ArrayList;

public class Box<T extends Fruit> {
  private final ArrayList<T> items = new ArrayList<>();

  public Box(T... fruits) {
    items.addAll(HwArrays.toArraylist(fruits));
  }

  public float getWeight(){
    float result = 0f;
    for (T item: items){
      result += item.weight();
    }
    return result;
  }
  public boolean compare(Box<?> box){
    return getWeight() == box.getWeight();
  }
  public void moveTo(Box<T> box){
    box.items.addAll(items);
    items.clear();
  }
  public void append(T fruit){
    items.add(fruit);
  }
}
