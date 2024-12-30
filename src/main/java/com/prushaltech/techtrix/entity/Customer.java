package com.prushaltech.techtrix.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	private String Cust_ID;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;
	
	private String aadharNumber;

	@Column(nullable = false, unique = true)
	private String email;

	private String phoneNumber;
	
	private String CompanyPhoneNumber;
	
	private String address;
	private String zipCode;

	@Enumerated(EnumType.STRING)
	private CustomerType customerType = CustomerType.Corporate;
	
	private String companyName;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	private Boolean isPremium = false;
	private Boolean isActive = true;

    public enum CustomerType {
        Individual, Corporate
    }
}
