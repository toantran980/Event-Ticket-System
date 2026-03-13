package com.example.Event_Ticket_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="Booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer booking_id;

    private String booking_reference;

    private LocalDateTime booking_date;

    @Enumerated(EnumType.STRING)
    private PaymentStatus payment_status;

    // By An Nguyen — made public so BookingService can access this
    public enum PaymentStatus{
        PENDING,
        CONFIRMED,
        CANCELLED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="attendee_id")
    private Attendee attendee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ticket_type_id")
    private TicketType ticketType;
}
