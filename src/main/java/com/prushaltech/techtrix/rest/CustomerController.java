package com.prushaltech.techtrix.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.CustomerRequest;
import com.prushaltech.techtrix.dto.CustomerResponse;
import com.prushaltech.techtrix.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
		return ResponseEntity.ok(customerService.createCustomer(customerRequest));
	}

	@PutMapping("/update/{customerId}")
	public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long customerId,
			@RequestBody CustomerRequest customerRequest) {
		return ResponseEntity.ok(customerService.updateCustomer(customerId, customerRequest));
	}

	@GetMapping("/get/{customerId}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId) {
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}

	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<CustomerResponse>> getCustomersByFirstName(@RequestParam String firstName) {
		return ResponseEntity.ok(customerService.getCustomersByFirstName(firstName));
	}

	@GetMapping("/all")
	public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@GetMapping("/pagination")
	public ResponseEntity<Page<CustomerResponse>> getAllCustomersPageable(Pageable pageable) {
		return ResponseEntity.ok(customerService.getAllCustomersPageable(pageable));
	}
}
