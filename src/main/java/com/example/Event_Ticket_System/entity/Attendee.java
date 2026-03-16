package com.example.Event_Ticket_System.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Attendee")
@Data
public class Attendee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer attendee_id;

    private String name;

    private String email;

    // Many attendees can belong to one event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false) // Maps to the foreign key column "event_id" in the Attendee table
    private Event event;
}
