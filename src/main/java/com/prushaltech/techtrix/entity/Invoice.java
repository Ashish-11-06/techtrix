package com.prushaltech.techtrix.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @Column(unique = true)
    private String invoiceNumber;

    private Long ticketId;

    private Long quotationId;

    @CreationTimestamp
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;

    private BigDecimal subTotal;
    private BigDecimal taxAmount;

    @Formula("subTotal + taxAmount")
    private BigDecimal totalAmount;

    private BigDecimal discount;

    @Formula("totalAmount - discount")
    private BigDecimal finalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String paymentMethod;
    private String notes;
    
    @CreationTimestamp
    private LocalDateTime createdDate;

    public enum PaymentStatus { Pending, Paid, Partially_Paid, Overdue }
}
