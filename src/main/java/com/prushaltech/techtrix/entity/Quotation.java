package com.prushaltech.techtrix.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.prushaltech.techtrix.entity.Customer.CustomerType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quotation")
public class Quotation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quotationId;
	
	private String Quot_ID;

	@Column(nullable = false)
	private Long ticketId;
	
	private Long c_customerId;
	private String c_Cust_ID;
	private String c_firstName;
	private String c_lastName;
	private String c_aadharNumber;
	private String c_email;
	private String c_phoneNumber;
	private String c_address;
	private String c_zipCode;
	private CustomerType c_customerType;
	private String c_companyName;
	private Boolean c_isPremium;

	@Column(nullable = false)
	private Long createdBy;

	@CreationTimestamp
	private LocalDateTime quotationDate;

	@Column(nullable = false)
	private Double totalAmount;
	
	private Double total18GstTax;
	private Double total28GstTax;
	private Double totalTax;

	@Column(nullable = false)
	private Long finalAmount;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('Initiated', 'Pending', 'Approved', 'Rejected') DEFAULT 'Pending'")
	private Status status = Status.Pending;

	@Lob
	private String comments;

	@Column
	private String taxes;
	
	@Column
	private String delivery;
	
	@Column
	private String payment;
	
	@Column
	private String warrantyOrSupport;
	
	@Column
	private String transport;
	
	@CreationTimestamp
	private LocalDateTime createdDate;

	public enum Status {
		Initiated, Pending, Approved, Rejected
	}
}
