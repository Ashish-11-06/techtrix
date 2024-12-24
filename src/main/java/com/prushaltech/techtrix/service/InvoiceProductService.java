package com.prushaltech.techtrix.service;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.InvoiceProductRequest;
import com.prushaltech.techtrix.dto.InvoiceProductResponse;
import com.prushaltech.techtrix.entity.Invoice;
import com.prushaltech.techtrix.entity.InvoiceProduct;
import com.prushaltech.techtrix.entity.Product;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.InvoiceProductRepository;
import com.prushaltech.techtrix.repository.InvoiceRepository;
import com.prushaltech.techtrix.repository.ProductRepository;

@Service
public class InvoiceProductService {

	@Autowired
	private InvoiceProductRepository invoiceProductRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	public InvoiceProductResponse createInvoiceProduct(InvoiceProductRequest request) {
		Invoice invoice = invoiceRepository.findById(request.getInvoiceId())
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));

		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		InvoiceProduct invoiceProduct = new InvoiceProduct();
		invoiceProduct.setInvoiceId(invoice.getInvoiceId());
		invoiceProduct.setProductId(product.getProductId());
		invoiceProduct.setQuantity(request.getQuantity());
		invoiceProduct.setUnitPrice(BigDecimal.valueOf(request.getUnitPrice()));
		invoiceProduct.setTotalPrice(BigDecimal.valueOf(request.getQuantity()).multiply(BigDecimal.valueOf(request.getUnitPrice())));
		invoiceProduct.setWarrantyStartDate(request.getWarrantyStartDate());
		invoiceProduct.setWarrantyEndDate(request.getWarrantyStartDate().plusMonths(product.getWarrantyMonths()));

		InvoiceProduct savedInvoiceProduct = invoiceProductRepository.save(invoiceProduct);

		return modelMapper.map(savedInvoiceProduct, InvoiceProductResponse.class);
	}
}
