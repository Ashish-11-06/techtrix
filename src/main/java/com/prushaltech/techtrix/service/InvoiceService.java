package com.prushaltech.techtrix.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.InvoiceRequest;
import com.prushaltech.techtrix.dto.InvoiceResponse;
import com.prushaltech.techtrix.dto.NotificationRequest;
import com.prushaltech.techtrix.entity.Invoice;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private TicketService ticketService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NotificationService notificationService;

	public InvoiceResponse createInvoice(InvoiceRequest request) {
		Invoice invoice = modelMapper.map(request, Invoice.class);
		invoice.setTotalAmount(
				BigDecimal.valueOf(request.getSubTotal()).add(BigDecimal.valueOf(request.getTaxAmount())));
		invoice.setFinalAmount(invoice.getTotalAmount().subtract(BigDecimal.valueOf(request.getDiscount())));
		Invoice savedInvoice = invoiceRepository.save(invoice);

		// Trigger notification after invoice creation
		NotificationRequest notificationRequest = new NotificationRequest();
		Long customerId = ticketService.getTicketById(savedInvoice.getTicketId()).getCustomerId();
		notificationRequest.setUserId(customerId); // Assuming customerId is userId
		notificationRequest.setMessage("Invoice " + savedInvoice.getInvoiceNumber() + " has been created.");
		notificationRequest.setNotificationType("IN_APP"); // Or "EMAIL" based on your logic
		notificationService.createNotification(notificationRequest);

		return modelMapper.map(savedInvoice, InvoiceResponse.class);
	}

	public InvoiceResponse getInvoiceById(Long id) {
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
		return modelMapper.map(invoice, InvoiceResponse.class);
	}

	public List<InvoiceResponse> getAllInvoices() {
		return invoiceRepository.findAll().stream().map(invoice -> modelMapper.map(invoice, InvoiceResponse.class))
				.collect(Collectors.toList());
	}

	public void deleteInvoice(Long id) {
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
		invoiceRepository.delete(invoice);
	}
}
