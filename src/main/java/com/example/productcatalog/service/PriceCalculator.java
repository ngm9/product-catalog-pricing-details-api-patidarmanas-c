package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;

import java.math.BigDecimal;

public interface PriceCalculator {

    BigDecimal calculatePrice(Product product);
}
