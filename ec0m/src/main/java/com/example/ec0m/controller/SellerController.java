package com.example.ec0m.controller;

import com.example.ec0m.model.Seller;
import com.example.ec0m.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{id}")
    public Seller getSellerById(@PathVariable Long id) {
        return sellerService.getSellerById(id);
    }

    @PostMapping
    public Seller createSeller(@RequestBody Seller seller) {
        return sellerService.saveSeller(seller);
    }

    @DeleteMapping("/{id}")
    public void deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
    }
}
