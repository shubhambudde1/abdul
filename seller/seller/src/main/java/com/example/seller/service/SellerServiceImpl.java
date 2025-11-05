package com.example.seller.service;

import com.example.seller.model.sellermodel;
import com.example.seller.repositery.SellerRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements sellerService {
    @Autowired
    private SellerRepositery repo;

    @Override

    public List<sellermodel> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(sellermodel product) {
        repo.save(product);
    }

    @Override
    public sellermodel getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
