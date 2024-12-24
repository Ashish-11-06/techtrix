package com.prushaltech.techtrix.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.ProductResponse;
import com.prushaltech.techtrix.dto.TicketRequest;
import com.prushaltech.techtrix.dto.TicketResponse;
import com.prushaltech.techtrix.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("/add")
	public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest ticketRequest) {
		return ResponseEntity.ok(ticketService.createTicket(ticketRequest));
	}

	@PutMapping("/update/{ticketId}")
	public ResponseEntity<TicketResponse> updateTicket(@PathVariable Long ticketId,
			@RequestBody TicketRequest ticketRequest) {
		return ResponseEntity.ok(ticketService.updateTicket(ticketId, ticketRequest));
	}

	@GetMapping("/get/{ticketId}")
	public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long ticketId) {
		return ResponseEntity.ok(ticketService.getTicketById(ticketId));
	}

	@GetMapping("/get/products/{ticketId}")
	public ResponseEntity<List<ProductResponse>> getProductsAssociatedByTicketId(@PathVariable Long ticketId) {
		return ResponseEntity.ok(ticketService.getProductsAssociatedByTicketId(ticketId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<TicketResponse>> getAllTickets() {
		return ResponseEntity.ok(ticketService.getAllTickets());
	}

	@GetMapping("/all/assigned-to-or-created-by-user/{userId}")
	public ResponseEntity<List<TicketResponse>> getAllTicketsAssignedToOrCreatedByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(ticketService.getAllTicketsAssignedToOrCreatedByUserId(userId));
	}

	@GetMapping("/all-issue-tickets")
	public ResponseEntity<List<TicketResponse>> getAllIssueTickets() {
		return ResponseEntity.ok(ticketService.getAllTickets());
	}

	@DeleteMapping("/delete/{ticketId}")
	public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
		ticketService.deleteTicket(ticketId);
		return ResponseEntity.noContent().build();
	}
}
