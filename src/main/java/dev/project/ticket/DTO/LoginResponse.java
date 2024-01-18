package dev.project.ticket.DTO;

import lombok.Data;

@Data
public class LoginResponse {
    private Long id;
    private String accessToken;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
