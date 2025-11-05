

package com.example.curd.curd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //primary key
    private String name;
    private double price;
    private String description;
    private int quantity;
    private int discount;
    private String size;


}

