package com.prushaltech.techtrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.TicketThread;

@Repository
public interface TicketThreadRepository extends JpaRepository<TicketThread, Long> {
	List<TicketThread> findByTicketId(Long ticketId);
}
