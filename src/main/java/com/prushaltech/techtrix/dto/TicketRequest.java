package com.prushaltech.techtrix.dto;

import java.util.List;

import com.prushaltech.techtrix.entity.Ticket;
import com.prushaltech.techtrix.entity.Ticket.CreatedByType;
import com.prushaltech.techtrix.entity.Ticket.TicketType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {

	private CreatedByType createdByType = CreatedByType.Employee;
	private Long createdById;
	private Long assignedTo;
	private String title;
	private String description;
	private Ticket.Status status;
	private String category;
	private Long customerId;
	private Boolean isChargeable;
	private Boolean isQuotationCreated;
	private TicketType ticketType;
	private List<Long> productIds;
}
