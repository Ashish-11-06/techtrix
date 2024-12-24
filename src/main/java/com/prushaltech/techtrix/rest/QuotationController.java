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

import com.prushaltech.techtrix.dto.QuotationRequest;
import com.prushaltech.techtrix.dto.QuotationResponse;
import com.prushaltech.techtrix.service.QuotationService;

@RestController
@RequestMapping("/api/quotations")
public class QuotationController {

	@Autowired
	private QuotationService quotationService;

	@PostMapping("/add")
	public ResponseEntity<QuotationResponse> createQuotation(@RequestBody QuotationRequest request) {
		return ResponseEntity.ok(quotationService.createQuotation(request));
	}
	
	@PutMapping("/update/{quotationId}")
	public ResponseEntity<QuotationResponse> updateQuotation(
			@PathVariable Long quotationId, @RequestBody QuotationRequest request) {
		return ResponseEntity.ok(quotationService.updateQuotation(quotationId, request));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<QuotationResponse> getQuotationById(@PathVariable Long id) {
		return ResponseEntity.ok(quotationService.getQuotationById(id));
	}
	
	@GetMapping("/get/ticket/{id}")
	public ResponseEntity<QuotationResponse> getQuotationByTicketId(@PathVariable Long id) {
		return ResponseEntity.ok(quotationService.getQuotationByTicketId(id));
	}
	
	@GetMapping("/get/user/{id}")
	public ResponseEntity<QuotationResponse> getQuotationByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(quotationService.getQuotationByUserIdAndInitiatedStatus(id));
	}

	@GetMapping("/all")
	public ResponseEntity<List<QuotationResponse>> getAllQuotations() {
		return ResponseEntity.ok(quotationService.getAllQuotations());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteQuotation(@PathVariable Long id) {
		quotationService.deleteQuotation(id);
		return ResponseEntity.noContent().build();
	}
}
