package com.project.resources;

import java.math.BigDecimal;

public class Rope {
    private static final BigDecimal price = new BigDecimal(10);

    public static BigDecimal getPrice() {
        return price;
    }
}
