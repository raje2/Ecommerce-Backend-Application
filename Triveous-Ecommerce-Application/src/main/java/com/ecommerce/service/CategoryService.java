package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Category;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public List<Category> getCategoryList();
	
	public Category deleteCategory(Integer id);
	
	public Category finCategoryById(Integer id);

}
