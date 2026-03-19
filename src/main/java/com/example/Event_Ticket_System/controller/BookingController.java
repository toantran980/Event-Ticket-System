package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.dto.BookingResponseDTO;
import com.example.Event_Ticket_System.entity.Attendee;
import com.example.Event_Ticket_System.entity.Booking;
import com.example.Event_Ticket_System.repository.AttendeeRepository;
import com.example.Event_Ticket_System.repository.BookingRepository;
import com.example.Event_Ticket_System.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookTicket(
            // @RequestBody Booking book,
            @RequestParam Integer attendId,
            @RequestParam Integer ticketTypeId
    ) {
        BookingResponseDTO savedBooking = bookingService.bookTicket(attendId, ticketTypeId);
        BookingResponseDTO bookingDTO = new BookingResponseDTO(
                savedBooking.getBooking_reference(),
                savedBooking.getBooking_date(),
                savedBooking.getStatus(),
                savedBooking.getAttendee_name(),
                savedBooking.getEvent_title(),
                savedBooking.getTicket_type_name(),
                savedBooking.getPrice()
        );
        return ResponseEntity.status(201).body(bookingDTO);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(
            @PathVariable ("id") Integer bookingId
    ) {
        BookingResponseDTO updatedBooking = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(200).body(updatedBooking);
    }

}
