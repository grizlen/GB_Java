package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    action(new Animal[]{
                new Dog("Бобик"),
                new Cat("Барсик"),
                new Dog("Тузик"),
                new Cat("Тишка"),
                new Dog("Палкан"),
                new Cat("Леопольд"),
        });
    }
    public static void action(Animal[] animals){
        Random random = new Random();
        int numDogs = 0, numCats = 0;
        for (Animal pet: animals){
            if (pet instanceof Dog) {
                pet.run(random.nextInt(1000));
                pet.swim(random.nextInt(20));
                numDogs++;
            }
            else{
                pet.run(random.nextInt(400));
                pet.swim(random.nextInt(1));
                numCats++;
            }
        }
        System.out.printf("Всего животных %d из них собак %d и котов %d\n", animals.length, numDogs, numCats);
    }
}
