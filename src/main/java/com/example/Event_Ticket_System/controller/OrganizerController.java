package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.entity.Organizer;
import com.example.Event_Ticket_System.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    // POST organizers
    @PostMapping
    public ResponseEntity<Organizer> createOrganizers(
            @RequestBody Organizer organizer
    ) {
        Organizer savedOrganizer = organizerService.createOrganization(organizer);
        return ResponseEntity.status(201).body(savedOrganizer);
    }
}
