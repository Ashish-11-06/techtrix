package com.prushaltech.techtrix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prushaltech.techtrix.dto.NotificationRequest;
import com.prushaltech.techtrix.dto.NotificationResponse;
import com.prushaltech.techtrix.entity.Notification;
import com.prushaltech.techtrix.exception.ResourceNotFoundException;
import com.prushaltech.techtrix.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmailService emailService;

	public NotificationResponse createNotification(NotificationRequest request) {
		Notification notification = modelMapper.map(request, Notification.class);
		notification.setNotificationType(Notification.NotificationType.valueOf(request.getNotificationType()));

		// If email notification is requested, send an email.
		if (notification.getNotificationType() == Notification.NotificationType.EMAIL) {
			// This email address should come from the user details; for now, it's
			// hardcoded.
			String email = "nitis566ni@gmail.com";
			emailService.sendEmail(email, "New Notification", notification.getMessage());
		}

		Notification savedNotification = notificationRepository.save(notification);
		return modelMapper.map(savedNotification, NotificationResponse.class);
	}

	public NotificationResponse markAsRead(Long notificationId) {
		Notification notification = notificationRepository.findById(notificationId)
				.orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
		notification.setIsRead(true);
		Notification updatedNotification = notificationRepository.save(notification);
		return modelMapper.map(updatedNotification, NotificationResponse.class);
	}

	public List<NotificationResponse> getUserNotifications(Long userId) {
		return notificationRepository.findByUserId(userId).stream()
				.map(notification -> modelMapper.map(notification, NotificationResponse.class))
				.collect(Collectors.toList());
	}
}
