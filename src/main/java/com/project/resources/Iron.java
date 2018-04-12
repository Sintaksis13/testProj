package com.project.resources;

import java.math.BigDecimal;

public class Iron {
    private static final BigDecimal price = new BigDecimal(50);

    public static BigDecimal getPrice() {
        return price;
    }
}
