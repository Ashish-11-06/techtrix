package com.prushaltech.techtrix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.ProductRequest;
import com.prushaltech.techtrix.dto.ProductResponse;
import com.prushaltech.techtrix.entity.Customer;
import com.prushaltech.techtrix.entity.Product;
import com.prushaltech.techtrix.entity.Product.ProductType;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.CustomerRepository;
import com.prushaltech.techtrix.repository.ProductRepository;
import com.prushaltech.techtrix.util.ModelMapperEx;

@Service
public class ProductService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapperEx modelMapper;

	public ProductResponse createProduct(ProductRequest request) {
		/*
		 * if (request.getProductType().equals(ProductType.Hardware)) { if
		 * (request.getCustomerId() != null) if
		 * (!customerRepository.existsById(request.getCustomerId())) throw new
		 * ResourceNotFoundException("Customer not found with Id: " +
		 * request.getCustomerId()); }
		 */

		Product product = new Product();
		product.setBrand(request.getBrand());
		product.setModelNo(request.getModelNo());
		product.setPartCode(request.getPartCode());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setQuantity(request.getQuantity() == null ? 0 : request.getQuantity());
		product.setUnitOfMeasurement(request.getUnitOfMeasurement() == null ? "Nos" : request.getUnitOfMeasurement());
		product.setHsnCode(request.getHsnCode());
		product.setIsSerialNoAllowed(request.getIsSerialNoAllowed() == null ? false : request.getIsSerialNoAllowed());
		product.setIsGstAvailable(request.getIsGstAvailable() == null ? false : request.getIsGstAvailable());
		product.setGst(request.getGst() == null ? 0 : request.getGst());
		product.setWarrantyMonths(request.getWarrantyMonths() == null ? 0 : request.getWarrantyMonths());
		product.setCustomerId(request.getCustomerId());
		product.setProductType(request.getProductType() == null ? ProductType.Hardware : request.getProductType());

		Product savedProduct = productRepository.save(product);
		return modelMapper.map(savedProduct, ProductResponse.class);
	}

	public ProductResponse getProductById(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with Id: " + productId));
		return modelMapper.map(product, ProductResponse.class);
	}

	public List<ProductResponse> getProductsByCustomerId(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with Id: " + customerId));
		List<Product> productList = productRepository.findByCustomerId(customer.getCustomerId());
		return modelMapper.mapList(productList, ProductResponse.class);
	}

	public List<ProductResponse> getAllProducts() {
		return modelMapper.mapList(productRepository.findAll(), ProductResponse.class);
	}

	public List<ProductResponse> getAllProductsByProductType(ProductType productType) {
		return modelMapper.mapList(productRepository.findByProductType(productType), ProductResponse.class);
	}

	public List<ProductResponse> getAllNonCustomerProducts() {
		return modelMapper.mapList(productRepository.findByNullCustomerIds(), ProductResponse.class);
	}

	public ProductResponse updateProduct(Long productId, ProductRequest request) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with Id: " + productId));

		if (request.getBrand() != null)
			product.setBrand(request.getBrand());
		if (request.getModelNo() != null)
			product.setModelNo(request.getModelNo());
		if (request.getPartCode() != null)
			product.setPartCode(request.getPartCode());
		if (request.getDescription() != null)
			product.setDescription(request.getDescription());
		if (request.getPrice() != null)
			product.setPrice(request.getPrice());
		if (request.getQuantity() != null)
			product.setQuantity(request.getQuantity());
		if (request.getUnitOfMeasurement() != null)
			product.setUnitOfMeasurement(request.getUnitOfMeasurement());
		if (request.getHsnCode() != null)
			product.setHsnCode(request.getHsnCode());
		if (request.getIsSerialNoAllowed() != null)
			product.setIsSerialNoAllowed(request.getIsSerialNoAllowed());
		if (request.getIsGstAvailable() != null)
			product.setIsGstAvailable(request.getIsGstAvailable());
		if (request.getGst() != null)
			product.setGst(request.getGst());
		if (request.getWarrantyMonths() != null)
			product.setWarrantyMonths(request.getWarrantyMonths());
		if (request.getCustomerId() != null)
			product.setCustomerId(request.getCustomerId());
		if (request.getProductType() != null)
			product.setProductType(request.getProductType());

		Product updatedProduct = productRepository.save(product);
		return modelMapper.map(updatedProduct, ProductResponse.class);
	}

	public void deleteProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with Id: " + productId));
		productRepository.delete(product);
	}
}
