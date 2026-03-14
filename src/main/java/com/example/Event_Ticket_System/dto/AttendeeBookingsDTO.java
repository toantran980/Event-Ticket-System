package com.example.Event_Ticket_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeBookingsDTO {
    private Integer attendee_id;
    private String attendee_name;
    private String attendee_email;
    List<BookingResponseDTO> bookings;
}
