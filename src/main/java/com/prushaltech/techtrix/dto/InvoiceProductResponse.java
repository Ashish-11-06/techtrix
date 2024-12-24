package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceProductResponse {
	private Long invoiceProductId;
	private Long invoiceId;
	private Long productId;
	private Integer quantity;
	private Double unitPrice;
	private Double totalPrice;
	private LocalDateTime warrantyStartDate;
	private LocalDateTime warrantyEndDate;
}
