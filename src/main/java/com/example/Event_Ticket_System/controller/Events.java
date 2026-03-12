package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.dto.EventResponseDTO;
import com.example.Event_Ticket_System.entity.Event;
import com.example.Event_Ticket_System.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/events")
public class Events {

    @Autowired
    private EventService eventService;

    @PostMapping("/api/events")
    public EventResponseDTO createEvent(
            @RequestBody Event event,
            @RequestParam Integer event_id
    ) {
        Event savedEvent = eventService.createEvent(event, event_id);
        EventResponseDTO createdEvent = new EventResponseDTO(
                savedEvent.getEvent_id()
        );
    }
}
