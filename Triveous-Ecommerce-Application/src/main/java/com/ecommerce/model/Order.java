package com.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    
    @ManyToOne
    private User user;
    
    private LocalDateTime orderDate;
    
    @ManyToMany
    private List<Product> products;
    
    private double total;
    private int quantity;

}
