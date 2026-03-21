package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType,Integer> {
    @Query("SELECT t FROM TicketType t WHERE t.event.event_id = :eventId")
    List<TicketType> findByEventId(Integer eventId);

}
