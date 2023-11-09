package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.User;
import com.ecommerce.repo.UserRepo;

@Service
public class UserSerImpl implements UserService{

	@Autowired
	private UserRepo aRepo;
	

	@Override
	public User registerUser(User user){
	
		
		return aRepo.save(user);
	}


	@Override
	public User findUserById(Integer id){
		
		Optional<User> find =  aRepo.findById(id);
		
		return find.get();
	}

}
