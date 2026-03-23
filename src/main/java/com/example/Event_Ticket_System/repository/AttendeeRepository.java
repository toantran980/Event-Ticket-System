package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee,Integer> {
    Optional<Attendee> findByEmail(String email);
}
