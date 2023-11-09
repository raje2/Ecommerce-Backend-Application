package com.ecommerce.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OrderHistory {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    
    @ManyToOne
    private User user;
    
    private LocalDateTime orderDate;

}
