package com.example.Event_Ticket_System.controller;

import com.example.Event_Ticket_System.dto.AttendeeBookingsDTO;
import com.example.Event_Ticket_System.dto.BookingResponseDTO;
import com.example.Event_Ticket_System.entity.Attendee;
import com.example.Event_Ticket_System.repository.AttendeeRepository;
import com.example.Event_Ticket_System.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @PostMapping
    public ResponseEntity<AttendeeBookingsDTO> createAttendee(
            @RequestBody Attendee attendee
    ) {
        Attendee savedAttendee = attendeeService.createAttendee(attendee);
        // not sure if this the great idea
        List<BookingResponseDTO> booking = new ArrayList<>();

        AttendeeBookingsDTO attendeeBookingsDTO = new AttendeeBookingsDTO(
                savedAttendee.getAttendee_id(),
                savedAttendee.getName(),
                savedAttendee.getEmail(),
                booking
        );
        return ResponseEntity.status(201).body(attendeeBookingsDTO);
    }

}
