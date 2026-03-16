package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

    // Find by name (exact matches)
    List<Organizer> findByName(String name);

    // Find by name (case-insensitive, partial match)
    List<Organizer> findByNameContainingIgnoreCase(String name);

    // Find by email (exact match)
    Optional<Organizer> findByEmail(String email);

    // Find by phone (exact match)
    Optional<Organizer> findByPhone(String phone);

    // Custom query to find organizers by name or email
    @Query("SELECT o FROM Organizer o WHERE o.name LIKE %:keyword% OR o.email LIKE %:keyword%")
    List<Organizer> searchByNameOrEmail(String keyword);
}
