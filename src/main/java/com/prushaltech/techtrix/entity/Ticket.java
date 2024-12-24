package com.prushaltech.techtrix.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CreatedByType createdByType = CreatedByType.Employee; // Employee or Customer

	@Column(nullable = false)
	private Long createdById;
	
	private Long customerId;

	private Long assignedTo; // Optional, who the ticket is assigned to

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	private Priority priority = Priority.Medium;

	@Enumerated(EnumType.STRING)
	private Status status = Status.Open;

	private String category;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	private LocalDateTime resolvedDate;

	private Boolean isClosed = false;
	private Boolean isChargeable = false;
	private Boolean isQuotationCreated = false;

	private TicketType ticketType = TicketType.Issue;

	public enum CreatedByType {
		Employee, Customer
	}

	public enum Priority {
		Low, Medium, High, Urgent
	}

	public enum Status {
		Open, InProgress, Resolved, Closed
	}
	
	public enum TicketType {
		Issue, Order
	}
}
