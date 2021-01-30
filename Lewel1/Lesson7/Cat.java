package com.company;

public class Cat {
    private String name;
    private int appetite;
    private boolean fill;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        fill = false;
    }
    public void eat(Plate plate){
        fill = plate.decreaseFood(appetite);
    }
    public void info(){
        System.out.printf("кот: %s, аппетит: %d, %s\n", name, appetite, fill ? "сытый": "голодный");
    }
}
