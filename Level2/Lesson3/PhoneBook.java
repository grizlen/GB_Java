package ru.geekbrains.hw3.point2;

import java.util.*;

public class PhoneBook {
  private Map<String, Contact> map;
  public PhoneBook() {
    map = new HashMap<>();
  }
  public void add(String name, String phone){
    Contact contact = map.get(name);
    if (contact == null){
      contact = new Contact(name);
      map.put(name, contact);
    }
    contact.phones.add(phone);
  }
  public String get(String name){
    Contact contact = map.get(name);
    if (contact == null){
      return name + " not found\n";
    }
    else return contact.strInfo();
  }
  public void showInfo(){
    for (Contact contact: map.values()){
      System.out.print(contact.strInfo());
    }
  }

  private class Contact{
    private String name;
    private Set<String> phones;

    public Contact(String name) {
      this.name = name;
      phones = new HashSet<>();
    }
    private String strInfo(){
      StringBuilder result = new StringBuilder(name + ":\n");
      for (String phone: phones){
        result.append("\t" + phone + "\n");
      }
      return result.toString();
    }
  }
}
