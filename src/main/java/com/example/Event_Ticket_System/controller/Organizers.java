package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.entity.Organizer;
import com.example.Event_Ticket_System.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizers")
public class Organizers {

    @Autowired
    private OrganizerService organizerService;

    // POST organizers
    @PostMapping("api/organizers")
    public ResponseEntity<Organizer> createOrganizers(
            @RequestBody Organizer organizer,
            @RequestParam Integer organizerId
    ) {
        Organizer savedOrganizer = organizerService.createOrganization(organizer, organizerId);
        return ResponseEntity.status(201).body(savedOrganizer);
    }
}
