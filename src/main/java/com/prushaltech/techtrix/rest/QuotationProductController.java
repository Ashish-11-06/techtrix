package com.prushaltech.techtrix.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.ProductRequest;
import com.prushaltech.techtrix.dto.QuotationProductRequest;
import com.prushaltech.techtrix.dto.QuotationProductResponse;
import com.prushaltech.techtrix.dto.QuotationResponse;
import com.prushaltech.techtrix.service.QuotationProductService;

@RestController
@RequestMapping("/api/quotation-products")
public class QuotationProductController {

	@Autowired
	private QuotationProductService quotationProductService;

//	@PostMapping("/add")
//	public ResponseEntity<QuotationProductResponse> createQuotationProduct(
//			@RequestBody QuotationProductRequest request) {
//		return ResponseEntity.ok(quotationProductService.createQuotationProduct(request));
//	}
	
	@PostMapping("/add")
	public ResponseEntity<List<QuotationProductResponse>> createQuotationProducts(
	        @RequestBody List<QuotationProductRequest> requests) {
	    return ResponseEntity.ok(quotationProductService.createQuotationProducts(requests));
	}

	
	@PutMapping("/update/quotation/{qId}/product/{pId}")
	public ResponseEntity<QuotationResponse> updateQuotationProduct(
			@PathVariable Long qId, @PathVariable Long pId, @RequestBody ProductRequest request) {
		return ResponseEntity.ok(quotationProductService.updateQuotationProduct(qId, pId, request));
	}
	
	@DeleteMapping("/delete/{qpId}")
	public ResponseEntity<Void> deleteQuotationProduct(@PathVariable Long qpId) {
		quotationProductService.deleteQuotationProduct(qpId);
		return ResponseEntity.noContent().build();
	}
}
