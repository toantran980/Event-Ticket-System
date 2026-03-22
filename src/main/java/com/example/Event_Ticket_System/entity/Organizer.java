package com.example.Event_Ticket_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="Organizer")
@Data
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizer_id;

    private String name;

    private String email;

    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "organizer")
    private List<Event> events;
}
