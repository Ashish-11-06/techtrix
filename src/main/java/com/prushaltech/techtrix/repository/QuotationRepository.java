package com.prushaltech.techtrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.Quotation;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
	Quotation findByTicketId(Long ticketId);

	List<Quotation> findByCreatedByAndStatus(Long createdBy, Quotation.Status status);

	Quotation findByTicketIdAndCreatedByAndStatus(Long ticketId, Long createdBy, Quotation.Status status);

	List<Quotation> findByCreatedBy(Long createdBy);

	List<Quotation> findByStatus(Quotation.Status status);

	@Query("Select q from Quotation q where q.status!='Initiated'")
	List<Quotation> findByNotInitiatedStatus();
}
