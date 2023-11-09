package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

	//List<Product> findAllByCat;
	
	

}
