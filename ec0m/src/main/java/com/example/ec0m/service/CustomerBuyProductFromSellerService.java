package com.example.ec0m.service;

import com.example.ec0m.model.CustomerBuyProductFromSeller;
import com.example.ec0m.repository.CustomerBuyProductFromSellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerBuyProductFromSellerService {

    @Autowired
    private CustomerBuyProductFromSellerRepository customerBuyProductFromSellerRepository;

    public List<CustomerBuyProductFromSeller> getAllCustomerBuyProductFromSellers() {
        return customerBuyProductFromSellerRepository.findAll();
    }

    public CustomerBuyProductFromSeller getCustomerBuyProductFromSellerById(Long id) {
        return customerBuyProductFromSellerRepository.findById(id).orElse(null);
    }

    public CustomerBuyProductFromSeller saveCustomerBuyProductFromSeller(CustomerBuyProductFromSeller customerBuyProductFromSeller) {
        return customerBuyProductFromSellerRepository.save(customerBuyProductFromSeller);
    }

    public void deleteCustomerBuyProductFromSeller(Long id) {
        customerBuyProductFromSellerRepository.deleteById(id);
    }
}
