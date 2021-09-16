package ru.java3.HomeWork_05.Race;

import java.util.Random;

import static ru.java3.HomeWork_05.Race.HW_05.finishCounter;
import static ru.java3.HomeWork_05.Race.HW_05.readyCounter;

public class Car implements Runnable {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private static int CARS_COUNT;
    private static String[] names = {"Жульен" , "Дидье", "Жослен", "Жан-Марк", "Жан-Франсуа", "Николя",
            "Стефан", "Мишель", "Бенжамен"};
    private static String[] secondName = {"Дикий", "Лихой", "Безумный", "Гонщик", "Молния", "Пуля",
            "Мотор", "Чертяга", "Таксист"};
    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    private void setRandomName(){
        Random random = new Random();
        name = names[random.nextInt(names.length)] + " " + secondName[random.nextInt(secondName.length)] + " #" + CARS_COUNT;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.setRandomName();
    }
    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            readyCounter.countDown();
            go();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void go(){
        try {
            readyCounter.await();
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (finishCounter.getCount() == CARS_COUNT){
            System.err.println(ANSI_GREEN + this.getName() + " Побеждает в этой гонке!!!" + ANSI_RESET);
        }
        HW_05.finishCounter.countDown();
    }
}