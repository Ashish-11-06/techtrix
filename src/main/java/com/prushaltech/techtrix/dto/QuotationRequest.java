package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;

import com.prushaltech.techtrix.entity.Quotation.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuotationRequest {

	private Long ticketId;
	private Long createdBy;
	private Status status;
	private String comments;
	private String taxes;
	private String delivery;
	private String payment;
	private String warrantyOrSupport;
	private String transport;
	private Integer validity;
	private LocalDateTime quotationDate;
}
