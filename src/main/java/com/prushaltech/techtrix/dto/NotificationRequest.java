package com.prushaltech.techtrix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
	private Long userId;
	private String message;
	private String notificationType; // IN_APP or EMAIL
}
