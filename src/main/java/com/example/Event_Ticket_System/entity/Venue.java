package com.example.Event_Ticket_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="Venue")
@Data
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer venue_id;

    private String name;

    private String address;

    private String city;

    private Integer total_capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "venue")
    private List<Event> events;
}
