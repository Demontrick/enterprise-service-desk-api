package com.company.servicedesk.controller;

import com.company.servicedesk.dto.CreateTicketRequest;
import com.company.servicedesk.dto.TicketResponse;
import com.company.servicedesk.service.TicketService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping
   public TicketResponse createTicket(@Valid @RequestBody CreateTicketRequest request){
        return ticketService.createTicket(request);
    }

    @GetMapping
    public List<TicketResponse> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketResponse getTicket(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }
    @PatchMapping("/{id}/status")
public TicketResponse updateStatus(
        @PathVariable Long id,
        @RequestParam String status){

    return ticketService.updateStatus(id,status);
}

}