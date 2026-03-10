package com.example.Event_Ticket_System.entity;

import jakarta.persistence.*;
import lombok.Data;

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
}
