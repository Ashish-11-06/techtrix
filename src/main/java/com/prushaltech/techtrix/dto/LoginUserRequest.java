package com.prushaltech.techtrix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
	private String email;
	
	private String password;
}
