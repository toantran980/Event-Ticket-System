package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByStatus(Event.Status status); // maybe make Status class as public under Entity
}
