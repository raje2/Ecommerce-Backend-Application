package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService proService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		
		Product newProduct = proService.addProduct(product);
		
		return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getListOfProducts(){
		List<Product> list = proService.getProductList();
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProductsByCategoryId(@RequestParam("id") Integer categoryId) {
	    List<Product> products = proService.getProductsByCategoryId(categoryId);
	    return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}


}
