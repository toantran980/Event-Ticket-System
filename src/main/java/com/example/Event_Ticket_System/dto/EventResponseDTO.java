package com.example.Event_Ticket_System.dto;

import com.example.Event_Ticket_System.entity.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {

    private Integer event_id;

    private String title;

    private String description;

    private LocalDateTime event_date;

    private String status;

    private String organizer_name;

    private String venue_name;

    private List<TicketTypesDTO> ticket_types;
}
