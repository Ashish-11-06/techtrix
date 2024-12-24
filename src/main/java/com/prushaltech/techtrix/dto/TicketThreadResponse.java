package com.prushaltech.techtrix.dto;

import java.time.LocalDateTime;

import com.prushaltech.techtrix.entity.TicketThread;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketThreadResponse {
	private Long threadId;
	private Long ticketId;
	private Long fromUserId;
	private Long toUserId;
	private String message;
	private String fileAttachmentPath;
	private Long transferredByUserId;
	private TicketThread.Status status;
	private LocalDateTime transferredDateTime;
}
