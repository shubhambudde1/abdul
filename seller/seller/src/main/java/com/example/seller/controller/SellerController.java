package com.example.seller.controller;


import com.example.seller.model.sellermodel;
import com.example.seller.service.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.ui.Model;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerServiceImpl service;

    @GetMapping
    @ResponseBody
    public List<sellermodel> getAll() {
        return service.getAll();
    }

    @GetMapping("/data")
    public String data(Model model) {
        return "data"; // Ensure this matches the name of the HTML file in the templates directory
    }

    @GetMapping("/test")
    public String test() {
        return "test"; // Create a test.html file in the templates directory
    }

    @PostMapping
    public void save(@RequestBody sellermodel product){
        service.save(product);
    }

    @GetMapping("/{id}")
    public sellermodel getById(@PathVariable Long id){
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
