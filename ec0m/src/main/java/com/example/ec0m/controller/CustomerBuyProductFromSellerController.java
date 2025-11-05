package com.example.ec0m.controller;

import com.example.ec0m.model.CustomerBuyProductFromSeller;
import com.example.ec0m.service.CustomerBuyProductFromSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerBuyProductFromSellers")
public class CustomerBuyProductFromSellerController {

    @Autowired
    private CustomerBuyProductFromSellerService customerBuyProductFromSellerService;

    @GetMapping
    public List<CustomerBuyProductFromSeller> getAllCustomerBuyProductFromSellers() {
        return customerBuyProductFromSellerService.getAllCustomerBuyProductFromSellers();
    }

    @GetMapping("/{id}")
    public CustomerBuyProductFromSeller getCustomerBuyProductFromSellerById(@PathVariable Long id) {
        return customerBuyProductFromSellerService.getCustomerBuyProductFromSellerById(id);
    }

    @PostMapping
    public CustomerBuyProductFromSeller saveCustomerBuyProductFromSeller(@RequestBody CustomerBuyProductFromSeller customerBuyProductFromSeller) {
        return customerBuyProductFromSellerService.saveCustomerBuyProductFromSeller(customerBuyProductFromSeller);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerBuyProductFromSeller(@PathVariable Long id) {
        customerBuyProductFromSellerService.deleteCustomerBuyProductFromSeller(id);
    }
}
