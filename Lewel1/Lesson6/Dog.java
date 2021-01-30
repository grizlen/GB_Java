package com.company;

public class Dog extends Animal{
    public Dog(String name) {
        super(name);
        runLimit = 500;
        swimLimit = 20;
    }
    @Override
    public String presentStr() {
        return "собака " + name;
    }
}
