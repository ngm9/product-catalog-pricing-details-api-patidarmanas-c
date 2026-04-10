package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StandardPriceCalculator implements PriceCalculator {

    @Override
    public BigDecimal calculatePrice(Product product) {
        if (product == null || product.getBasePrice() == null) {
            return BigDecimal.ZERO;
        }
        return product.getBasePrice();
    }
}
