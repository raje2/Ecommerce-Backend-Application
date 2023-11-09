package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Product;

public interface ProductService {
	
	public Product addProduct(Product product);

	public List<Product> getProductList();
	
	public Product removeProductById(Integer id);
	
	public Product getProductById(Integer id);
	
	public List<Product> getProductsByCategoryId(Integer id);
}
