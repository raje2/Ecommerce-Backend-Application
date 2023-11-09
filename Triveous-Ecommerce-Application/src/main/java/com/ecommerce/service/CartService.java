package com.ecommerce.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.exception.CartNotFoundException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;

public interface CartService {
	
	public Cart addProductToCurrentUserCart(Integer productId);
	
	public Cart removeProductFromCurrentUserCart( Integer productId) throws CartNotFoundException;
	
	public List<Product> getProductsInCurrentUserCart() throws CartNotFoundException;
}
