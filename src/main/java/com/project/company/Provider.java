package com.project.company;

import com.project.Start;
import com.project.resources.Iron;
import com.project.resources.Rope;
import com.project.resources.Stone;
import com.project.resources.Wood;

import java.math.BigDecimal;
import java.util.Random;

public class Provider implements Runnable {
    private static final int MIN_IRON_VALUE_FOR_20_GOODS = Producer.IRON_QUANTITY_FOR_ALL_TYPE_OF_GOODS * 20;
    private static final int MIN_STONE_VALUE_FOR_20_GOODS = Producer.STONE_QUANTITY_FOR_ALL_TYPE_OF_GOODS * 20;
    private static final int MIN_WOOD_VALUE_FOR_20_GOODS = Producer.WOOD_QUANTITY_FOR_ALL_TYPE_OF_GOODS * 20;
    private static final int MIN_ROPE_VALUE_FOR_20_GOODS = Producer.ROPE_QUANTITY_FOR_ALL_TYPE_OF_GOODS * 20;

    private double interval;
    private static Company company = Company.getInstance();

    public Provider(int interval) {
        this.interval = interval / 2.0;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(1000));
            while(true) {
                Thread.sleep((long) interval);

                if (checkResources() && checkCompanyAmount()) {
                    deliverOrder();
                }

                if (Start.checkClients()) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static synchronized boolean checkCompanyAmount() {
        BigDecimal companyAccount = Company.getInstance().getAccount();

        return companyAccount.compareTo(calculateDeliveryPrice()) >= 0;
    }

    private static synchronized boolean checkResources() {
        return company.getIronQuantity() < MIN_IRON_VALUE_FOR_20_GOODS
                || company.getStoneQuantity() < MIN_STONE_VALUE_FOR_20_GOODS
                || company.getWoodQuantity() < MIN_WOOD_VALUE_FOR_20_GOODS
                || company.getRopeQuantity() < MIN_ROPE_VALUE_FOR_20_GOODS;
    }

    private static BigDecimal calculateDeliveryPrice() {
        BigDecimal value = new BigDecimal(0);

        value = value.add(Iron.getPrice().multiply(new BigDecimal(MIN_IRON_VALUE_FOR_20_GOODS)));
        value = value.add(Stone.getPrice().multiply(new BigDecimal(MIN_STONE_VALUE_FOR_20_GOODS)));
        value = value.add(Wood.getPrice().multiply(new BigDecimal(MIN_WOOD_VALUE_FOR_20_GOODS)));
        value = value.add(Rope.getPrice().multiply(new BigDecimal(MIN_ROPE_VALUE_FOR_20_GOODS)));

        return value;
    }

    private void deliverOrder() {
        company.takeFee(calculateDeliveryPrice());
        company.addResources(MIN_IRON_VALUE_FOR_20_GOODS, MIN_STONE_VALUE_FOR_20_GOODS,
                MIN_WOOD_VALUE_FOR_20_GOODS, MIN_ROPE_VALUE_FOR_20_GOODS);
    }
}
