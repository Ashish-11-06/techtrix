package com.prushaltech.techtrix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String zipCode;
	private String companyName;
	private Boolean isPremium = false;
}
