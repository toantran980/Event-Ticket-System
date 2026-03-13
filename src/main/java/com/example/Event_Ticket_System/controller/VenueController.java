package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.entity.Venue;
import com.example.Event_Ticket_System.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    @Autowired
    private VenueService venueService;

    public VenueController() {
        this.venueService = new VenueService();
    }

    // POST organizers
    @PostMapping
    public ResponseEntity<Venue> createVenues(
            @RequestBody Venue venue
    ) {
        Venue savedVenue = venueService.createVenue(venue);
        return ResponseEntity.status(201).body(savedVenue);
    }
}
