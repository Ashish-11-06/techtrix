package com.prushaltech.techtrix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.CustomerRequest;
import com.prushaltech.techtrix.dto.CustomerResponse;
import com.prushaltech.techtrix.entity.Customer;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.CustomerRepository;
import com.prushaltech.techtrix.util.GenerateID;
import com.prushaltech.techtrix.util.ModelMapperEx;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapperEx modelMapper;

	public CustomerResponse createCustomer(CustomerRequest customerRequest) {
		if (customerRepository.existsByEmail(customerRequest.getEmail())) {
			throw new IllegalArgumentException("Email is already in use");
		}
		if (customerRepository.existsByCompanyName(customerRequest.getCompanyName())) {
			throw new IllegalArgumentException("Company is already in use");
		}
		Customer customer = modelMapper.map(customerRequest, Customer.class);
		Customer savedCustomer = customerRepository.save(customer);
		savedCustomer.setCust_ID(GenerateID.generateCustomerId(savedCustomer.getCustomerId()));
		Customer updatedCustomer = customerRepository.save(savedCustomer);
		CustomerResponse responseDto = modelMapper.map(updatedCustomer, CustomerResponse.class);
		return responseDto;
	}

	public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with Id: " + customerId));

		modelMapper.map(customerRequest, existingCustomer); // Update existing customer
		CustomerResponse responseDto = modelMapper.map(customerRepository.save(existingCustomer),
				CustomerResponse.class);
		return responseDto;
	}

	public CustomerResponse getCustomerById(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with Id: " + customerId));
		CustomerResponse responseDto = modelMapper.map(customer, CustomerResponse.class);
		return responseDto;
	}

	public void deleteCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with Id: " + customerId));
		customerRepository.delete(customer);
	}

	public List<CustomerResponse> getCustomersByFirstName(String firstName) {
		return customerRepository.findByFirstNameContainingIgnoreCase(firstName).stream()
				.map(customer -> modelMapper.map(customer, CustomerResponse.class)).collect(Collectors.toList());
	}

	public List<CustomerResponse> getAllCustomers() {
		return customerRepository.findAll().stream().map(customer -> modelMapper.map(customer, CustomerResponse.class))
				.collect(Collectors.toList());
	}

	public Page<CustomerResponse> getAllCustomersPageable(Pageable pageable) {
		return customerRepository.findAll(pageable).map(customer -> modelMapper.map(customer, CustomerResponse.class));
	}
}
