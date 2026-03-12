package com.example.Event_Ticket_System.service;

import com.example.Event_Ticket_System.entity.Event;
import com.example.Event_Ticket_System.entity.Organizer;
import com.example.Event_Ticket_System.entity.Venue;
import com.example.Event_Ticket_System.repository.EventRepository;
import com.example.Event_Ticket_System.repository.OrganizerRepository;
import com.example.Event_Ticket_System.repository.VenueRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    // Is this public Event createEvent(EvenTDO event, Interger event_id) or
    @Transactional
    public Event createEvent(Event event, Integer event_id) {
        organizerRepository.findById(event_id)
                .orElseThrow(() -> new EntityNotFoundException("Organizer does not exist"));

        venueRepository.findById(event_id)
                .orElseThrow(() -> new EntityNotFoundException("Venue does not exist"));

        Event eventCreated = new Event();
        eventCreated.setEvent_id(event.getEvent_id());

        // OR
        //eventCreatedTDO.event.setTitle(dto.getTitle());.....

        return eventRepository.save(event);
    }

}
