package com.example.Event_Ticket_System.service;

import com.example.Event_Ticket_System.entity.Organizer;
import com.example.Event_Ticket_System.repository.OrganizerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepository organizerRepository;

    @Transactional
    public Organizer createOrganization(Organizer organizer) {
        return organizerRepository.save(organizer);
    }
}