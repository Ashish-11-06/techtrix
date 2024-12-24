package com.prushaltech.techtrix.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prushaltech.techtrix.dto.NotificationRequest;
import com.prushaltech.techtrix.dto.NotificationResponse;
import com.prushaltech.techtrix.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping
	public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest request) {
		return ResponseEntity.ok(notificationService.createNotification(request));
	}

	@PutMapping("/{id}/read")
	public ResponseEntity<NotificationResponse> markAsRead(@PathVariable Long id) {
		return ResponseEntity.ok(notificationService.markAsRead(id));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<NotificationResponse>> getUserNotifications(@PathVariable Long userId) {
		return ResponseEntity.ok(notificationService.getUserNotifications(userId));
	}
}
