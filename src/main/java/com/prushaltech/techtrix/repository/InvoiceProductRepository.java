package com.prushaltech.techtrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.InvoiceProduct;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
}
