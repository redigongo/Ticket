package dev.project.ticket.DTO;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.ZonedDateTime;
import java.util.List;
@Data
public class TicketDTO {

    private Long id;
    
    private String serialNumber;
    private double amount;
    private String ticketPlace;
    private String plateId;
    private String breaker;
    private String ticketDate;
    private String createdDate;
    private String vehicleType;
    private List<String> laws;
    private OfficialDTO officialDto;
}
