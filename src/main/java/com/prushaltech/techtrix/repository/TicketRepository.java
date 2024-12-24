package com.prushaltech.techtrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.Ticket;
import com.prushaltech.techtrix.entity.Ticket.TicketType;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByCreatedById(Long createdById);
	List<Ticket> findByAssignedTo(Long assignedTo);
	@Query("select t from Ticket t where t.ticketType=0 and (t.createdById=:userId or t.assignedTo=:userId)")
	List<Ticket> findByCreatedByIdOrAssignedTo(Long userId);
	List<Ticket> findByCustomerId(Long customerId);
	List<Ticket> findByTicketType(TicketType ticketType);
}
