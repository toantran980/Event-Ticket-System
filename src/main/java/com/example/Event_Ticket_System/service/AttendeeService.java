package com.example.Event_Ticket_System.service;

// By An Nguyen — service for all attendee stuff

import com.example.Event_Ticket_System.dto.AttendeeBookingsDTO;
import com.example.Event_Ticket_System.dto.BookingResponseDTO;
import com.example.Event_Ticket_System.entity.Attendee;
import com.example.Event_Ticket_System.entity.Booking;
import com.example.Event_Ticket_System.repository.AttendeeRepository;
import com.example.Event_Ticket_System.repository.BookingRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // POST /api/attendees
    @Transactional
    public AttendeeBookingsDTO createAttendee(Attendee attendee) {
        attendeeRepository.findByEmail(attendee.getEmail())
                .ifPresent( existingAttendee -> {
                    throw new EntityExistsException("Attendee with email " + attendee.getEmail() + " already exists");
                });
        Attendee savedAttendee = attendeeRepository.save(attendee);
        return new AttendeeBookingsDTO(
                savedAttendee.getAttendee_id(),
                savedAttendee.getName(),
                savedAttendee.getEmail(),
                new ArrayList<>()
        );
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
    /*@Transactional
    public void deleteAttendee(Integer attendeeId) {
        if (!attendeeRepository.existsById(attendeeId))
            throw new EntityNotFoundException("Attendee " + attendeeId + " not found");
        attendeeRepository.deleteById(attendeeId);
    }*/

    public List<BookingResponseDTO> getAllBookingsAttendee(Integer attendeeId) {
        List<Booking> bookings = bookingRepository.findBookingsByAttendeeId(attendeeId);

        if (bookings.isEmpty()) {
            throw new EntityNotFoundException("Attendee " + attendeeId + " not found");
        }

        return bookings.stream().map(booking -> {
            BookingResponseDTO dto = new BookingResponseDTO();
            dto.setBooking_reference(booking.getBooking_reference());
            dto.setBooking_date(booking.getBooking_date());
            dto.setStatus(booking.getPayment_status().name());
            dto.setAttendee_name(booking.getAttendee().getName());

            if (booking.getTicketType() != null && booking.getTicketType().getEvent() != null) {
                dto.setEvent_title(booking.getTicketType().getEvent().getTitle());
            }

            dto.setTicket_type_name(booking.getTicketType().getName());
            dto.setPrice(booking.getTicketType().getPrice());
            return dto;
        }).toList();
    }
}
