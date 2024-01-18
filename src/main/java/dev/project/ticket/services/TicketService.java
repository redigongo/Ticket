package dev.project.ticket.services;

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
                .peek(ticketDTO -> ticketDTO.setAmount(checkAmount(ticketDTO)))
                .collect(Collectors.toList());
    }

    public String saveUpdate(TicketDTO ticketDTO){
        Ticket ticket = ticketRepository.save(toTicket.convert(ticketDTO));

        if (ticket.getId()!=null && ticketDTO.getId()!=null){
            return "Fleta e gjobes u ndryshua me sukses";
        }
        if (ticket.getId()!=null){
            return "Fleta e gjobes u ruajt me sukses";
        }
        throw new RuntimeException();
    }

    public String payTicket(TicketDTO ticketDTO){
        if (ticketDTO.getAmount()!= checkAmount(ticketDTO)){
            return "Vlera e gjobes nuk eshte e sakte.\nVlera e gjobes me numer serial: " + ticketDTO.getSerialNumber() + " eshte: " + checkAmount(ticketDTO);
        }

        Ticket ticket = toTicket.convert(ticketDTO);
        ticket.setPaid(true);

        ticketRepository.save(ticket);

        return "Pagesa u krye me sukses";

    }

    public TicketDTO findBySerialNumber(String serialNumber) {

        Ticket ticket = ticketRepository.findBySerialNumber(serialNumber).orElseThrow(() ->
                new NotFoundException("Gjoba me numer serial: " + serialNumber + " nuk u gjet!"));

        if (ticket==null){
            return null;
        }

        TicketDTO ticketDTO = toTicketDTO.convert(ticket);
        ticketDTO.setAmount(checkAmount(ticketDTO));

        return ticketDTO;
    }


    private double checkAmount(TicketDTO ticket) {
        double amount = ticket.getAmount();

        ZonedDateTime dateToday = ZonedDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        ZonedDateTime tickedDate = ZonedDateTime.parse(ticket.getTicketDate(),formatter);

        int numberOfDaysBetween = dateToday.getDayOfYear() - tickedDate.getDayOfYear();

        if (numberOfDaysBetween <= 15) {
            return amount * 0.5;
        }

        int exceededDays = numberOfDaysBetween - 15;
        double penalty = ticket.getAmount() * 0.02;

        for (int i = 1; i <= exceededDays; i++) {
            amount += amount + penalty;
        }
        return amount;
    }

}
