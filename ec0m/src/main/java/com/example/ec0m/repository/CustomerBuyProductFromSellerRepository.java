package com.example.ec0m.repository;

import com.example.ec0m.model.CustomerBuyProductFromSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerBuyProductFromSellerRepository extends JpaRepository<CustomerBuyProductFromSeller, Long> {
}
