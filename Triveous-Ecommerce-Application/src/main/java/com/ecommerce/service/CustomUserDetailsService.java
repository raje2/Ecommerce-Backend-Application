package com.ecommerce.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		Optional<com.ecommerce.model.User> findUser = userRepo.findByEmail(userEmail);
		 
		 com.ecommerce.model.User find = findUser.get();
		
		if(userEmail.equals(find.getEmail())) {
			return new User(find.getEmail(), find.getPassword(), new ArrayList<>());
		}
		else
			throw new UsernameNotFoundException("User not found");
		
	}
	
	

}
