package com.example.Event_Ticket_System.dto;

import com.example.Event_Ticket_System.entity.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private String event_details;
    private List<TicketTypesDTO> ticket_types;
    private String organizer_name;
    private String venue_name;
}
