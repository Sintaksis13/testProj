package com.project.company;

import com.project.Start;
import com.project.resources.Iron;
import com.project.resources.Rope;
import com.project.resources.Stone;
import com.project.resources.Wood;

import java.math.BigDecimal;

public class Provider implements Runnable {
    private static final int MIN_IRON_VALUE_FOR_10_GOODS = Producer.IRON_QUANTITY_FOR_ALL_TYPE_OF_GOODS * 10;
    private static final int MIN_STONE_VALUE_FOR_10_GOODS = Producer.STONE_QUANTITY_FOR_ALL_TYPE_OF_GOODS * 10;
    private static final int MIN_WOOD_VALUE_FOR_10_GOODS = Producer.WOOD_QUANTITY_FOR_ALL_TYPE_OF_GOODS * 10;
    private static final int MIN_ROPE_VALUE_FOR_10_GOODS = Producer.ROPE_QUANTITY_FOR_ALL_TYPE_OF_GOODS * 10;

    private double interval;
    private Company company = Company.getInstance();

    public Provider(int interval) {
        this.interval = interval / 2.0;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (checkResources() && checkCompanyAmount()) {
                deliverOrder();
            }

            if (Start.checkClients()) {
                break;
            }
        }

        System.out.println("\nВСЕ КЛИЕНТЫ ЗАКОНЧИЛИ СВОЮ РАБОТУ!");
        Start.printFinalState();
    }

    private boolean checkCompanyAmount() {
        BigDecimal companyAccount = Company.getInstance().getAccount();

        return companyAccount.compareTo(calculateDeliveryPrice()) >= 0;
    }

    private boolean checkResources() {
        return company.getIronQuantity() < MIN_IRON_VALUE_FOR_10_GOODS
                || company.getStoneQuantity() < MIN_STONE_VALUE_FOR_10_GOODS
                || company.getWoodQuantity() < MIN_WOOD_VALUE_FOR_10_GOODS
                || company.getRopeQuantity() < MIN_ROPE_VALUE_FOR_10_GOODS;
    }

    private BigDecimal calculateDeliveryPrice() {
        BigDecimal value = new BigDecimal(0);

        value = value.add(Iron.getPrice().multiply(new BigDecimal(MIN_IRON_VALUE_FOR_10_GOODS)));
        value = value.add(Stone.getPrice().multiply(new BigDecimal(MIN_STONE_VALUE_FOR_10_GOODS)));
        value = value.add(Wood.getPrice().multiply(new BigDecimal(MIN_WOOD_VALUE_FOR_10_GOODS)));
        value = value.add(Rope.getPrice().multiply(new BigDecimal(MIN_ROPE_VALUE_FOR_10_GOODS)));

        return value;
    }

    private void deliverOrder() {
        company.takeFee(calculateDeliveryPrice());
        company.addResources(MIN_IRON_VALUE_FOR_10_GOODS, MIN_STONE_VALUE_FOR_10_GOODS,
                MIN_WOOD_VALUE_FOR_10_GOODS, MIN_ROPE_VALUE_FOR_10_GOODS);
    }
}
