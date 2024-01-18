package dev.project.ticket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "official")
public class Official extends BaseEntity{

    private String name;
    @Column(unique = true)
    private String officialCode;

    private String birthDate;

    @OneToMany(mappedBy = "official", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
