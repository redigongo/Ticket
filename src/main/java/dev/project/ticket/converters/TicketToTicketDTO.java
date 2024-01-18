package dev.project.ticket.converters;

import dev.project.ticket.DTO.TicketDTO;
import dev.project.ticket.models.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class TicketToTicketDTO implements Converter<Ticket, TicketDTO> {

    private final OfficialToOfficialDTO toOfficialDto;

    @Override
    public TicketDTO convert(Ticket source) {
        if (source != null) {

            TicketDTO ticketDTO = new TicketDTO();

            ticketDTO.setId(source.getId());
            ticketDTO.setSerialNumber(source.getSerialNumber());
            ticketDTO.setAmount(source.getAmount());
            ticketDTO.setTicketPlace(source.getTicketPlace());
            ticketDTO.setPlateId(source.getPlateId());
            ticketDTO.setBreaker(source.getBreaker());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

            String tickedDateString = formatter.format(source.getTicketDate());
            String createdDateString = formatter.format(source.getCreatedDate());

            ticketDTO.setTicketDate(tickedDateString);
            ticketDTO.setCreatedDate(createdDateString);

            ticketDTO.setVehicleType(source.getVehicleType());
            ticketDTO.setLaws(source.getLaws());

            ticketDTO.setOfficialDto(toOfficialDto.convert(source.getOfficial()));

            return ticketDTO;
        }
        return null;
    }
}
