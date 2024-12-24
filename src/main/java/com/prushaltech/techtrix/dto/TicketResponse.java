package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.prushaltech.techtrix.entity.Ticket;
import com.prushaltech.techtrix.entity.Ticket.TicketType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponse {
	private Long ticketId;
	private Ticket.CreatedByType createdByType;
	private Long createdById;
	private String title;
	private String description;
	private Ticket.Priority priority;
	private Ticket.Status status;
	private String category;
	private Long assignedTo;
	private Long customerId;
	private Boolean isChargeable;
	private Boolean isQuotationCreated;
	private TicketType ticketType;
	private LocalDateTime createdDate;
	private LocalDateTime lastUpdatedDate;
	private LocalDateTime resolvedDate;
	private Boolean isClosed;
	
	private List<ProductResponse> products;
}
