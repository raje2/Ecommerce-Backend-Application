package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.CartEmptyException;
import com.ecommerce.holder.TokenHolder;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderHistory;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repo.CartRepo;
import com.ecommerce.repo.OrderHistoryRepo;
import com.ecommerce.repo.OrderRepo;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.security.JwtHelper;

@Service
public class OrderService {
	
	@Autowired
    private CartRepo cartRepo;
    
    @Autowired
    private OrderRepo orderRepository;
    
    @Autowired
    private OrderHistoryRepo orderHistoryRepo;
    
    @Autowired
    private TokenHolder tokenHolder;
    
    @Autowired
    private JwtHelper jwtHelper;
    
    @Autowired
    private UserRepo userRepo;

    public Order placeOrder() throws CartEmptyException {
    	
    	String userEmail = jwtHelper.getUsernameFromToken(tokenHolder.getToken());
		 
	    Optional<User > optUser = userRepo.findByEmail(userEmail);
		User findUser = optUser.get();
		
        Cart cart = findUser.getCart();

        if (cart == null || cart.getProducts().isEmpty()) {
            throw new CartEmptyException("Cannot place an empty order.");
        }

        Order order = new Order();
        order.setUser(findUser);
        order.setOrderDate(LocalDateTime.now());
        order.setProducts(new ArrayList<>(cart.getProducts()));
        
     // Create an order history entry
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setUser(findUser);
        orderHistory.setOrderDate(LocalDateTime.now());

        // Save the order history
        orderHistoryRepo.save(orderHistory);

        // Calculate the total and quantity
        double total = 0;
        int quantity = 0;
        for (Product product : cart.getProducts()) {
            total += product.getPrice();
            quantity++;
        }
        order.setTotal(total);
        order.setQuantity(quantity);

        order = orderRepository.save(order);

        // Clear the user's cart
        cart.getProducts().clear();
        Cart dlt = cartRepo.save(cart);
 System.out.println(dlt+"..........");
        return order;
    }


}
