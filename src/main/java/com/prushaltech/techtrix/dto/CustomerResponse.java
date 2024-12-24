package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
	private Long customerId;
	private String Cust_ID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String zipCode;
	private String companyName;
	private Boolean isPremium;
	private LocalDateTime createdDate;
	private LocalDateTime lastUpdatedDate;
}
