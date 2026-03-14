package com.example.Event_Ticket_System.service;

// By An Nguyen — handles booking and cancellation logic

import com.example.Event_Ticket_System.dto.BookingResponseDTO;
import com.example.Event_Ticket_System.entity.Attendee;
import com.example.Event_Ticket_System.entity.Booking;
import com.example.Event_Ticket_System.entity.TicketType;
import com.example.Event_Ticket_System.repository.AttendeeRepository;
import com.example.Event_Ticket_System.repository.BookingRepository;
import com.example.Event_Ticket_System.repository.TicketTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    // POST /api/bookings
    // checks attendee + ticket exist, makes sure there's stock, then creates the booking
    @Transactional
    public BookingResponseDTO bookTicket(Integer attendeeId, Integer ticketTypeId) {

        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new EntityNotFoundException("Attendee " + attendeeId + " not found"));

        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new EntityNotFoundException("TicketType " + ticketTypeId + " not found"));

        if (ticketType.getQuantity_available() <= 0)
            throw new IllegalStateException("No tickets left for: " + ticketType.getName());

        // take one ticket from stock
        ticketType.setQuantity_available(ticketType.getQuantity_available() - 1);
        ticketTypeRepository.save(ticketTypeRepository.save(ticketType));

        // build the booking
        Booking booking = new Booking();
        booking.setBooking_date(LocalDateTime.now());
        booking.setPayment_status(Booking.PaymentStatus.CONFIRMED);
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);

        // save first to get the id
        Booking saved = bookingRepository.save(booking);

        // format: TKT-year-paddedId
        String ref = String.format("TKT-%d-%06d", saved.getBooking_date().getYear(), saved.getBooking_id());
        saved.setBooking_reference(ref);

        return mapToDTO(bookingRepository.save(saved));
    }

    // PUT /api/bookings/{id}/cancel
    // flips status to CANCELLED and puts the ticket slot back
    @Transactional
    public BookingResponseDTO cancelBooking(Integer bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking " + bookingId + " not found"));

        if (Booking.PaymentStatus.CANCELLED.equals(booking.getPayment_status()))
            throw new IllegalStateException("Booking " + bookingId + " is already cancelled");

        booking.setPayment_status(Booking.PaymentStatus.CANCELLED);

        // give the slot back
        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantity_available(ticketType.getQuantity_available() + 1);
        ticketTypeRepository.save(ticketType);

        return mapToDTO(bookingRepository.save(booking));
    }

    // turns a Booking entity into a clean DTO for the response
    private BookingResponseDTO mapToDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBooking_reference(booking.getBooking_reference());
        dto.setBooking_date(booking.getBooking_date());
        dto.setStatus(booking.getPayment_status() != null ? booking.getPayment_status().name() : null);
        dto.setAttendee_name(booking.getAttendee() != null ? booking.getAttendee().getName() : null);
        dto.setEvent_title(booking.getTicketType() != null && booking.getTicketType().getEvent() != null
                ? booking.getTicketType().getEvent().getTitle() : null);
        dto.setTicket_type_name(booking.getTicketType() != null ? booking.getTicketType().getName() : null);
        dto.setPrice(booking.getTicketType() != null && booking.getTicketType().getPrice() != null
                ? booking.getTicketType().getPrice() : null);
        return dto;
    }
}
