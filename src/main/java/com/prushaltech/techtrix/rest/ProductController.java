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

import com.prushaltech.techtrix.dto.ProductRequest;
import com.prushaltech.techtrix.dto.ProductResponse;
import com.prushaltech.techtrix.entity.Product.ProductType;
import com.prushaltech.techtrix.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
		return ResponseEntity.ok(productService.createProduct(request));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@GetMapping("/get/customer/{customerId}")
	public ResponseEntity<List<ProductResponse>> getProductByCustomerId(@PathVariable Long customerId) {
		return ResponseEntity.ok(productService.getProductsByCustomerId(customerId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping("/all-hardwares")
	public ResponseEntity<List<ProductResponse>> getAllHardwareProducts() {
		return ResponseEntity.ok(productService.getAllProductsByProductType(ProductType.Hardware));
	}
	
	@GetMapping("/all-services")
	public ResponseEntity<List<ProductResponse>> getAllServiceProducts() {
		return ResponseEntity.ok(productService.getAllProductsByProductType(ProductType.Service));
	}
	
	@GetMapping("/all-non-customer-products")
	public ResponseEntity<List<ProductResponse>> getAllNonCustomerProducts() {
		return ResponseEntity.ok(productService.getAllNonCustomerProducts());
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
		return ResponseEntity.ok(productService.updateProduct(id, request));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
