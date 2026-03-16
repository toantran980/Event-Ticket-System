package com.example.Event_Ticket_System.repository;

import com.example.Event_Ticket_System.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {

    // Find venues by name (case-insensitive, partial match)
    List<Venue> findByNameContainingIgnoreCase(String name);

    // Find Venues by city
    List<Venue> findByCity(String city);

    // Find venues with a total capacity greater than or equal to a specific value
    List<Venue> findByTotalCapacityGreaterThanEqual(Integer capacity);

    // Find venues with a total capacity less than a specific value
    List<Venue> findByTotalCapacityLessThan (Integer capacity);

    // Custom query: Find venues by city and minimum capacity
    @Query("SELECT v FROM Venue v WHERE v.city = :city AND v.total_capacity >= :capacity")
    List<Venue> findByCityAndMinimumCapacity(String city, Integer capacity);

    // Custom query: Find venues by partial address match
    @Query(value = "SELECT * FROM Venue WHERE address LIKE %:address%", nativeQuery = true)
    List<Venue> findByAddressContaining(String address);

    // Find a venue by name and city
    Optional<Venue> findByNameAndCity(String name, String city);

}
