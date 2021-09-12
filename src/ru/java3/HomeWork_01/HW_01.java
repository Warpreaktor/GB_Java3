package ru.java3.HomeWork_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HW_01 <T>{

    public static void main(String[] args) {
        HW_01 hw_01 = new HW_01();
        hw_01.test1();
        hw_01.test2();
        hw_01.test3();
    }
    /**
     * 1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа.
     *
     * Метод принимает на вход List с любыми объектами и два int параметра - индексы объектов в массиве
     * которые нужно поменять местами
     */
    public void swap(List<T> array, int a, int b){
        T val = array.get(a);
        array.set(a, array.get(b));
        array.set(b, val);
    }
    //Перегрузка метода для работы с массивом
    public void swap(T[] array, int a, int b){
        T val = array[a];
        array[a] = array[b];
        array[b]  = val;
    }

    public void test1(){
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        HW_01 hw_01 = new HW_01();
        hw_01.swap(numbers, 3, 9);
        System.out.println("swap для List of Integers: " + numbers);

        Thread[] threads = new Thread[3];
        threads[0] = new Thread("Джон");
        threads[1] = new Thread("Мустафа");
        threads[2] = new Thread("Илья");

        hw_01.swap(threads, 0, 2);
        System.out.print("swap для array of Threads: ");
        Arrays.stream(threads).forEach(x -> {
            System.out.print(x.getName() + "; ");
        });
    }

    /**
     *2. Написать метод, который преобразует массив в ArrayList;
     */
        public ArrayList<T> toArrayList(T[] array){
            ArrayList list = new ArrayList();
            Arrays.stream(array).forEach(object -> {
                list.add(object);
            });
            return list;
        }

        public void test2(){
            HW_01 hw_01 = new HW_01();
            Thread[] threads = new Thread[3];
            threads[0] = new Thread("Джон");
            threads[1] = new Thread("Мустафа");
            threads[2] = new Thread("Илья");
            ArrayList list = hw_01.toArrayList(threads);
            System.out.println(list);
        }

    /**
     *Большая задача:
     * a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
     * b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
     * поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
     * c. Для хранения фруктов внутри коробки можете использовать ArrayList;
     * d. Сделать метод getWeight() который высчитывает вес коробки,
     * зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
     * e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
     * которую подадут в compare в качестве параметра, true - если их веса равны,
     * false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
     * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в
     * другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
     * соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
     * g. Не забываем про метод добавления фрукта в коробку.
     */

    public void test3(){
        Box<Orange> boxOfOrange1 = new Box();
        Box<Orange> boxOfOrange2 = new Box();
        Box<Apple> boxOfApple = new Box();

        for (int i = 0; i < 90; i++) {
            boxOfOrange1.put(new Orange());
        }
        for (int i = 0; i < 30; i++) {
            boxOfOrange2.put(new Orange());
        }
        for (int i = 0; i < 150; i++) {
            boxOfApple.put(new Apple());
        }

        System.out.println("Вес коробки с апельсинами: " + boxOfOrange1.getWeight());
        System.out.println("Вес коробки с апельсинами: " + boxOfApple.getWeight());
        System.out.println("Коробка с яблоками весит столько сколько и коробка с апельсинами? - " + boxOfApple.compare(boxOfOrange1));
        System.out.println(boxOfOrange1);
        System.out.println(boxOfOrange2);
        boxOfOrange1.pourOver(boxOfOrange2);
        System.out.println(boxOfOrange1);
        System.out.println(boxOfOrange2);

    }
}
