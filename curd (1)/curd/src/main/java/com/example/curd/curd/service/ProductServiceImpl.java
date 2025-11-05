package com.example.curd.curd.service;


import com.example.curd.curd.model.Product;
import com.example.curd.curd.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(Product product) {
        repo.save(product);
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
