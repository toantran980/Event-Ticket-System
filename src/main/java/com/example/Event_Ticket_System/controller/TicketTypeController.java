package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.entity.TicketType;
import com.example.Event_Ticket_System.service.TicketTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-types")
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;

    // Class Constructor
    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @PostMapping("/event/{eventId}")
    public ResponseEntity<TicketType> createTicketType(@PathVariable Integer eventId,
                                                       @RequestBody TicketType ticketType) {
        TicketType saved = ticketTypeService.createTicketType(eventId, ticketType);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}