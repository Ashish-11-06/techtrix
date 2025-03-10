package com.prushaltech.techtrix.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.LoginUserRequest;
import com.prushaltech.techtrix.dto.LoginUserResponse;
import com.prushaltech.techtrix.dto.UserRequest;
import com.prushaltech.techtrix.dto.UserResponse;
import com.prushaltech.techtrix.entity.User;
import com.prushaltech.techtrix.entity.User.Role;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserResponse createUser(UserRequest userRequest) {
		if (userRepository.existsByEmail(userRequest.getEmail())) {
			throw new IllegalArgumentException("Email is already in use");
		}

		User user = modelMapper.map(userRequest, User.class);
		user.setDepartment(user.getRole().toString());
		user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword())); // Hash the password

		return modelMapper.map(userRepository.save(user), UserResponse.class);
	}
	
	public LoginUserResponse loginUser(LoginUserRequest userRequest) {
	    // Initialize response object
	    LoginUserResponse loginUserResponse = new LoginUserResponse();
	    
	    Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
	    
	    if (optionalUser.isEmpty()) {
	        // User not found
	        loginUserResponse.setHttpStatus(HttpStatus.NOT_FOUND);
	        loginUserResponse.setMessage("Email [" + userRequest.getEmail() + "] not found");
	        loginUserResponse.setUserContent("Content Not Found");
	        return loginUserResponse;  // Return early to avoid further processing
	    }
	    
	    User user = optionalUser.get();
	    
	 // Check if the user is active
	    if (!user.getIsActive()) { 
	        loginUserResponse.setHttpStatus(HttpStatus.FORBIDDEN);
	        loginUserResponse.setMessage("Email [" + userRequest.getEmail() + "] is not active");
	        loginUserResponse.setUserContent("User is inactive");
	        return loginUserResponse;  // Return early if the user is inactive
	    }

	    
	    // Check for incorrect password
	    if (!passwordEncoder.matches(userRequest.getPassword(), user.getPasswordHash())) {
	        loginUserResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
	        loginUserResponse.setMessage("Email [" + userRequest.getEmail() + "] found but password incorrect");
	        loginUserResponse.setUserContent("Content Unauthorized");
	        return loginUserResponse;  // Return early to avoid unnecessary logic
	    }
	    
	    // Successful login
	    loginUserResponse.setHttpStatus(HttpStatus.OK);
	    loginUserResponse.setMessage("Login successful");
	    // Map user to UserResponse (perform mapping only once)
	    loginUserResponse.setUserContent(modelMapper.map(user, UserResponse.class));
	    
	    System.out.println(loginUserResponse);
	    
	    return loginUserResponse;
	}


	public UserResponse updateUser(Long userId, UserRequest userRequest) {
	    // Find the existing user or throw an exception if not found
	    User existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + userId));

	    // Update only the fields that are provided in the userRequest
	    if (userRequest.getFirstName() != null) {
	        existingUser.setFirstName(userRequest.getFirstName());
	    }
	    if (userRequest.getLastName() != null) {
	        existingUser.setLastName(userRequest.getLastName());
	    }
	    if (userRequest.getEmail() != null) {
	        existingUser.setEmail(userRequest.getEmail());
	    }
	    if (userRequest.getPhoneNumber() != null) {
	        existingUser.setPhoneNumber(userRequest.getPhoneNumber());
	    }
	    if (userRequest.getAddress() != null) {
	        existingUser.setAddress(userRequest.getAddress());
	    }
	    if (userRequest.getZipCode() != null) {
	        existingUser.setZipCode(userRequest.getZipCode());
	    }
	    if (userRequest.getPassword() != null) {
	        existingUser.setPasswordHash(passwordEncoder.encode(userRequest.getPassword())); // Rehash the password
	    }
	    if (userRequest.getRole() != null) {
	        existingUser.setRole(userRequest.getRole());
	    }
	    if (userRequest.getUserType() != null) {
	        existingUser.setUserType(userRequest.getUserType());
	    }
	    if (userRequest.getIsActive() != null) {
	        existingUser.setIsActive(userRequest.getIsActive());
	    }

	    // Save the updated user and map it to UserResponse
	    User updatedUser = userRepository.save(existingUser);
	    return modelMapper.map(updatedUser, UserResponse.class);
	}
	
	
	public UserResponse getUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + userId));

		return modelMapper.map(user, UserResponse.class);
	}

	public void deleteUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + userId));

		userRepository.delete(user);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
	}

	public Page<UserResponse> getAllUsersPageable(Pageable pageable) {
		return userRepository.findAll(pageable).map(user -> modelMapper.map(user, UserResponse.class));
	}
	
	public List<UserResponse> getUserByRole(Role role) {
		return userRepository.findAllByRole(role).stream().map(user -> modelMapper.map(user, UserResponse.class))
				.collect(Collectors.toList());
	}

	

}
