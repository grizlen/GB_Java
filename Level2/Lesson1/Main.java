package ru.geekbrains.hw2_1;

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Comand 1",
            new Human(500, 150),
            new Robot(2000, 150),
            new Human(300, 100),
            new Human(1600, 150));
        Course course = new Course(new Barrier[]{
            new Track(1000),
            new Wall(120),
            new Track(100),
        });
        team.showInfo();
        course.doIt(team);
        team.showRsults();
    }
}
