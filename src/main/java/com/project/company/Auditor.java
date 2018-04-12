package com.project.company;

import com.project.Start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Auditor implements Runnable {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void run() {
        while (true) {
            System.out.println("Список команд: \n\t\"Отчет\" - получить отчет о состоянии фирмы." +
                    "\n\t\"Выйти\" - выйти из программы.");
            String s = "";

            try {
                s = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch(s) {
                case "Отчет": {
                    Company.getInstance().printState();
                    break;
                }
                case "Выйти": {
                    System.exit(0);
                }
                default: {
                    System.out.println("Вы ввели неизвестную команду!");
                }
            }

            if (Start.checkClients()) {
                break;
            }
    }
}
}
