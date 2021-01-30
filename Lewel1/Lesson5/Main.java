package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Person[] persons = {
                new Person("Иванов Иван Иванович", "директор", "boss@gmail.com",
                        "+7 902 12 13 123", 120000f, 55),
                new Person("Петров Петр Петрович", "грузчик", "halc@gmail.com",
                        "+7 902 22 23 223", 25000f, 40),
                new Person("Герасимов Герасим Герасимович", "охранник", "sequrity@gmail.com",
                        "+7 902 32 33 323", 12792f, 20),
                new Person("Александрова Александра Александровна", "бухгалтер", "minfin@gmail.com",
                        "+7 902 42 43 423", 40000f, 50),
                new Person("Евгеньева Евгения Евгеньевна", "уборщица", "cleanboss@gmail.com",
                        "+7 902 02 02 002", 90000f, 80)
                };
        for (Person person: persons)
            if (person.getAge() >= 40) person.print();
    }
}
