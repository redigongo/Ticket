package dev.project.ticket.DTO;
import lombok.Data;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Data
public class TicketDTO {

    private Long id;

    private String serialNumber;
    private boolean paid;
    private double amount;
    private double surcharge;
    private double total;
    private String paymentInstitution;
    private String ticketPlace;
    private String plateId;
    private String breaker;
    private String ticketDate;
    private String createdDate;
    private String paidDate;
    private String vehicleType;
    private List<String> laws;
    private OfficialDTO officialDto;


    public void calculateTotal() {

        this.surcharge = checkSurcharge(this.amount, this.ticketDate);
        this.total = this.surcharge == 0 ? this.amount / 2 : this.amount + this.surcharge;
    }

    private double checkSurcharge(double amount, String ticketDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        ZonedDateTime ticketDateFormatted = ZonedDateTime.parse(ticketDate,formatter);

        ZonedDateTime dateToday = ZonedDateTime.now();

        int numberOfDaysBetween = dateToday.getDayOfYear() - ticketDateFormatted.getDayOfYear();

        if (numberOfDaysBetween <= 15) {
            return 0;
        }

        int exceededDays = numberOfDaysBetween - 15;
        double penalty = exceededDays * amount * 0.02;

        return penalty;
    }
}
