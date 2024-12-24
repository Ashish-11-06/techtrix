package com.prushaltech.techtrix.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationResponse {
	private Long notificationId;
	private Long userId;
	private String message;
	private LocalDateTime createdDate;
	private Boolean isRead;
	private String notificationType;
}
