package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceProductRequest {
	private Long invoiceId;
	private Long productId;
	private Integer quantity;
	private Double unitPrice;
	private LocalDateTime warrantyStartDate;
}
