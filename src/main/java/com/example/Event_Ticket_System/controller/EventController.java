package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.dto.EventResponseDTO;
import com.example.Event_Ticket_System.dto.RevenueDTO;
import com.example.Event_Ticket_System.entity.Event;
import com.example.Event_Ticket_System.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    // POST /api/events
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody Event event,
            @RequestParam Integer organizer_id,
            @RequestParam Integer venue_id
    ) {
        EventResponseDTO savedEvent = eventService.createEvent(event, organizer_id, venue_id);
        return ResponseEntity.status(201).body(savedEvent);
    }

    // GET /api/events
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        List<EventResponseDTO> upcomingEvents = eventService.getAllUpcomingEvents();
        return ResponseEntity.status(200).body(upcomingEvents);
    }

    // GET /api/events/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventByIdWithTicketTypes(@PathVariable("id") Integer event_id) {
        EventResponseDTO events = eventService.getEventByIdWithTicketTypes(event_id);
        return ResponseEntity.status(200).body(events);
    }

    // GET /api/events/{id}/revenue
    @GetMapping("{id}/revenue")
    public ResponseEntity<RevenueDTO> getEventRevenue(
            @PathVariable("id") Integer revenueId) {
        RevenueDTO revenueDTO = eventService.getEventRevenue(revenueId);
        return ResponseEntity.status(200).body(revenueDTO);
    }
}
