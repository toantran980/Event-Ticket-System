package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType,Integer> {
    List<TicketType> findByEventId(Integer eventId);

    // Find ticket types by name(case-insensitive, partial match)
    List<TicketType> findByNameContainingIgnoreCase(String name);

    // Find ticket types with a price less than or equal to a specific value
    List<TicketType> findByPriceLessThan(Double price);

    // Find ticket types wit ha specific quantity available
    List<TicketType> findByQuantityAvailable(Integer quantity);

    // Custom Query to find ticket types with low availability
    @Query("SELECT t FROM TicketType t WHERE t.quantity_available < :threshold")
    List<TicketType> findLowAvailabilityTickets(Integer threshold);

    // Custom query to find ticket types by event ID and price range
    @Query(value = "SELECT t FROM TicketType t WHERE t.event.event_id = :eventId AND t.price BETWEEN :minPrice AND :maxPrice")
    List<TicketType> findByEventIdAndPriceRange (Integer eventId, Double minPrice, Double maxPrice);

    // Find a ticket type by name and event ID
    Optional<TicketType> findByNameAndEventId(String name, Integer eventId);


}
