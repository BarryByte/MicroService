package com.example.microservice.services;

import com.example.microservice.dtos.FakeStoreProductDto;
import com.example.microservice.models.Category;
import com.example.microservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    String url = "https://fakestoreapi.com/products/";
    RestTemplate restTemplate = new RestTemplate();

    public Product getProductById(Long id){
        FakeStoreProductDto response = restTemplate.getForObject(url + id, FakeStoreProductDto.class);

        if(response == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(response);
    }
    public List<Product> getAllProduct(){
        FakeStoreProductDto[] response = restTemplate.getForObject(url , FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        assert response != null;
        for(FakeStoreProductDto fakeStoreProductDto : response){
            products.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto response){
        Product product = new Product();
        product.setId(response.getId());
        product.setTitle(response.getTitle());
        product.setPrice(response.getPrice());
        product.setDescription(response.getDescription());
        product.setImageUrl(response.getImageUrl());
        Category category = new Category();
        product.setCategory(category);

        return product;

    }



}
