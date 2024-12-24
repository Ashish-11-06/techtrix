package com.prushaltech.techtrix.dto;

import com.prushaltech.techtrix.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String zipCode;
	private String password;
	private User.Role role;
	private User.UserType userType;
}
