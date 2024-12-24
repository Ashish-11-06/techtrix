package com.prushaltech.techtrix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.TicketThreadRequest;
import com.prushaltech.techtrix.dto.TicketThreadResponse;
import com.prushaltech.techtrix.entity.Ticket;
import com.prushaltech.techtrix.entity.TicketThread;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.TicketRepository;
import com.prushaltech.techtrix.repository.TicketThreadRepository;

@Service
public class TicketThreadService {

	@Autowired
	private TicketThreadRepository ticketThreadRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ModelMapper modelMapper;

	public TicketThreadResponse createTicketThread(Long ticketId, TicketThreadRequest ticketThreadRequest) {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found with Id: " + ticketId));

		TicketThread ticketThread = new TicketThread();
		ticketThread.setTicketId(ticket.getTicketId());
		modelMapper.map(ticketThreadRequest, ticketThread);

		return modelMapper.map(ticketThreadRepository.save(ticketThread), TicketThreadResponse.class);
	}

	public List<TicketThreadResponse> getTicketThreads(Long ticketId) {
		return ticketThreadRepository.findByTicketId(ticketId).stream()
				.map(thread -> modelMapper.map(thread, TicketThreadResponse.class)).collect(Collectors.toList());
	}
}
