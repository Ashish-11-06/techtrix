package com.prushaltech.techtrix.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.UserRequest;
import com.prushaltech.techtrix.dto.UserResponse;
import com.prushaltech.techtrix.entity.User.Role;
import com.prushaltech.techtrix.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.createUser(userRequest));
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.updateUser(userId, userRequest));
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	@GetMapping("/get/role/{role}")
	public ResponseEntity<List<UserResponse>> getUserByRole(@PathVariable Role role) {
		return ResponseEntity.ok(userService.getUserByRole(role));
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/pagination")
	public ResponseEntity<Page<UserResponse>> getAllUsersPageable(Pageable pageable) {
		return ResponseEntity.ok(userService.getAllUsersPageable(pageable));
	}
}
