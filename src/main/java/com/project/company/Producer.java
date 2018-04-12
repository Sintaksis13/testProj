package com.project.company;

import com.project.Start;

public class Producer implements Runnable {
    public static final int IRON_QUANTITY_FOR_ALL_TYPE_OF_GOODS = 3;
    public static final int STONE_QUANTITY_FOR_ALL_TYPE_OF_GOODS = 3;
    public static final int WOOD_QUANTITY_FOR_ALL_TYPE_OF_GOODS = 6;
    public static final int ROPE_QUANTITY_FOR_ALL_TYPE_OF_GOODS = 4;

    private Company company = Company.getInstance();

    public void run() {
        while (true) {
            if (checkResources()) {
                company.produceGoods(IRON_QUANTITY_FOR_ALL_TYPE_OF_GOODS, STONE_QUANTITY_FOR_ALL_TYPE_OF_GOODS,
                        WOOD_QUANTITY_FOR_ALL_TYPE_OF_GOODS, ROPE_QUANTITY_FOR_ALL_TYPE_OF_GOODS);
            }

            if (Start.checkClients()) {
                break;
            }
        }
    }

    private boolean checkResources() {
        return (company.getIronQuantity() >= IRON_QUANTITY_FOR_ALL_TYPE_OF_GOODS)
                && (company.getStoneQuantity() >= STONE_QUANTITY_FOR_ALL_TYPE_OF_GOODS)
                && (company.getWoodQuantity() >= WOOD_QUANTITY_FOR_ALL_TYPE_OF_GOODS)
                && (company.getRopeQuantity() >= ROPE_QUANTITY_FOR_ALL_TYPE_OF_GOODS);
    }

}
