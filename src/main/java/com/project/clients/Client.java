package com.project.clients;

import com.project.Order.ClientOrder;

import java.math.BigDecimal;
import java.util.Random;

public class Client implements Runnable {
    private int id;
    private BigDecimal clientAccount;
    private BigDecimal initialClientAccount;
    private int maxRefusalCount;
    private int interval;
    private int refusalCount = 0;

    public Client(int id, BigDecimal clientAccount, int maxRefusalCount, int interval) {
        this.id = id;
        this.clientAccount = clientAccount;
        this.maxRefusalCount = maxRefusalCount;
        this.interval = interval;
        this.initialClientAccount = clientAccount;
    }

    public void run() {
        try {
            Thread.sleep(new Random().nextInt(5000));

            while (refusalCount != maxRefusalCount && clientAccount.compareTo(new BigDecimal(80)) >= 0) {
                if (!ClientOrder.makeOrder(this)) {
                    refusalCount++;
                }

                Thread.sleep(interval);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public BigDecimal getClientAccount() {
        return clientAccount;
    }

    public void takeFee(BigDecimal amount) {
        this.clientAccount = this.clientAccount.subtract(amount);
    }

    public BigDecimal getInitialClientAccount() {
        return initialClientAccount;
    }
}
