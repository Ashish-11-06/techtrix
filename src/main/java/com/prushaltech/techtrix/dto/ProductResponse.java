package com.prushaltech.techtrix.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.prushaltech.techtrix.entity.Product.ProductType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
	private Long productId;
	private Long customerId;
	private ProductType productType;
	private String brand;
	private String modelNo;
	private String partCode;
	private String description;
	private BigDecimal price;
	private Integer quantity;
	private String unitOfMeasurement;
	private String hsnCode;
	private Boolean isSerialNoAllowed;
	private String serialNo;
	private Boolean isGstAvailable;
	private Integer gst;
	private Integer warrantyMonths;
	private LocalDateTime warrantyStartDate;
	private LocalDateTime warrantyEndDate;
	private LocalDateTime createdDate;
}
