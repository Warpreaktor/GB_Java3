package ru.java3.HomeWork_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<E> {
    private ArrayList<E> fruits;

    private double appleWeight = 1.0;
    private double orangeWeight = 1.5;

    public Box() {
        this.fruits = new ArrayList<E>();
    }

    public void put(E fruit){
        this.fruits.add(fruit);
    }

    public double getWeight(){
        if (fruits.size() < 1){
            return 0;
        }
        if (fruits.get(0).getClass() == Apple.class)
        return fruits.size() * appleWeight;
        if (fruits.get(0).getClass() == Orange.class){
            return fruits.size() * orangeWeight;
        }
        return -1.0f;
    }

    public boolean compare(Box b){
        if (Double.compare(this.getWeight(), b.getWeight()) == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Метод позволят переложить ВСЕ фрукты из одной коробки в другую коробку с тем же сортом фруктов.
     * Метод принимает коробку в которую нужно пересыпать.
     */
    public void pourOver(Box<E> target){
        for (int i = fruits.size() - 1; i >= 0; i--) {
            target.put(fruits.get(i));
            fruits.remove(i);
        }
    }


    @Override
    public String toString() {
        return "В этой коробке " + fruits.size() + " фрукт\\ов";
    }
}
