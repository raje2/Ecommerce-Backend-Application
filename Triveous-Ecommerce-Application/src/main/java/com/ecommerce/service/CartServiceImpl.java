package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.CartNotFoundException;
import com.ecommerce.holder.TokenHolder;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repo.CartRepo;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.security.JwtHelper;

@Service
public class CartServiceImpl implements CartService{
	
	
	@Autowired
    private JwtHelper jwtHelper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TokenHolder tokenHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartRepo cartRepo;

	@Override
	public Cart addProductToCurrentUserCart(Integer productId) {
		
		String userEmail = jwtHelper.getUsernameFromToken(tokenHolder.getToken());
		 
	    Optional<User > optUser = userRepo.findByEmail(userEmail);
		User findUser = optUser.get();
		
        Cart cart = findUser.getCart(); // Get the user's cart

        if (cart == null) {
            cart = new Cart();
            cart.setUser(findUser);
        }

        Product product = productService.getProductById(productId);

        cart.getProducts().add(product);

        return cartRepo.save(cart);
		
	}

	@Override
	public Cart removeProductFromCurrentUserCart(Integer productId) throws CartNotFoundException {
		
		String userEmail = jwtHelper.getUsernameFromToken(tokenHolder.getToken());
		 
	    Optional<User > optUser = userRepo.findByEmail(userEmail);
		User findUser = optUser.get();
		
        Cart cart = findUser.getCart();

        if (cart == null) {
            throw new CartNotFoundException("User does not have a cart.");
        }

        Product product = productService.getProductById(productId);

        cart.getProducts().remove(product);

        return cartRepo.save(cart);
	}

	@Override
	public List<Product> getProductsInCurrentUserCart() throws CartNotFoundException {
		String userEmail = jwtHelper.getUsernameFromToken(tokenHolder.getToken());
		 
	    Optional<User > optUser = userRepo.findByEmail(userEmail);
		User findUser = optUser.get();
		
        Cart cart = findUser.getCart();

        if (cart == null) {
            throw new CartNotFoundException("User does not have a cart.");
        }

        return new ArrayList<>(cart.getProducts());
    }
	
}
