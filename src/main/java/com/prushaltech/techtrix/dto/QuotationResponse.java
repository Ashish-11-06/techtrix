package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.prushaltech.techtrix.entity.Customer.CustomerType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuotationResponse {
    private Long quotationId;
    private String Quot_ID;
    private Long ticketId;
    private Long createdBy;
    private LocalDateTime quotationDate;
    private Double totalAmount;
    private Double total18GstTax;
	private Double total28GstTax;
    private Double totalTax;
    private Double finalAmount;
    private String status;
    private String comments;
	private String taxes;
	private String delivery;
	private String payment;
	private String warrantyOrSupport;
	private String transport;
	
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
	
	private List<QuotationProductResponse> quotationProducts;
}
