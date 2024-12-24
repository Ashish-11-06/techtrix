package com.prushaltech.techtrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prushaltech.techtrix.entity.TicketThreadFileAttachment;

@Repository
public interface TicketThreadFileAttachmentRepository extends JpaRepository<TicketThreadFileAttachment, Long> {
}
