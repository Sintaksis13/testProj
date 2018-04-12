package com.project.Order;

import com.project.Wrappers.GoodsPrice;
import com.project.clients.Client;
import com.project.company.Company;

import java.math.BigDecimal;
import java.util.Random;

public class ClientOrder {
    private BigDecimal orderAmount;
    private Company company = Company.getInstance();
    private int choice = new Random().nextInt(4);

    public synchronized boolean makeOrder(Client client) {
        int quantity = new Random().nextInt(5) + 1;

        switch (choice) {
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
}
