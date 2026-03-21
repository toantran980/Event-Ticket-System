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
}
