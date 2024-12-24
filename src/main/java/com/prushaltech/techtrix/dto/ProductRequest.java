package com.prushaltech.techtrix.dto;

import java.math.BigDecimal;

import com.prushaltech.techtrix.entity.Product.ProductType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
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
	private Boolean isGstAvailable = false;
	private Integer gst;
	private Integer warrantyMonths;
	private Long customerId;
	private ProductType productType;
}
