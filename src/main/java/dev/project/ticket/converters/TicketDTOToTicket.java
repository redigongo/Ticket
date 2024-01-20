package dev.project.ticket.converters;

import dev.project.ticket.DTO.TicketDTO;
import dev.project.ticket.models.Official;
import dev.project.ticket.models.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TicketDTOToTicket implements Converter<TicketDTO,Ticket> {
    @Override
    public Ticket convert(TicketDTO source) {
        if (source!=null){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

            Ticket ticket = new Ticket();
            ticket.setId(source.getId());
            ticket.setSerialNumber(source.getSerialNumber());
            ticket.setAmount(source.getAmount());
            ticket.setTicketPlace(source.getTicketPlace());
            ticket.setPlateId(source.getPlateId());
            ticket.setBreaker(source.getBreaker());
            ticket.setPaid(source.isPaid());
            ticket.setPaymentInstitution(source.getPaymentInstitution());

            ZonedDateTime tickedDate = ZonedDateTime.parse(source.getTicketDate(),formatter);
            ZonedDateTime createdDate = ZonedDateTime.now();

            ticket.setTicketDate(tickedDate);
            ticket.setCreatedDate(createdDate);

            ticket.setVehicleType(source.getVehicleType());

            ticket.setLaws(source.getLaws());

            Official official = new Official();
            official.setId(source.getOfficialDto().getId());

            ticket.setOfficial(official);

            return ticket;
        }
        return null;
    }
}
