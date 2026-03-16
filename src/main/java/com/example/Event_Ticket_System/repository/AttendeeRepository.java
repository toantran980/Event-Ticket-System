package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.Attendee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee,Integer> {
    Optional<Attendee> findByEmail(String email);

    boolean existsByEmail (String email);

    List<Attendee> findByName (String name);

    Page<Attendee> findALL (Pageable pageable);

    List<Attendee> findByNameContaining (String name , Sort sort);

    // Count attendees by name
    long countByName(String name);


    // Find attendees by event ID
    @Query("SELECT a FROM Attendee a WHERE a.event.event_id = :eventId")
    List<Attendee> findByEventId(@Param("eventId") Integer eventId);
}
