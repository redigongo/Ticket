package dev.project.ticket.controllers;

import dev.project.ticket.DTO.TicketDTO;
import dev.project.ticket.exceptions.NotFoundException;
import dev.project.ticket.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TICKET")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.findAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<?> getTicketBySerialNumber(@PathVariable String serialNumber) {
        try {

            TicketDTO ticketDTO = ticketService.findBySerialNumber(serialNumber);

            return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>("Fleta e gjobes me numer serial: " + serialNumber + " nuk u gjet!",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<String> saveUpdateTicket(@RequestBody TicketDTO ticketDTO) {
        try {
            String result = ticketService.saveUpdate(ticketDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Dicka shkoj keq, provo perseri!\n" + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<String> payTicket(@RequestBody TicketDTO ticketDTO) {
        String result = ticketService.payTicket(ticketDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
