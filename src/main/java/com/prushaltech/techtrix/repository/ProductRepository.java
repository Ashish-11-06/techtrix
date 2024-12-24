package com.prushaltech.techtrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.Product;
import com.prushaltech.techtrix.entity.Product.ProductType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findBySerialNo(String serialNo);
	boolean existsBySerialNo(String serialNo);
	List<Product> findByCustomerId(Long customerId);
	
	@Query("select p from Product p where p.customerId is null")
	List<Product> findByNullCustomerIds();
	List<Product> findByProductType(ProductType productType);
}
