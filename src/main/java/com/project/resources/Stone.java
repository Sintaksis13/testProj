package com.project.resources;

import java.math.BigDecimal;

public class Stone {
    private static final BigDecimal price = new BigDecimal(20);

    public static BigDecimal getPrice() {
        return price;
    }
}
