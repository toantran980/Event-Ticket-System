package com.example.Event_Ticket_System.service;

// By An Nguyen — service for all attendee stuff

import com.example.Event_Ticket_System.entity.Attendee;
import com.example.Event_Ticket_System.repository.AttendeeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    // POST /api/attendees
    @Transactional
    public Attendee createAttendee(Attendee attendee) {
        attendeeRepository.findByEmail(attendee.getEmail())
                .ifPresent(existingAttendee -> {
                    throw new EntityExistsException("Attendee with email " + attendee.getEmail() + " already exists");
                });
        return attendeeRepository.save(attendee);
    }

    // GET /api/attendees
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    // GET /api/attendees/exact?name={name} - Retrieve attendees by exact name match (case-sensitive)
    public List<Attendee> getAttendeesByExactName(String name) {
        return attendeeRepository.findByName(name);
    }

    // GET /api/attendees with pagination
    public Page<Attendee> getAttendeesPaginated(Pageable pageable) {
        return attendeeRepository.findALL(pageable);
    }

    // GET /api/attendees/{id}
    public Attendee getAttendeeById(Integer attendeeId) {
        return attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new EntityNotFoundException("Attendee " + attendeeId + " not found"));
    }

    // GET /api/attendees/search?name={name} - Retrieve attendees by partial name match (case-insensitive)
    public List<Attendee> searchAttendeesByName(String name, Sort sort) {
        return attendeeRepository.findByNameContaining(name, sort);
    }

    // GET /api/attendees/domain?domain={domain}
    public List<Attendee> findAttendeesByEmailDomain(String domain) {
        return attendeeRepository.findAll().stream().filter(attendee -> attendee.getEmail().endsWith(domain)).collect(Collectors.toList());
    }

    // GET /api/attendees/count?name={name}
    public long countAttendeesByName(String name) {
        return attendeeRepository.countByName(name);
    }

   // GET /api/attendees/event/{eventId}
    public List<Attendee> getAttendeesByEventId(Integer eventId)
    {
        return attendeeRepository.findByEventId(eventId);
    }

    // Check if attendee exists by email
    public boolean attendeeExistsByEmail(String email) {
        return attendeeRepository.existsByEmail(email);
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
