package dev.project.ticket.DTO;

import lombok.Data;

@Data
public class PaymentDTO {
    private String serialNumber;
    private double amount;
    private double surcharge;
    private String paymentInstitution;

}
