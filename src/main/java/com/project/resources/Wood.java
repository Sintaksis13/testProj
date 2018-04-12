package com.project.resources;

import java.math.BigDecimal;

public class Wood {
    private static final BigDecimal price = new BigDecimal(30);

    public static BigDecimal getPrice() {
        return price;
    }
}
