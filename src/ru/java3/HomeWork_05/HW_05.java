package ru.java3.HomeWork_05;

import java.util.concurrent.CountDownLatch;

public class HW_05 {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static final int CARS_COUNT = 4;
    public static final CountDownLatch readyCounter = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch finishCounter = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        System.err.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60, "Грязь"), new Tunnel(80, 4), new Road(40, "Лес"));
        Car[] cars = new Car[CARS_COUNT];


        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        readyCounter.await();
        System.err.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        finishCounter.await();
        System.err.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
