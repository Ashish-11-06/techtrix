package com.prushaltech.techtrix.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationId;

	@Column(nullable = false)
	private Long userId; // For whom the notification is intended.

	@Column(nullable = false)
	private String message;

	@Column(nullable = false)
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(nullable = false)
	private Boolean isRead = false; // Tracks if the notification has been read.

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificationType notificationType;

	public enum NotificationType {
		IN_APP, EMAIL
	}
}
