package com.example.Event_Ticket_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="Attendee")
@Data
public class Attendee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer attendee_id;

    private String name;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "attendee")
    private List<Booking> bookings;
}
