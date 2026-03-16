package com.example.Event_Ticket_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Event")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer event_id;

    private String title;

    private String description;

    private LocalDateTime event_date;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        UPCOMING,
        ONGOING,
        CANCELLED,
        COMPLETED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organizer_id")
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="venue_id")
    private Venue venue;

    // One event can have many attendees
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendee> attendees;
}
