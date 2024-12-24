package com.prushaltech.techtrix.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserResponse {
	HttpStatus httpStatus;
	String message;
	Object userContent;
	String token;
}
