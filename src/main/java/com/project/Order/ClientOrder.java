package com.project.Order;

import com.project.Wrappers.GoodsPrice;
import com.project.clients.Client;
import com.project.company.Company;

import java.math.BigDecimal;
import java.util.Random;

public class ClientOrder {
    private static BigDecimal orderAmount;
    private static Company company = Company.getInstance();

    public synchronized static boolean makeOrder(Client client) {
        int quantity = getRandomValue(5) + 1;

        switch (getRandomValue(4)) {
            case 0: {
                orderAmount = GoodsPrice.getIronSwordPrice().multiply(new BigDecimal(quantity));

                if (company.getIronSwordQuantity() >= quantity && client.getClientAccount().compareTo(orderAmount) >= 0) {
                    company.buyIronSword(quantity);
                    client.takeFee(orderAmount);
                    company.payMoney(orderAmount);
                    return true;
                } else {
                    return false;
                }
            }

            case 1: {
                orderAmount = GoodsPrice.getStoneSwordPrice().multiply(new BigDecimal(quantity));

                if (company.getStoneSwordQuantity() >= quantity && client.getClientAccount().compareTo(orderAmount) >= 0) {
                    company.buyStoneSword(quantity);
                    client.takeFee(orderAmount);
                    company.payMoney(orderAmount);
                    return true;
                } else {
                    return false;
                }
            }

            case 2: {
                orderAmount = GoodsPrice.getIronAxePrice().multiply(new BigDecimal(quantity));

                if (company.getIronAxeQuantity() >= quantity && client.getClientAccount().compareTo(orderAmount) >= 0) {
                    company.buyIronAxe(quantity);
                    client.takeFee(orderAmount);
                    company.payMoney(orderAmount);
                    return true;
                } else {
                    return false;
                }
            }

            case 3: {
                orderAmount = GoodsPrice.getStoneAxePrice().multiply(new BigDecimal(quantity));

                if (company.getStoneAxeQuantity() >= quantity && client.getClientAccount().compareTo(orderAmount) >= 0) {
                    company.buyStoneAxe(quantity);
                    client.takeFee(orderAmount);
                    company.payMoney(orderAmount);
                    return true;
                } else {
                    return false;
                }
            }

            default: return false;
        }
    }

    private static int getRandomValue(int bound) {
        return new Random().nextInt(bound);
    }

}
