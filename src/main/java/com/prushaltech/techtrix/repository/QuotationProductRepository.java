package com.prushaltech.techtrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.QuotationProduct;

@Repository
public interface QuotationProductRepository extends JpaRepository<QuotationProduct, Long> {
	List<QuotationProduct> findByQuotationId(Long quotationId);
	QuotationProduct findByQuotationIdAndProductId(Long quotationId, Long productId);
}
