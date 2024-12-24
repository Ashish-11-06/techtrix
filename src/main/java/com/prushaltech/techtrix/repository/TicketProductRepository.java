package com.prushaltech.techtrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.TicketProduct;

@Repository
public interface TicketProductRepository extends JpaRepository<TicketProduct, Long> {

    // Custom query to find products related to a ticket
    List<TicketProduct> findByTicketId(Long ticketId);
}

