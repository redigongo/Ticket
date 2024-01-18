package dev.project.ticket.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OfficialDTO {

    private Long id;
    private String name;
    private String officialCode;
    private String birthDate;
}
