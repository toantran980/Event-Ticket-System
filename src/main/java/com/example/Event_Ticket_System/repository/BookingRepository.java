package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.Booking;
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
}
