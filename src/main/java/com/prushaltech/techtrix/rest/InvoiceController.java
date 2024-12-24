package com.prushaltech.techtrix.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.InvoiceRequest;
import com.prushaltech.techtrix.dto.InvoiceResponse;
import com.prushaltech.techtrix.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@PostMapping
	public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody InvoiceRequest request) {
		return ResponseEntity.ok(invoiceService.createInvoice(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<InvoiceResponse> getInvoiceById(@PathVariable Long id) {
		return ResponseEntity.ok(invoiceService.getInvoiceById(id));
	}

	@GetMapping
	public ResponseEntity<List<InvoiceResponse>> getAllInvoices() {
		return ResponseEntity.ok(invoiceService.getAllInvoices());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
		invoiceService.deleteInvoice(id);
		return ResponseEntity.noContent().build();
	}
}
