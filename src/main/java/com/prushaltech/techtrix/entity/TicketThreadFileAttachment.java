package com.prushaltech.techtrix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ticket_thread_file_attachment")
public class TicketThreadFileAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attachmentId;

	private Long threadId;

	private String fileAttachmentPath;

	@CreationTimestamp
	private LocalDateTime uploadedDateTime;
}
