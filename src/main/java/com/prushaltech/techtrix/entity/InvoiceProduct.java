package com.prushaltech.techtrix.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import jakarta.persistence.Entity;
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
@Table(name = "invoice_product")
public class InvoiceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceProductId;

    private Long invoiceId;

    private Long productId;

    private Integer quantity;
    private BigDecimal unitPrice;

    @Formula("quantity * unitPrice")
    private BigDecimal totalPrice;

    @CreationTimestamp
    private LocalDateTime warrantyStartDate;

    @Formula("DATE_ADD(warrantyStartDate, INTERVAL (SELECT warrantyMonths FROM product WHERE product.productId = invoice_product.productId) MONTH)")
    private LocalDateTime warrantyEndDate;
}
