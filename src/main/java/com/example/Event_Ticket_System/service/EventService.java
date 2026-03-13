package com.example.Event_Ticket_System.service;

import com.example.Event_Ticket_System.dto.EventResponseDTO;
import com.example.Event_Ticket_System.dto.RevenueDTO;
import com.example.Event_Ticket_System.entity.Booking;
import com.example.Event_Ticket_System.entity.Event;
import com.example.Event_Ticket_System.entity.Organizer;
import com.example.Event_Ticket_System.entity.Venue;
import com.example.Event_Ticket_System.repository.BookingRepository;
import com.example.Event_Ticket_System.repository.EventRepository;
import com.example.Event_Ticket_System.repository.OrganizerRepository;
import com.example.Event_Ticket_System.repository.VenueRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    // By An Nguyen — needed for revenue calculation
    @Autowired
    private BookingRepository bookingRepository;

    // Is this public Event createEvent(EvenTDO event, Interger event_id) or
    @Transactional
    public Event createEvent(Event event, Integer organizer_id, Integer venue_id) {
        Organizer organizer = organizerRepository.findById(organizer_id)
                .orElseThrow(() -> new EntityNotFoundException("Organizer does not exist"));

        Venue venue = venueRepository.findById(venue_id)
                .orElseThrow(() -> new EntityNotFoundException("Venue does not exist"));

        event.setOrganizer(organizer);
        event.setVenue(venue);

        // OR
        //eventCreatedTDO.event.setTitle(dto.getTitle());.....

        return eventRepository.save(event);
    }

    public List<EventResponseDTO> getAllUpcomingEvents() {
        return eventRepository.findAll().stream().filter(
                event -> (Status.UPCOMING).equals(event.getStatus())
                )
                .map(
                event -> new EventResponseDTO(
                        event.getDescription(),
                        Collections.emptyList(), // not sure
                        event.getOrganizer().getName(),
                        event.getVenue().getName()
                )
        ).collect(Collectors.toList());
    }

    // By An Nguyen — GET /api/events/{id}/revenue
    // sums up ticket prices of all CONFIRMED bookings for this event
    public RevenueDTO getEventRevenue(Integer eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event " + eventId + " not found"));

        // grab all confirmed bookings (custom query in BookingRepository)
        List<Booking> confirmedBookings = bookingRepository.findConfirmedBookingsByEventId(eventId);

        // add up the prices — using long just in case numbers get big
        long totalRevenue = confirmedBookings.stream()
                .filter(b -> b.getTicketType() != null && b.getTicketType().getPrice() != null)
                .mapToLong(b -> b.getTicketType().getPrice().longValue())
                .sum();

        return new RevenueDTO(event.getTitle(), totalRevenue);
    }
}

