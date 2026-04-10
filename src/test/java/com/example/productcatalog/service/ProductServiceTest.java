package com.example.productcatalog.service;

import com.example.productcatalog.dto.ProductDetailsResponse;
import com.example.productcatalog.dto.ProductRequest;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PriceCalculator priceCalculator;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProductShouldPersistAndReturnProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("Test");
        request.setBasePrice(BigDecimal.TEN);
        request.setAvailableQuantity(5);

        Product saved = new Product();
        saved.setId(1L);
        saved.setName("Test");
        saved.setBasePrice(BigDecimal.TEN);
        saved.setAvailableQuantity(5);

        when(productRepository.save(any(Product.class))).thenReturn(saved);

        Product result = productService.createProduct(request);
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getProductDetailsShouldReturnResponseWhenFound() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test");
        product.setBasePrice(BigDecimal.TEN);
        product.setAvailableQuantity(2);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(priceCalculator.calculatePrice(product)).thenReturn(BigDecimal.TEN);

        ProductDetailsResponse response = productService.getProductDetails(1L);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
    }
}
