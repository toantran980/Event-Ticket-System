package com.example.Event_Ticket_System.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="TicketType")
@Data
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticket_type_id;

    private String name;

    private Integer price;

    private Integer quantity_available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;
}
