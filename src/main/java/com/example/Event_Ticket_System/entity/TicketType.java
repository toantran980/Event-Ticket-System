package com.example.Event_Ticket_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="TicketType")
@Data
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticket_type_id;

    private String name;

    private Double price;

    @Column(name = "quantity_available")
    private Integer quantity_available = 100;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties({"ticketTypes"})
    private Event event;

    @JsonIgnore
    @OneToMany(mappedBy = "ticketType")
    private List<Booking> bookings;
}
