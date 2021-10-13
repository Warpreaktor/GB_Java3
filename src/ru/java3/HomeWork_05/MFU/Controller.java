package ru.java3.HomeWork_05.MFU;


import java.io.InputStream;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

public class Controller {

    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        Printer printer = new Printer();
        Copier copier = new Copier();
        Vector<InputStream> document = scanner.scan("src/ru/java3/HomeWork_05/MFU/documents/док1.txt");
        printer.toPrintQueue(document);
        printer.print();
        copier.copy("O:\\Develop\\GB_Java3\\src\\ru\\java3\\HomeWork_05\\MFU\\documents\\док2.txt");

        ListIterator itr = document.listIterator();

    }

}
