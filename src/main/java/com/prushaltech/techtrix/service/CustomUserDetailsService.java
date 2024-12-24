package com.prushaltech.techtrix.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.entity.User;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if (!userRepository.existsByEmail(email)) {
			throw new ResourceNotFoundException("Email ["+email+"] not found");
		} else {
			User user = userRepository.findByEmail(email).get();
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(),
					new ArrayList<>());
			return userDetails;
		}
	}
}
