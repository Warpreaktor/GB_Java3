package ru.java3.HomeWork_03;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HW_03 {

    public static void main(String[] args) throws IOException {
//        fileReader();
//        fileAppender();
        bigFileReader();
    }

    //1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;

    /**
     * Обращаюсь к преподавателю.
     * Нижепредставленный код работает, но в консоль выводит символы типа: ￐ﾗ￐ﾰ￐ﾹ￐ﾺ￑ﾃ ￐ﾱ￑ﾀ￐ﾾ￑ﾁ￐ﾸ￐ﾻ￐ﾰ ￑ﾅ￐ﾾ￐ﾷ￑ﾏ￐ﾹ￐ﾺ￐ﾰ ￢ﾀﾔ
     * Скажите как можно при помощи NIO сделать вывод в консоль читабельным?
     */
    public static void fileReader() throws IOException {

        RandomAccessFile file = new RandomAccessFile("src/ru/java3/HomeWork_03/Агния Барто.txt", "r");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(15);
        int bytesRead = channel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = channel.read(buf);
        }
        channel.close();
        file.close();
    }

    /**
     * Вопрос преподавателю. Нижепредставленный код работает через БафферРидеры, но я не могу никак понять,
     * где именно происходит чтение в буфер? Есть ли к нему доступ? Я создаю переменную x и в нее пишется всего один
     * байт. Не понимаю как именно работает буфер.
     */
    //2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция:
    // ArrayList<InputStream> al = new ArrayList<>();
    // ... Enumeration<InputStream> e = Collections.enumeration(al);
    public static void fileAppender() throws IOException {
        List<InputStream> streams = new ArrayList<>();
        try (BufferedInputStream fis1 = new BufferedInputStream(
                new FileInputStream("src/ru/java3/HomeWork_03/Рассказ о неизвестном герое 1.txt"));
             BufferedInputStream fis2 = new BufferedInputStream(
                     new FileInputStream("src/ru/java3/HomeWork_03/Рассказ о неизвестном герое 2.txt"));
             BufferedInputStream fis3 = new BufferedInputStream(
                     new FileInputStream("src/ru/java3/HomeWork_03/Рассказ о неизвестном герое 3.txt"));
             BufferedInputStream fis4 = new BufferedInputStream(
                     new FileInputStream("src/ru/java3/HomeWork_03/Рассказ о неизвестном герое 4.txt"));
             BufferedInputStream fis5 = new BufferedInputStream(
                     new FileInputStream("src/ru/java3/HomeWork_03/Рассказ о неизвестном герое 5.txt"));

             FileOutputStream fosFinal = new FileOutputStream("src/ru/java3/HomeWork_03/Рассказ о неизвестном герое.txt")) {
            streams.add(fis1);
            streams.add(fis2);
            streams.add(fis3);
            streams.add(fis4);
            streams.add(fis5);
            int x;
            for (int i = 0; i < streams.size(); i++) {
                while ((x = streams.get(i).read()) != -1) {
                    fosFinal.write(x);
                }
            }
        }
    }

    public static void bigFileReader() {
        String testPath = "src/ru/java3/HomeWork_03/bigFile.txt";
        System.out.println("Укажите путь к файлу для чтения:");
        Scanner in = new Scanner(System.in);
        int bufferSize = 3600;//одна страница = 1800 char
        ArrayList<ByteArrayInputStream> book = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(in.nextLine())){
            BufferedInputStream bis = new BufferedInputStream( fis, bufferSize);
            byte[] buf;
            while ((buf = bis.readNBytes(bufferSize)).length > 0) {
                book.add(new ByteArrayInputStream(buf));

            }

        }catch (IOException e){
            System.err.println("Неверно указан путь!");
            System.exit(-1);
        }
        System.out.println("Файл готов к чтению. Всего " + book.size() + " страниц. Выберите страницу:");
        try {
            int pageNumber = in.nextInt();
            InputStreamReader is = new InputStreamReader(book.get(pageNumber));
            while (is.ready()) {
                System.out.print((char)is.read());
            }
        }
        catch (Exception e){
            System.err.println("Страница указана не верно");
            System.exit(-1);
        }
            in.close();
    }
}
