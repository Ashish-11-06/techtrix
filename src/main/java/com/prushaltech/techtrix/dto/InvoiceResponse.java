package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceResponse {
    private Long invoiceId;
    private String invoiceNumber;
    private Long ticketId;
    private Long quotationId;
    private Long customerId;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private Double subTotal;
    private Double taxAmount;
    private Double totalAmount;
    private Double discount;
    private Double finalAmount;
    private String paymentStatus;
    private String paymentMethod;
    private String notes;
}
