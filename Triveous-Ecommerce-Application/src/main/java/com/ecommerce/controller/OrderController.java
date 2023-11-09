package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.CartEmptyException;
import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	 @Autowired
	    private OrderService orderService;

	    @PostMapping("/place")
	    public ResponseEntity<Order> placeOrder() throws CartEmptyException {
	        
	        Order order = orderService.placeOrder();
	        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	    }
	

}
