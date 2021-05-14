package ru.geekbrains;

import ru.geekbrains.testcalsses.Test1;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Tester.start(Test1.class);
    }
}
