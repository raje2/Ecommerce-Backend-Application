package com.ecommerce.service;

import com.ecommerce.model.User;

public interface UserService {
	
	public User registerUser(User user);
	public User findUserById(Integer id);

}
