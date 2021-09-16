package ru.java3.HomeWork_05.MFU;

import java.io.*;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Scanner {
    public static Vector<InputStream> memory;
    private InputStream inputStream;
    private int buffSize = 1024;

    private String path;
    private java.util.Scanner scanner;
    private final Lock locker = new ReentrantLock();

    public Scanner(){
    }

    /**
     * Укажите путь к файлу для сканирования.
     * Может выполняться только в один поток.
     */
    public Vector<InputStream> scan(String path){
        memory = new Vector<>();
        try {
            locker.lock();
            inputStream = new FileInputStream(path);
            scanner = new java.util.Scanner(inputStream);
            while (inputStream.available() > 0){
                memory.add(new ByteArrayInputStream(inputStream.readNBytes(buffSize)));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
        }
        catch (IOException e){
            System.err.println("Не удалось прочитать файл");
        }
        finally {
            locker.unlock();
        }
        return getMemory();
    }

    public static Vector<InputStream> getMemory() {
        Vector<InputStream> copyMemory = new Vector<>();
        copyMemory.addAll(memory);
        return copyMemory;
    }
}
