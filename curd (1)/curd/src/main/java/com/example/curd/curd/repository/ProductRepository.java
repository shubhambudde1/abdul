package com.example.curd.curd.repository;

import com.example.curd.curd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
