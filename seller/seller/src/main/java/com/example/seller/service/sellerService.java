package com.example.seller.service;

import com.example.seller.model.sellermodel;

import java.util.List;

public interface sellerService {
    List<sellermodel> getAll();
    void save(sellermodel product);
    sellermodel getById(Long id);
    void delete(Long id);
}
