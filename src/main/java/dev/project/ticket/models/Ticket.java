package dev.project.ticket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket extends BaseEntity{

    @Column(unique = true)
    private String serialNumber;
    @Column(nullable = false)
    private double amount;
    private double surcharge;
    private double total;
    private String paymentInstitution;
    @Column(nullable = false)
    private String ticketPlace;
    @Column(nullable = false)
    private String plateId;
    @Column(nullable = false)
    private String breaker;
    @Column(nullable = false)
    private ZonedDateTime ticketDate;

    private ZonedDateTime createdDate;

    private ZonedDateTime paidDate;
    @Column(nullable = false)
    private String vehicleType;

    private boolean paid;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "laws", joinColumns = @JoinColumn(name = "ticket_id"))
    @Column(name = "law", nullable = false)
    private List<String> laws = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "official_id")
    private Official official;
}
