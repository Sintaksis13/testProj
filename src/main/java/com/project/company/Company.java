package com.project.company;

import java.math.BigDecimal;

public class Company {
    private static Company instance = null;

    private BigDecimal account = new BigDecimal(0);
    private int tradeCounter = 0;

    private int stoneSwordQuantity = 10;
    private int ironSwordQuantity = 10;
    private int stoneAxeQuantity = 10;
    private int ironAxeQuantity = 10;

    private int ropeQuantity = 0;
    private int woodQuantity = 0;
    private int ironQuantity = 0;
    private int stoneQuantity = 0;

    private int stoneSwordSaleCounter = 0;
    private int ironSwordSaleCounter = 0;
    private int stoneAxeSaleCounter = 0;
    private int ironAxeSaleCounter = 0;

    private int stoneSwordSaleAmount = 0;
    private int stoneAxeSaleAmount = 0;
    private int ironSwordSaleAmount = 0;
    private int ironAxeSaleAmount = 0;

    private Company() {

    }

    public synchronized static Company getInstance() {
        if (instance == null) {
            instance = new Company();
        }

        return instance;
    }

    private synchronized void incrementCounter() {
        tradeCounter++;
    }

    public synchronized void payMoney(BigDecimal amount) {
        account = account.add(amount);
    }

    public synchronized void takeFee(BigDecimal amount) {
        account = account.subtract(amount);
    }

    public synchronized void addResources(int ironQuantity, int stoneQuantity, int woodQuantity, int ropeQuantity) {
        this.ironQuantity += ironQuantity;
        this.stoneQuantity += stoneQuantity;
        this.woodQuantity += woodQuantity;
        this.ropeQuantity += ropeQuantity;
    }

    public synchronized void produceGoods(int ironQuantity, int stoneQuantity, int woodQuantity, int ropeQuantity) {
        this.ironQuantity -= ironQuantity;
        this.stoneQuantity -= stoneQuantity;
        this.woodQuantity -= woodQuantity;
        this.ropeQuantity -= ropeQuantity;

        this.ironAxeQuantity++;
        this.ironSwordQuantity++;
        this.stoneAxeQuantity++;
        this.stoneSwordQuantity++;
    }

    public void printState() {
        System.out.println("Счет компании: " + account
                            + "\nКоличество сделок: " + tradeCounter
                            + "\nКоличество материалов на складе:"
                            + "\n\tЖелезо - " + ironQuantity
                            + "\n\tКамень - " + stoneQuantity
                            + "\n\tДерево - " + woodQuantity
                            + "\n\tВеревки - " + ropeQuantity);
    }

    public synchronized void buyIronSword(int quantity) {
        ironSwordQuantity -= quantity;
        ironSwordSaleCounter++;
        ironSwordSaleAmount += quantity;
        incrementCounter();
    }

    public synchronized void buyStoneSword(int quantity) {
        stoneSwordQuantity -= quantity;
        stoneSwordSaleCounter++;
        stoneSwordSaleAmount += quantity;
        incrementCounter();
    }

    public synchronized void buyIronAxe(int quantity) {
        ironAxeQuantity -= quantity;
        ironAxeSaleCounter++;
        ironAxeSaleAmount += quantity;
        incrementCounter();
    }

    public synchronized void buyStoneAxe(int quantity) {
        stoneAxeQuantity -= quantity;
        stoneAxeSaleCounter++;
        stoneAxeSaleAmount += quantity;
        incrementCounter();
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public int getStoneSwordQuantity() {
        return stoneSwordQuantity;
    }

    public int getIronSwordQuantity() {
        return ironSwordQuantity;
    }

    public int getStoneAxeQuantity() {
        return stoneAxeQuantity;
    }


    public int getIronAxeQuantity() {
        return ironAxeQuantity;
    }

    public int getRopeQuantity() {
        return ropeQuantity;
    }

    public synchronized int getWoodQuantity() {
        return woodQuantity;
    }

    public int getIronQuantity() {
        return ironQuantity;
    }

    public int getStoneQuantity() {
        return stoneQuantity;
    }

    public int getStoneSwordSaleCounter() {
        return stoneSwordSaleCounter;
    }

    public int getIronSwordSaleCounter() {
        return ironSwordSaleCounter;
    }

    public int getStoneAxeSaleCounter() {
        return stoneAxeSaleCounter;
    }

    public int getIronAxeSaleCounter() {
        return ironAxeSaleCounter;
    }

    public int getStoneSwordSaleAmount() {
        return stoneSwordSaleAmount;
    }

    public int getStoneAxeSaleAmount() {
        return stoneAxeSaleAmount;
    }

    public int getIronSwordSaleAmount() {
        return ironSwordSaleAmount;
    }

    public int getIronAxeSaleAmount() {
        return ironAxeSaleAmount;
    }
}
