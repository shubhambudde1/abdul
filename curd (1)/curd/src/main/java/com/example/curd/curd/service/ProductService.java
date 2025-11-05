package com.example.curd.curd.service;


import com.example.curd.curd.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void save(Product product);
    Product getById(Long id);
    void delete(Long id);
}
