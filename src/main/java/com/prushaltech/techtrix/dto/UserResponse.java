package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;

import com.prushaltech.techtrix.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String zipCode;
	private User.Role role;
	private User.UserType userType;
	private Boolean isActive;
	private LocalDateTime hireDate;
	private LocalDateTime lastLoginDate;
	private LocalDateTime createdDate;
	private LocalDateTime lastUpdatedDate;
}
