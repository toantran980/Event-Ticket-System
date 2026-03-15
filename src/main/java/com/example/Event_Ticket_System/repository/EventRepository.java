package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByStatus(Event.Status status); // maybe make Status class as public under Entity

    // Find events by title (case-insensitive)
    List<Event> findByTitleIgnoreCase(String title);

    // Find events by organizedID
    List<Event> findByOrganizerOrganizerId(Integer organizerId);

    // Find events by venue ID
    List<Event> findByVenueVenueId (Integer venueId);

    // Find events within a specific date range
    List<Event> findByEventDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find events by status and date range
    List<Event> findByStatusAndEventDateBetween(Event.Status status, LocalDateTime startDate, LocalDateTime endDate);

    // Custom Query: Find all upcoming events after a specific date
    @Query("SELECT e FROM Event e WHERE e.status = :status AND e.event_date >= :date")
    List<Event> findUpcomingEventsAfterDate(@Param("status") Event.Status status, @Param("date") LocalDateTime date);

    //Paginated query: Find Events by status
    Page<Event> findByStatus(Event.Status status, Pageable pageable);

    //Paginated Query: Find events within a specific date range
    Page<Event> findByEventDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}

