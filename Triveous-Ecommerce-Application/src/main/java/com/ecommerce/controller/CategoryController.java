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
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		
		Category newCategory = categoryService.addCategory(category);
		
		return new ResponseEntity<Category>(newCategory,HttpStatus.CREATED);
	}
	
	@GetMapping("/getCategories")
	public ResponseEntity<List<Category>> getListOfCategories(){
		List<Category> list = categoryService.getCategoryList();
		return new ResponseEntity<List<Category>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable("id") Integer id){
		Category find = categoryService.finCategoryById(id);
		return new ResponseEntity<Category>(find,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Category> removeById(@PathVariable("id") Integer id){
		Category find = categoryService.deleteCategory(id);
		return new ResponseEntity<Category>(find,HttpStatus.OK);
	}

}
