package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.dto.EventResponseDTO;
import com.example.Event_Ticket_System.dto.TicketTypesDTO;
import com.example.Event_Ticket_System.entity.Event;
import com.example.Event_Ticket_System.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/events")
public class Events {

    @Autowired
    private EventService eventService;

    @PostMapping("/api/events")
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody Event event,
            @RequestParam Integer organizer_id,
            @RequestParam Integer venue_id
    ) {
        Event savedEvent = eventService.createEvent(event, organizer_id, venue_id);
        EventResponseDTO eventDTO = new EventResponseDTO(
                savedEvent.getDescription(),               // String event_details
                Collections.emptyList(), // List<TicketTypesDTO> assume it empty b/c there is no ticket in Event entity yet
                savedEvent.getOrganizer().getName(),
                savedEvent.getVenue().getName()
        );
    return ResponseEntity.status(201).body(eventDTO);
    }

    @GetMapping("api/events")
    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        List<EventResponseDTO> upcomingEvents = eventService.getAllUpcomingEvents();
        return ResponseEntity.status(200).body(upcomingEvents);
    }
}
