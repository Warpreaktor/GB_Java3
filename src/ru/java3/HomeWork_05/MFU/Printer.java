package ru.java3.HomeWork_05.MFU;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

public class Printer {
    private ArrayBlockingQueue<Vector<InputStream>> printQueue;
    private int queueCapacity = 10;
    int bufferSize = 1024;

    public Printer(){
        printQueue = new ArrayBlockingQueue(queueCapacity, true);
    }

    public void print(){
        Vector<InputStream> vector = getNext();
        InputStreamReader isr;
        for (InputStream is : vector) {
            try {
                isr = new InputStreamReader(is);
                int ch;
                while ((ch = isr.read()) > 0){
                    System.out.print((char)ch);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Vector<InputStream> getNext(){
        return printQueue.poll();
    }

    public void toPrintQueue(Vector<InputStream> inputStream){
        try {
            printQueue.put(inputStream);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
