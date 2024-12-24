package com.prushaltech.techtrix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketThreadRequest {

	private Long toUserId;

	private String message;

	private String fileAttachmentPath;

	private Long transferredByUserId;
}
