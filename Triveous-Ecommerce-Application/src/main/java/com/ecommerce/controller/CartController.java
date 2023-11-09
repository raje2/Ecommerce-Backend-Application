package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.CartNotFoundException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
    private CartService cartService;

	@PostMapping("/addProduct")
    public ResponseEntity<Cart> addProductToCurrentUserCart(
        @RequestParam("id") Integer productId
    ) {
        Cart cart = cartService.addProductToCurrentUserCart(productId);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
	
	@DeleteMapping("/removeProduct")
    public ResponseEntity<Cart> removeProductFromCurrentUserCart(
        @RequestParam("id") Integer productId,
        @AuthenticationPrincipal UserDetails userDetails
    ) throws CartNotFoundException {
        Cart cart = cartService.removeProductFromCurrentUserCart(productId);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
	
	@GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProductsInCurrentUserCart(
        
    ) throws CartNotFoundException {
        List<Product> products = cartService.getProductsInCurrentUserCart();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

}
