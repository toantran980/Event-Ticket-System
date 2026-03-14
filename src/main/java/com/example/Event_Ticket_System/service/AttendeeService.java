package com.example.Event_Ticket_System.service;

// By An Nguyen — service for all attendee stuff

import com.example.Event_Ticket_System.entity.Attendee;
import com.example.Event_Ticket_System.repository.AttendeeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    // POST /api/attendees
    @Transactional
    public Attendee createAttendee(Attendee attendee) {
        attendeeRepository.findByEmail(attendee.getEmail())
                .ifPresent( existingAttendee -> {
                    throw new EntityExistsException("Attendee with email " + attendee.getEmail() + " already exists");
                });
        return attendeeRepository.save(attendee);
    }

    // GET /api/attendees
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    // GET /api/attendees/{id}
    public Attendee getAttendeeById(Integer attendeeId) {
        return attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new EntityNotFoundException("Attendee " + attendeeId + " not found"));
    }

    // PUT /api/attendees/{id} — only updates fields that are actually sent
    @Transactional
    public Attendee updateAttendee(Integer attendeeId, Attendee updatedAttendee) {
        Attendee existing = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new EntityNotFoundException("Attendee " + attendeeId + " not found"));

        if (updatedAttendee.getName() != null) existing.setName(updatedAttendee.getName());
        if (updatedAttendee.getEmail() != null) existing.setEmail(updatedAttendee.getEmail());

        return attendeeRepository.save(existing);
    }

    // DELETE /api/attendees/{id}
    @Transactional
    public void deleteAttendee(Integer attendeeId) {
        if (!attendeeRepository.existsById(attendeeId))
            throw new EntityNotFoundException("Attendee " + attendeeId + " not found");
        attendeeRepository.deleteById(attendeeId);
    }
}
