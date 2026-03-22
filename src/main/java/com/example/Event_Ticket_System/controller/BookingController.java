package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.dto.BookingResponseDTO;
import com.example.Event_Ticket_System.entity.Booking;
import com.example.Event_Ticket_System.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    // POST /api/bookings
    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookTicket(@RequestBody Booking booking) {
        BookingResponseDTO savedBooking = bookingService.bookTicket(
                booking.getAttendee().getAttendee_id(),
                booking.getTicketType().getTicket_type_id()
        );
        return ResponseEntity.status(201).body(savedBooking);
    }

    // PUT /api/bookings/{id}/cancel
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(
            @PathVariable ("id") Integer bookingId
    ) {
        BookingResponseDTO updatedBooking = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(200).body(updatedBooking);
    }
}