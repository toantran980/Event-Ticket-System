package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType,Integer> {
}
