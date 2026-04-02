package com.company.servicedesk.service;

import com.company.servicedesk.dto.CreateTicketRequest;
import com.company.servicedesk.dto.TicketResponse;
import com.company.servicedesk.exception.ResourceNotFoundException;
import com.company.servicedesk.model.Priority;
import com.company.servicedesk.model.Status;
import com.company.servicedesk.model.Ticket;
import com.company.servicedesk.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket("VPN Issue", "Cannot connect to VPN", Priority.HIGH);
        setField(ticket, "id", 1L);
        setField(ticket, "status", Status.OPEN);
        setField(ticket, "createdAt", LocalDateTime.now());
    }

    @Test
    void createTicket_shouldReturnTicketResponse() {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setTitle("VPN Issue");
        request.setDescription("Cannot connect to VPN");
        request.setPriority(Priority.HIGH);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketResponse response = ticketService.createTicket(request);

        assertNotNull(response);
        assertEquals("VPN Issue", response.getTitle());
        assertEquals(Priority.HIGH, response.getPriority());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void getAllTickets_shouldReturnListOfTickets() {
        when(ticketRepository.findAll()).thenReturn(List.of(ticket));

        List<TicketResponse> result = ticketService.getAllTickets();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("VPN Issue", result.get(0).getTitle());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void getTicketById_shouldReturnTicket_whenExists() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        TicketResponse response = ticketService.getTicketById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("VPN Issue", response.getTitle());
    }

    @Test
    void getTicketById_shouldThrowException_whenNotFound() {
        when(ticketRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> ticketService.getTicketById(99L));

        verify(ticketRepository, times(1)).findById(99L);
    }

    @Test
    void deleteTicket_shouldCallRepository() {
        doNothing().when(ticketRepository).deleteById(1L);

        ticketService.deleteTicket(1L);

        verify(ticketRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateStatus_shouldUpdateAndReturnTicket() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketResponse response = ticketService.updateStatus(1L, "IN_PROGRESS");

        assertNotNull(response);
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void updateStatus_shouldThrowException_whenTicketNotFound() {
        when(ticketRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> ticketService.updateStatus(99L, "IN_PROGRESS"));
    }

    // Helper to set private fields for testing
    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field: " + fieldName, e);
        }
    }
}