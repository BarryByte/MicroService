package com.example.microservice.services;

import com.example.microservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProduct();
}
