package ru.java3.HomeWork_04;

public class HW_04 {

    public static void main(String[] args) {
        // Создать три потока, каждый из которых выводит
        // определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
        /**
         * Вопрос к преподавателю:
         * Честно говоря, я не понимаю зачем в этой задаче использовать что-то кроме join.
         * Буду признателен за дополнительное разъяснение.
         */
        Thread A = getThread("A");
        Thread B = getThread("B");
        Thread C = getThread("C");
        for (int i = 0; i < 5; i++) {
            try {
                A.run();
                A.join();
                B.run();
                B.join();
                C.run();
                C.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static Thread getThread(String message){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print(message);
            }
        });
        return thread;
    }
}
