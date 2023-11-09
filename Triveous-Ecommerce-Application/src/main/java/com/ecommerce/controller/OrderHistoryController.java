package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.holder.TokenHolder;
import com.ecommerce.model.OrderHistory;
import com.ecommerce.model.User;
import com.ecommerce.repo.OrderHistoryRepo;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.security.JwtHelper;

@RestController
@RequestMapping("/order-history")
public class OrderHistoryController {
	
	@Autowired
    private OrderHistoryRepo orderHistoryRepo;

	@Autowired
    private JwtHelper jwtHelper;

	@Autowired
    private TokenHolder tokenHolder;
	
	@Autowired
    private UserRepo userRepo;
	
    @GetMapping("/user")
    public ResponseEntity<List<OrderHistory>> getOrderHistoryForUser() {
    	String userEmail = jwtHelper.getUsernameFromToken(tokenHolder.getToken());
		 
	    Optional<User > optUser = userRepo.findByEmail(userEmail);
		User findUser = optUser.get();
		
        List<OrderHistory> orderHistory = orderHistoryRepo.findAll();
        
        
        List<OrderHistory> list = new ArrayList<>();
        
        for(OrderHistory i:orderHistory) {
        	if(i.getUser().getUserId() == findUser.getUserId()) {
        		list.add(i);
        	}
        }

        return new ResponseEntity<List<OrderHistory>>(list, HttpStatus.OK);
    }

}
