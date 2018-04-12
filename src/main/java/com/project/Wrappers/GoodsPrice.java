package com.project.Wrappers;

import com.project.goods.IronAxe;
import com.project.goods.IronSword;
import com.project.goods.StoneAxe;
import com.project.goods.StoneSword;
import com.project.resources.Iron;
import com.project.resources.Rope;
import com.project.resources.Stone;
import com.project.resources.Wood;

import java.math.BigDecimal;

public abstract class GoodsPrice {
    private static final BigDecimal PROFIT = new BigDecimal(1.1); //наценка фирмы, чтобы получать выгоду

    public static BigDecimal getIronAxePrice() {
        BigDecimal value = BigDecimal.ZERO;

        value = value.add(new BigDecimal(IronAxe.getRequiredIron()).multiply(Iron.getPrice()));
        value = value.add(new BigDecimal(IronAxe.getRequiredWood()).multiply(Wood.getPrice()));
        value = value.add(new BigDecimal(IronAxe.getRequiredRope()).multiply(Rope.getPrice()));
        value = value.multiply(PROFIT);

        return value;
    }

    public static BigDecimal getIronSwordPrice() {
        BigDecimal value = BigDecimal.ZERO;

        value = value.add(new BigDecimal(IronSword.getRequiredIron()).multiply(Iron.getPrice()));
        value = value.add(new BigDecimal(IronSword.getRequiredWood()).multiply(Wood.getPrice()));
        value = value.add(new BigDecimal(IronSword.getRequiredRope()).multiply(Rope.getPrice()));
        value = value.multiply(PROFIT);

        return value;
    }

    public static BigDecimal getStoneAxePrice() {
        BigDecimal value = BigDecimal.ZERO;

        value = value.add(new BigDecimal(StoneAxe.getRequiredStone()).multiply(Stone.getPrice()));
        value = value.add(new BigDecimal(StoneAxe.getRequiredWood()).multiply(Wood.getPrice()));
        value = value.add(new BigDecimal(StoneAxe.getRequiredRope()).multiply(Rope.getPrice()));
        value = value.multiply(PROFIT);

        return value;
    }

    public static BigDecimal getStoneSwordPrice() {
        BigDecimal value = BigDecimal.ZERO;

        value = value.add(new BigDecimal(StoneSword.getRequiredStone()).multiply(Stone.getPrice()));
        value = value.add(new BigDecimal(StoneSword.getRequiredWood()).multiply(Wood.getPrice()));
        value = value.add(new BigDecimal(StoneSword.getRequiredRope()).multiply(Rope.getPrice()));
        value = value.multiply(PROFIT);

        return value;
    }
}
