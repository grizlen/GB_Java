package com.company;

public abstract class Animal {
    protected String name;
    protected int runLimit = 0, swimLimit = 0;
    public Animal(String name) {
        this.name = name;
    }
    public abstract String presentStr();
    public void run(int value){
        if (value < 0)
            System.out.printf("для %s задний ход не предусмотрен\n", presentStr());
        else if (value == 0)
            System.out.printf("%s ни куда не бежит\n", presentStr());
        else if (value > runLimit)
            System.out.printf("%s пробежал %d метров и устал\n", presentStr(), runLimit);
        else
            System.out.printf("%s пробежал %d метров\n", presentStr(), value);
    }
    public void swim(int value){
        if (swimLimit == 0)
            System.out.printf("%s утонул\n", presentStr());
        else if (value < 0)
            System.out.printf("для %s задний ход не предусмотрен\n", presentStr());
        else if (value == 0)
            System.out.printf("%s ни куда не плывет\n", presentStr());
        else if (value > swimLimit)
            System.out.printf("%s проплыл %d метров и утонул\n", presentStr(), swimLimit);
        else
            System.out.printf("%s проплыл %d метров\n", presentStr(), value);
    }
}
