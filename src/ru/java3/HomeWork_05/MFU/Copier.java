package ru.java3.HomeWork_05.MFU;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class Copier {

    public void copy(String path){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int num = 0;
                    StringBuilder newPath = new StringBuilder("O:\\Develop\\GB_Java3\\src\\ru\\java3\\HomeWork_05\\MFU\\documents\\"
                            + "копия-" + num + Path.of(path).getFileName());
                    while (Files.exists(Path.of(String.valueOf(newPath))) || num > 1000){
                        num++;
                        newPath.replace(0, newPath.length(),
                                "O:\\Develop\\GB_Java3\\src\\ru\\java3\\HomeWork_05\\MFU\\documents\\"
                                + "копия-" + num + Path.of(path).getFileName());
                    }
                    Files.copy(Path.of(path), Path.of(String.valueOf(newPath)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

}
