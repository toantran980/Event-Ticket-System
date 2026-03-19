package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.dto.BookingResponseDTO;
import com.example.Event_Ticket_System.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// By An Nguyen — added this query for revenue calculation
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // gets all CONFIRMED bookings linked to an event (Booking -> TicketType -> Event)
    @Query("SELECT b FROM Booking b " +
           "WHERE b.ticketType.event.event_id = :eventId " +
           "AND b.payment_status = 'CONFIRMED'")
    List<Booking> findConfirmedBookingsByEventId(@Param("eventId") Integer eventId);

    //Calculate total revenue for an event
    @Query("SELECT SUM(b.ticketType.price) FROM Booking b " +
            "WHERE b.ticketType.event.event_id = :eventId " +
            "AND b.payment_status = 'CONFIRMED'")
    Double calculateTotalRevenueByEventID(@Param("eventID") Integer eventID);

    // Get all booking for a specific attendee and include event title in response
    @Query("SELECT b FROM Booking b " +
            "JOIN FETCH b.attendee a " +
            "JOIN FETCH b.booking_id i " +
            "JOIN FETCH b.booking_reference r " +
            "WHERE a.attendee_id = :attendeeId")
    List<Booking> findBookingsByAttendeeId(@Param("attendeeId") Integer attendeeId);

    //Get all bookings for a specific ticket type
    List<Booking> findByTicketTypeId(Integer ticketTypeId);

    // Counts confirmed bookings for an event
    @Query("SELECT COUNT(b) FROM Booking b " +
            "WHERE b.ticketType.event.event_id = :eventId " +
            "AND b.payment_status = 'CONFIRMED'")
    Long countConfirmedBookingsByEventID(@Param("eventId") Integer eventId);

    // Gets bookings for an event with a specific status
    @Query("SELECT b FROM Booking b " +
    "WHERE b.ticketType.event.event_id = :eventId " +
    "AND b.payment_status = :status")
    List<Booking> findBookingsByEventIdAndStatus(@Param("eventId") Integer eventId, @Param("status") String status);

    // Paginated confirmed bookings for an event
    @Query("SELECT b FROM Booking b " +
    "WHERE b.ticketType.event.event_id = :eventId " +
    "AND b.payment_status = 'CONFIRMED'")
    Page<Booking> findConfirmedBookingsByEventId(@Param("eventId") Integer eventId, Pageable pageable);

}
