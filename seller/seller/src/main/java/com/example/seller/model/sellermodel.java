package com.example.seller.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class sellermodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String phone;
	private String company_name;
	private String status;

	private String created_at;
}