package com.prushaltech.techtrix.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.InvoiceProductRequest;
import com.prushaltech.techtrix.dto.InvoiceProductResponse;
import com.prushaltech.techtrix.service.InvoiceProductService;

@RestController
@RequestMapping("/api/invoice-products")
public class InvoiceProductController {

	@Autowired
	private InvoiceProductService invoiceProductService;

	@PostMapping
	public ResponseEntity<InvoiceProductResponse> createInvoiceProduct(@RequestBody InvoiceProductRequest request) {
		return ResponseEntity.ok(invoiceProductService.createInvoiceProduct(request));
	}
}
