package com.example.Event_Ticket_System.service;

//import com.example.Event_Ticket_System.dto.OrganizerDTO;
import com.example.Event_Ticket_System.entity.Organizer;
import com.example.Event_Ticket_System.repository.OrganizerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Transactional
    public Organizer createOrganization(Organizer organizer, Integer organizerId) {
        Organizer org = organizerRepository.findById(organizerId).orElse(null);
        // org.setOrganizer_id(organizerId);  --> this is none sense there for reference
        return organizerRepository.save(organizer);
    }
}