package com.example.Event_Ticket_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    private String booking_reference;
    private LocalDateTime booking_date;
    private String status;
    private String attendee_name;
    private String event_title;
    private String ticket_type_name;
    private Long price;
}
