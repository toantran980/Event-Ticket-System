package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.entity.Venue;
import com.example.Event_Ticket_System.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venues")
public class Venues {
    @Autowired
    private VenueService venueService;

    // POST organizers
    @PostMapping("api/venues")
    public ResponseEntity<Venue> createVenues(
            @RequestBody Venue venue,
            @RequestParam Integer venueId
    ) {
        Venue savedVenue = venueService.createVenue(venue, venueId);
        return ResponseEntity.status(201).body(savedVenue);
    }
}
