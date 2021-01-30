package com.company;

public class Cat extends Animal{
    public Cat(String name) {
        super(name);
        runLimit = 200;
    }
    @Override
    public String presentStr() {
        return "кот " + name;
    }
}
