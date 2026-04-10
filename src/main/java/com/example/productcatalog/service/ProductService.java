package com.example.productcatalog.service;

import com.example.productcatalog.dto.ProductDetailsResponse;
import com.example.productcatalog.dto.ProductRequest;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.repository.ProductRepository;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceCalculator priceCalculator;

    public Product createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setBasePrice(request.getBasePrice());
        product.setAvailableQuantity(request.getAvailableQuantity());
        return productRepository.save(product);
    }

    public ProductDetailsResponse getProductDetails(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return null;
        }
        Product product = optionalProduct.get();
        return buildDetailsResponse(product);
    }

    public CompletableFuture<ProductDetailsResponse> getProductDetailsAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> getProductDetails(id));
    }

    private ProductDetailsResponse buildDetailsResponse(Product product) {
        ProductDetailsResponse response = new ProductDetailsResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setAvailableQuantity(product.getAvailableQuantity());
        response.setAvailable(product.getAvailableQuantity() > 0);
        response.setPrice(priceCalculator.calculatePrice(product));
        return response;
    }
}
