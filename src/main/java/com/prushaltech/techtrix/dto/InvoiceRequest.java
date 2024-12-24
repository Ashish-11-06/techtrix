package com.prushaltech.techtrix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRequest {
    private String invoiceNumber;
    private Long ticketId;
    private Long quotationId;
    private Long customerId;
    private Double subTotal;
    private Double taxAmount;
    private Double discount;
    private String paymentMethod;
    private String notes;
}
