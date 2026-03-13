package com.example.Event_Ticket_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypesDTO {
    private String ticket_type;
    private Integer price;
    private Integer ticket_quantity;
}
