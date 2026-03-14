package com.example.Event_Ticket_System.service;

import com.example.Event_Ticket_System.dto.EventResponseDTO;
import com.example.Event_Ticket_System.dto.RevenueDTO;
import com.example.Event_Ticket_System.dto.TicketTypesDTO;
import com.example.Event_Ticket_System.entity.*;
import com.example.Event_Ticket_System.repository.*;
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

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Transactional
    public Event createEvent(Event event, Integer organizer_id, Integer venue_id) {
        Organizer organizer = organizerRepository.findById(organizer_id)
                .orElseThrow(() -> new EntityNotFoundException("Organizer does not exist"));

        Venue venue = venueRepository.findById(venue_id)
                .orElseThrow(() -> new EntityNotFoundException("Venue does not exist"));

        event.setOrganizer(organizer);
        event.setVenue(venue);

        return eventRepository.save(event);
    }

    public EventResponseDTO getEventByIdWithTicketTypes(Integer event_id) {
        Event event = eventRepository.findById(event_id)
                .orElseThrow(() -> new RuntimeException("Event does not exist"));
        List<TicketType> tickets = ticketTypeRepository.findByEventEventId(event_id);
        List<TicketTypesDTO> ticket_dto = tickets.stream()
                .map(ticket -> new TicketTypesDTO(
                        ticket.getTicket_type_id(),
                        ticket.getName(),
                        ticket.getPrice(),
                        ticket.getQuantity_available()
                )).toList();

        return new EventResponseDTO(
                event.getEvent_id(),
                event.getTitle(),
                event.getDescription(),
                event.getEvent_date(),
                event.getStatus().name(),
                event.getOrganizer().getName(),
                event.getVenue().getName(),
                ticket_dto
        );
    }

    public List<EventResponseDTO> getAllUpcomingEvents(){

        return eventRepository.findByStatus(Event.Status.UPCOMING)
                .stream()
                .map(event -> new EventResponseDTO(
                        event.getEvent_id(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getEvent_date(),
                        event.getStatus().name(),
                        event.getOrganizer().getName(),
                        event.getVenue().getName(),
                        Collections.emptyList() // need change later
                ))
                .toList();
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
                .mapToLong(b -> b.getTicketType().getPrice())
                .sum();

        return new RevenueDTO(event.getTitle(), totalRevenue);
    }
}
