package com.example.productcatalog.controller;

import com.example.productcatalog.dto.ProductDetailsResponse;
import com.example.productcatalog.dto.ProductRequest;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        Product created = productService.createProduct(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ProductDetailsResponse> getProductDetails(@PathVariable Long id) {
        ProductDetailsResponse response = productService.getProductDetails(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/details-async")
    public CompletableFuture<ResponseEntity<ProductDetailsResponse>> getProductDetailsAsync(@PathVariable Long id) {
        return productService.getProductDetailsAsync(id)
                .thenApply(response -> {
                    if (response == null) {
                        return ResponseEntity.notFound().build();
                        }
                    return ResponseEntity.ok(response);
                });
    }
}
