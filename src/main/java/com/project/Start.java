package com.project;

import com.project.Wrappers.GoodsPrice;
import com.project.clients.Client;
import com.project.company.*;
import com.project.resources.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Start {
    private static Company company = Company.getInstance();
    private static Map<Integer, Thread> clients = null;
    private static Map<Integer, Thread> providers = null;

    private static Map<Integer, Client> clientMap = null;
    private static Map<Integer, Provider> providerMap = null;

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static BigDecimal initialCompanyAccount = null;
    private static int clientCount;
    private static BigDecimal initialClientAccount = null;
    private static int maxRefusalCount;
    private static int providerCount;
    private static int intervalTime;

    public static void main(String[] args) {
        fillUpValues();

        Company company = Company.getInstance();
        company.setAccount(initialCompanyAccount);

        clients = new ConcurrentHashMap<>(clientCount);
        providers = new ConcurrentHashMap<>(providerCount);

        clientMap = new HashMap<>(clientCount);
        providerMap = new HashMap<>(providerCount);

        startThreads();
    }

    public static boolean checkClients() {
        int counter = 0;

        for (int i = 0; i < clients.size(); i++) {
            if (!clients.get(i).isAlive()) {
                counter++;
            }
        }

        return counter == clients.size();
    }

    private static void fillUpValues() {
        try {
            System.out.print("Введите начальную сумму денег на счету компании: ");
            initialCompanyAccount = new BigDecimal(Double.parseDouble(reader.readLine()));

            System.out.print("\nВведите начальную сумму денег на счету клиентов: ");
            initialClientAccount = new BigDecimal(Double.parseDouble(reader.readLine()));

            System.out.print("\nВведите количество одновременно работающих заказчиков: ");
            clientCount = Integer.parseInt(reader.readLine());

            System.out.print("\nВведите максимальное количество отказов заказчику: ");
            maxRefusalCount = Integer.parseInt(reader.readLine());

            System.out.print("\nВведите количество одновременно работающих поставщиков: ");
            providerCount = Integer.parseInt(reader.readLine());

            System.out.print("\nВведите интервал между обращениями к серверу поставщика(в секундах): ");
            intervalTime = Integer.parseInt(reader.readLine()) * 1000;
        } catch (IOException e) {
            System.err.print("Вы ввели некорректное значение! " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private static void fillUpThreads() {
        for (int i = 0; i < clientCount; i++) {
            clientMap.put(i, new Client(i, initialClientAccount, maxRefusalCount, intervalTime));
        }

        for (int i = 0; i < providerCount; i++) {
            providerMap.put(i, new Provider(intervalTime));
        }
    }

    private static void startThreads() {
        fillUpThreads();

        for (int i = 0; i < clientCount; i++) {
            clients.put(i, new Thread(clientMap.get(i)));
            clients.get(i).start();
        }

        for (int i = 0; i < providerCount; i++) {
            providers.put(i, new Thread(providerMap.get(i)));
            providers.get(i).start();
        }

        Thread threadAuditor = new Thread(new Auditor());
        threadAuditor.start();

        Thread threadProducer = new Thread(new Producer());
        threadProducer.start();
    }

    public static void printFinalState() {
        System.out.println("\nСтатистика заказов по клиентам с суммарным итогом:");
        System.out.println("Идентификатор | Сумма заказов | Остаток средств");
        for (int i = 0; i < clientCount; i++) {
            System.out.printf("%13d | %13.2f | %15.2f%n", clientMap.get(i).getId(),
                    clientMap.get(i).getInitialClientAccount().subtract(clientMap.get(i).getClientAccount()),
                    clientMap.get(i).getClientAccount());
        }

        System.out.println("\nСтатистика заказов по товарам с суммарным итогом:");
        System.out.println(" Наименование  | Кол-во заказов | Кол-во проданного товара | Сумма проданного");
        System.out.printf("Железный меч   | %14d | %24d | %16.2f%n", company.getIronSwordSaleCounter(),
                company.getIronSwordSaleAmount(),
                GoodsPrice.getIronSwordPrice().multiply(new BigDecimal(company.getIronSwordSaleAmount())));
        System.out.printf("Железный топор | %14d | %24d | %16.2f%n", company.getIronAxeSaleCounter(),
                company.getIronAxeSaleAmount(),
                GoodsPrice.getIronAxePrice().multiply(new BigDecimal(company.getIronAxeSaleAmount())));
        System.out.printf("Каменный меч   | %14d | %24d | %16.2f%n", company.getStoneSwordSaleCounter(),
                company.getStoneSwordSaleAmount(),
                GoodsPrice.getStoneSwordPrice().multiply(new BigDecimal(company.getStoneSwordSaleAmount())));
        System.out.printf("Каменный топор | %14d | %24d | %16.2f%n", company.getStoneAxeSaleCounter(),
                company.getStoneAxeSaleAmount(),
                GoodsPrice.getStoneAxePrice().multiply(new BigDecimal(company.getStoneAxeSaleAmount())));

        System.out.println("Остатки материалов на складе с итоговой стоимостью:");
        System.out.println("Наименование | Кол-во материалов | Стоимость");
        System.out.printf("   Железо    | %17d | %9.2f%n", company.getIronQuantity(),
                Iron.getPrice().multiply(new BigDecimal(company.getIronQuantity())));
        System.out.printf("   Камень    | %17d | %9.2f%n", company.getStoneQuantity(),
                Stone.getPrice().multiply(new BigDecimal(company.getStoneQuantity())));
        System.out.printf("   Дерево    | %17d | %9.2f%n", company.getWoodQuantity(),
                Wood.getPrice().multiply(new BigDecimal(company.getWoodQuantity())));
        System.out.printf("   Веревка   | %17d | %9.2f%n", company.getRopeQuantity(),
                Rope.getPrice().multiply(new BigDecimal(company.getRopeQuantity())));

        System.out.println("Остаток на счету фирмы: " + company.getAccount());
    }
}
