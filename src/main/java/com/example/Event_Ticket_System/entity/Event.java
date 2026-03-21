package com.example.Event_Ticket_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("eventDate")
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

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<TicketType> ticketTypes;
}
