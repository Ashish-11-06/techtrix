package com.prushaltech.techtrix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ticket_thread")
public class TicketThread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;

    private Long ticketId;

    private Long fromUserId;

    private Long toUserId;

    private String message;

    private String fileAttachmentPath;

    private Long transferredByUserId;

    @CreationTimestamp
    private LocalDateTime transferredDateTime;

    @Enumerated(EnumType.STRING)
    private Status status = Status.Pending;

    public enum Status {
        Pending, InProgress, Resolved, Closed
    }
}
