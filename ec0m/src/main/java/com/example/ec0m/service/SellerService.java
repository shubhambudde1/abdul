package com.example.ec0m.service;

import com.example.ec0m.model.Seller;
import com.example.ec0m.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }

    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
