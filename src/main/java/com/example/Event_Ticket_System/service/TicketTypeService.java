
package com.example.Event_Ticket_System.service;

import com.example.Event_Ticket_System.entity.Event;
import com.example.Event_Ticket_System.entity.TicketType;
import com.example.Event_Ticket_System.repository.EventRepository;
import com.example.Event_Ticket_System.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    // Class Constructor
    public TicketTypeService(TicketTypeRepository ticketTypeRepository,
                             EventRepository eventRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public TicketType createTicketType(Integer eventId, TicketType ticketType) {
        // Retrieves event
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found."));

        // Checks if ticket price is >= 0
        if (ticketType.getPrice() == null || ticketType.getPrice() < 0) {
            throw new RuntimeException("Ticket price must be greater than or equal to 0.");
        }

        ticketType.setEvent(event);
        return ticketTypeRepository.save(ticketType);
    }
}
