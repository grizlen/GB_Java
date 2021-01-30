package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        action();
    }
    public static void action(){
        Random random = new Random();
        Cat[] cats = {
                new Cat("Барсик", (random.nextInt(5) + 1) * 5),
                new Cat("Мурзик", (random.nextInt(5) + 1) * 5),
                new Cat("Тишка", (random.nextInt(5) + 1) * 5),
                new Cat("Мишка", (random.nextInt(5) + 1) * 5),
                new Cat("Матроскин", (random.nextInt(5) + 1) * 5)
        };
        Plate plate = new Plate(50);
        for (Cat c: cats){
            plate.info();
            c.eat(plate);
            c.info();
        }
        // пункт задания 6. добавил чтобы idea не ругалась на неиспольуемый метод
        plate.increaseFood(50);
        plate.info();
    }
}
