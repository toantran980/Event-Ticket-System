package com.example.Event_Ticket_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypesDTO {
    private Integer ticket_type_id;
    private String ticket_name;
    private Double price;
    private Integer ticket_quantity;
}
