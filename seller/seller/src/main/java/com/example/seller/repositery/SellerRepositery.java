package com.example.seller.repositery;

import com.example.seller.model.sellermodel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepositery extends JpaRepository<sellermodel, Long> {
}
