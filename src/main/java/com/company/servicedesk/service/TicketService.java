package com.company.servicedesk.service;

import com.company.servicedesk.dto.CreateTicketRequest;
import com.company.servicedesk.dto.TicketResponse;
import com.company.servicedesk.model.Ticket;
import com.company.servicedesk.repository.TicketRepository;
import org.springframework.stereotype.Service;
import com.company.servicedesk.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public TicketResponse createTicket(CreateTicketRequest request){

        Ticket ticket = new Ticket(
                request.getTitle(),
                request.getDescription(),
                request.getPriority()
        );

        Ticket saved = ticketRepository.save(ticket);

        return mapToResponse(saved);
    }

    public List<TicketResponse> getAllTickets(){
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse getTicketById(Long id){

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));

        return mapToResponse(ticket);
    }

    public void deleteTicket(Long id){
        ticketRepository.deleteById(id);
    }

    private TicketResponse mapToResponse(Ticket ticket){

        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCreatedAt()
        );
    }
    public TicketResponse updateStatus(Long id, String status){

    Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));

    ticket.setStatus(Enum.valueOf(com.company.servicedesk.model.Status.class, status));

    Ticket updated = ticketRepository.save(ticket);

    return mapToResponse(updated);
}

}