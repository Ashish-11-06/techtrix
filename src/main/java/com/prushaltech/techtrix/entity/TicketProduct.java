package com.prushaltech.techtrix.entity;

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
@Table(name = "ticket_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketProductId;

    private Long ticketId;
    private Long productId;
}
