package com.prushaltech.techtrix.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.TicketThreadRequest;
import com.prushaltech.techtrix.dto.TicketThreadResponse;
import com.prushaltech.techtrix.service.TicketThreadService;

@RestController
@RequestMapping("/api/tickets/{ticketId}/threads")
public class TicketThreadController {

	@Autowired
	private TicketThreadService ticketThreadService;

	@PostMapping
	public ResponseEntity<TicketThreadResponse> createTicketThread(@PathVariable Long ticketId,
			@RequestBody TicketThreadRequest ticketThreadRequest) {
		return ResponseEntity.ok(ticketThreadService.createTicketThread(ticketId, ticketThreadRequest));
	}

	@GetMapping
	public ResponseEntity<List<TicketThreadResponse>> getTicketThreads(@PathVariable Long ticketId) {
		return ResponseEntity.ok(ticketThreadService.getTicketThreads(ticketId));
	}
}
