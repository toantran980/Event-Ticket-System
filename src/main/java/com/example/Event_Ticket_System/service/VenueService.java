package com.example.Event_Ticket_System.service;

import com.example.Event_Ticket_System.entity.Venue;
import com.example.Event_Ticket_System.repository.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    @Transactional
    public Venue createVenue(Venue organizer, Integer venueId) {
        Venue ven = venueRepository.findById(venueId).orElse(null);
        return venueRepository.save(organizer);
    }

}
