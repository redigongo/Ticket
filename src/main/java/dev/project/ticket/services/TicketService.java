package dev.project.ticket.services;

import dev.project.ticket.DTO.PaymentDTO;
import dev.project.ticket.DTO.TicketDTO;
import dev.project.ticket.converters.TicketDTOToTicket;
import dev.project.ticket.converters.TicketToTicketDTO;
import dev.project.ticket.exceptions.NotFoundException;
import dev.project.ticket.models.Ticket;
import dev.project.ticket.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketToTicketDTO toTicketDTO;
    private final TicketDTOToTicket toTicket;


    public List<TicketDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(toTicketDTO::convert)
                .peek(ticketDTO -> ticketDTO.calculateTotal())
                .collect(Collectors.toList());
    }

    public String saveUpdate(TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.save(toTicket.convert(ticketDTO));

        if (ticket.getId() != null && ticketDTO.getId() != null) {
            return "Fleta e gjobes u ndryshua me sukses";
        }
        if (ticket.getId() != null) {
            return "Fleta e gjobes u ruajt me sukses";
        }
        throw new RuntimeException();
    }

    public String payTicket(PaymentDTO paymentDTO) {

            double total = paymentDTO.getAmount() + paymentDTO.getSurcharge();

            TicketDTO ticketDTO = findBySerialNumber(paymentDTO.getSerialNumber());

            if (ticketDTO.isPaid()) {
                return "Gjoba me numer serial: " + paymentDTO.getSerialNumber() + " eshte e paguar!";
            }

            if (total != ticketDTO.getTotal()) {
                return "Vlera e gjobes nuk eshte e sakte.\nVlera e gjobes me numer serial: " + paymentDTO.getSerialNumber() + " eshte: " + ticketDTO.getTotal() + " dhe kamata: " + ticketDTO.getSurcharge();
            }

            Ticket ticket = toTicket.convert(ticketDTO);
            ticket.setPaid(true);
            ticket.setPaidDate(ZonedDateTime.now());
            ticket.setPaymentInstitution(paymentDTO.getPaymentInstitution());

            ticketRepository.save(ticket);

            return "Pagesa u krye me sukses";

    }

    public TicketDTO findBySerialNumber(String serialNumber) {

        Ticket ticket = ticketRepository.findBySerialNumber(serialNumber).orElseThrow(() ->
                new NotFoundException("Gjoba me numer serial: " + serialNumber + " nuk u gjet!"));

        if (ticket == null) {
            return null;
        }

        TicketDTO ticketDTO = toTicketDTO.convert(ticket);
        ticketDTO.calculateTotal();

        return ticketDTO;
    }


}
