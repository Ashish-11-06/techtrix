package com.prushaltech.techtrix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.ProductResponse;
import com.prushaltech.techtrix.dto.TicketRequest;
import com.prushaltech.techtrix.dto.TicketResponse;
import com.prushaltech.techtrix.entity.Ticket;
import com.prushaltech.techtrix.entity.Ticket.TicketType;
import com.prushaltech.techtrix.entity.TicketProduct;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.TicketProductRepository;
import com.prushaltech.techtrix.repository.TicketRepository;
import com.prushaltech.techtrix.util.ModelMapperEx;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TicketProductRepository ticketProductRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapperEx modelMapper;

	public TicketResponse createTicket(TicketRequest ticketRequest) {
		Ticket ticket = new Ticket();
		ticket.setAssignedTo(ticketRequest.getAssignedTo());
		ticket.setCategory(ticketRequest.getTicketType().toString());
		ticket.setCreatedById(ticketRequest.getCreatedById());
		ticket.setCreatedByType(ticketRequest.getCreatedByType());
		ticket.setCustomerId(ticketRequest.getCustomerId());
		ticket.setDescription(ticketRequest.getDescription());
		ticket.setIsChargeable(ticketRequest.getIsChargeable());
		ticket.setIsClosed(false);
		ticket.setIsQuotationCreated(false);
		ticket.setPriority(Ticket.Priority.Medium);
		ticket.setStatus(Ticket.Status.Open);
		ticket.setTicketType(ticketRequest.getTicketType());
		ticket.setTitle(ticketRequest.getTitle());

		Ticket savedTicket = ticketRepository.save(ticket);
		TicketResponse ticketResponse = modelMapper.map(savedTicket, TicketResponse.class);

		if (ticketRequest.getProductIds() != null) {
			if (!ticketRequest.getProductIds().isEmpty()) {
				List<ProductResponse> products = new ArrayList<>();
				for (Long productId : ticketRequest.getProductIds()) {
					TicketProduct ticketProduct = new TicketProduct();
					ticketProduct.setTicketId(savedTicket.getTicketId());
					ticketProduct.setProductId(productId);
					ticketProductRepository.save(ticketProduct);

					products.add(productService.getProductById(productId));
				}
				ticketResponse.setProducts(products);
			}
		}
		return ticketResponse;
	}

	public TicketResponse updateTicket(Long ticketId, TicketRequest ticketRequest) {
		Ticket existingTicket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found with Id: " + ticketId));
		if (ticketRequest.getAssignedTo() != null)
			existingTicket.setAssignedTo(ticketRequest.getAssignedTo());
		if (ticketRequest.getTitle() != null)
			existingTicket.setTitle(ticketRequest.getTitle());
		if (ticketRequest.getDescription() != null)
			existingTicket.setDescription(ticketRequest.getDescription());
		if (ticketRequest.getStatus() != null)
			existingTicket.setStatus(ticketRequest.getStatus());
		if (ticketRequest.getCustomerId() != null)
			existingTicket.setCustomerId(ticketRequest.getCustomerId());
		if (ticketRequest.getIsChargeable() != null)
			existingTicket.setIsChargeable(ticketRequest.getIsChargeable());
		if (ticketRequest.getIsQuotationCreated() != null)
			existingTicket.setIsQuotationCreated(ticketRequest.getIsQuotationCreated());
		return modelMapper.map(ticketRepository.save(existingTicket), TicketResponse.class);
	}

	public TicketResponse getTicketById(Long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found with Id: " + ticketId));
		List<TicketProduct> ticketProducts = ticketProductRepository.findByTicketId(ticket.getTicketId());
		List<ProductResponse> productResponses = new ArrayList<>();
		for (TicketProduct ticketProduct : ticketProducts) {
			productResponses.add(productService.getProductById(ticketProduct.getProductId()));
		}
		TicketResponse ticketResponse = modelMapper.map(ticket, TicketResponse.class);
		ticketResponse.setProducts(productResponses);
		return ticketResponse;
	}

	public List<ProductResponse> getProductsAssociatedByTicketId(Long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found with Id: " + ticketId));
		List<TicketProduct> ticketProducts = ticketProductRepository.findByTicketId(ticket.getTicketId());
		List<ProductResponse> productResponses = new ArrayList<>();
		for (TicketProduct ticketProduct : ticketProducts) {
			productResponses.add(productService.getProductById(ticketProduct.getProductId()));
		}
		return modelMapper.mapList(productResponses, ProductResponse.class);
	}

	public List<TicketResponse> getAllTickets() {
		return ticketRepository.findByTicketType(TicketType.Issue).stream()
				.map(ticket -> modelMapper.map(ticket, TicketResponse.class)).collect(Collectors.toList());
	}

	public List<TicketResponse> getAllTicketsAssignedToOrCreatedByUserId(Long userId) {
		return ticketRepository.findByCreatedByIdOrAssignedTo(userId).stream()
				.map(ticket -> modelMapper.map(ticket, TicketResponse.class)).collect(Collectors.toList());
	}

	public List<TicketResponse> getAllIssueTickets() {
		return ticketRepository.findAll().stream().map(ticket -> modelMapper.map(ticket, TicketResponse.class))
				.collect(Collectors.toList());
	}

	public void deleteTicket(Long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found with Id: " + ticketId));
		List<TicketProduct> ticketProducts = ticketProductRepository.findByTicketId(ticket.getTicketId());
		for (TicketProduct ticketProduct : ticketProducts) {
			ticketProductRepository.delete(ticketProduct);
		}
		ticketRepository.delete(ticket);
	}
}
