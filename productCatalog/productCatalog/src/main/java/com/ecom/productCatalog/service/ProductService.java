package com.ecom.productCatalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.productCatalog.model.Product;
import com.ecom.productCatalog.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> getAllProductsByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }
}
