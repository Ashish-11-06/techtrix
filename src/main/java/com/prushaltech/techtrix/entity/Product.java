package com.prushaltech.techtrix.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
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
	private Integer warrantyMonths;
	private Boolean isGstAvailable = true;
	private Integer gst;

	@CreationTimestamp
	private LocalDateTime warrantyStartDate;

	private LocalDateTime warrantyEndDate;

	@CreationTimestamp
	private LocalDateTime createdDate;
	
    private Long customerId;

    //Product type enum to differentiate between hardware and service products
    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.Hardware;
    
    public enum ProductType {
        Hardware, Service
    }
}
