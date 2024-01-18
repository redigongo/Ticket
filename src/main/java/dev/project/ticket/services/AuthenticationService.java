package dev.project.ticket.services;

import dev.project.ticket.DTO.LoginDTO;
import dev.project.ticket.DTO.LoginResponse;
import dev.project.ticket.models.User;
import dev.project.ticket.repositories.UserRepository;
import dev.project.ticket.utils.JwtUtils;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private HttpSession session;

    private final AuthenticationProvider authenticationManager;

    public Object login(LoginDTO loginDTO) {

        LoginResponse loginResponse;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateAccessToken(userRepository.findByEmailAndEnabledTrue(loginDTO.getEmail()));
            loginResponse = setUserData(userRepository.findByEmailAndEnabledTrue(loginDTO.getEmail()), token);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Login failed ! Invalid email or password.");
        } catch (Exception e) {
            throw new InternalError("We encountered an issue while processing your request. Please try again later.Thank you for your understanding.");
        }
    }

    public LoginResponse setUserData(User user, String token) {
        LoginResponse apiResponse = new LoginResponse();

        apiResponse.setId(user.getId());
        apiResponse.setEmail(user.getEmail());
        apiResponse.setFirstName(user.getFirstName());
        apiResponse.setLastName(user.getLastName());
        apiResponse.setRole(user.getRole().getName());
        apiResponse.setAccessToken(token);

        return apiResponse;
    }

    public void logout(String id) {
        try {
            Long parseId = Long.parseLong(id);
            if (parseId != null && userRepository.existsById(parseId)) {
                session.invalidate();
                SecurityContextHolder.clearContext();
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("User id: \"" + id + "\" can't be parsed!");
        }
    }
}
