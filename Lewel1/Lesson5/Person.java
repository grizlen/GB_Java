package com.company;

public class Person {

    private String name, job, e_mail, phone;
    private float amount;
    private int age;

    public Person(String name, String job, String e_mail, String phone, float amount, int age){
        this.name = name;
        this.job = job;
        this.e_mail = e_mail;
        this.phone = phone;
        this.amount = amount;
        this.age = age;
    }
    public int getAge(){return age;}
    public void print(){
        System.out.println("Ф.И.О: " + name);
        System.out.println("должность: " + job);
        System.out.println("email: " + e_mail);
        System.out.println("телефон: " + phone);
        System.out.println("зарплата: " + amount);
        System.out.println("возраст: " + age);
        System.out.println();
    }
}
