package ru.java3.HomeWork_05.Race;

public class Road extends Stage {
    private String name;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public Road(int length, String name) {
        this.name = name;
        this.length = length;
        this.description = name + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.err.println(ANSI_BLUE + c.getName() + " успешно проходит " + name + ANSI_BLUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
