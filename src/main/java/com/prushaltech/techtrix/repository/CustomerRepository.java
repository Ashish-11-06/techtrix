package com.prushaltech.techtrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

	List<Customer> findByIsActive(Boolean isActive);

	boolean existsByEmail(String email);
	
	Customer findByCustomerId(Long customerId);

	Customer findByPhoneNumber(String phoneNumber);

	Customer findByEmail(String email);
	
	@Query("select c from Customer c, Ticket t where c.customerId = t.customerId and t.ticketId = :ticketId")
	Customer findByTicketId(Long ticketId);

	void deleteByPhoneNumber(String phoneNumber);
	
	void deleteByCustomerId(Long customerId);
}
