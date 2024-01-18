package dev.project.ticket.Loader;

import dev.project.ticket.models.Official;
import dev.project.ticket.models.Role;
import dev.project.ticket.models.User;
import dev.project.ticket.repositories.OfficialRepository;
import dev.project.ticket.repositories.RoleRepository;
import dev.project.ticket.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoadData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final OfficialRepository officialRepository;


    @Override
    public void run(String... args) {
        saveUsers();
        saveOfficial();
    }

    private void saveOfficial() {
        if (officialRepository.count() == 0){

            Official official = new Official();
            official.setName("Oficeri 1");
            official.setOfficialCode("AL115");
            official.setBirthDate("01/01/1990");
            officialRepository.save(official);
        }
    }

    private void saveUsers() {

        if (userRepository.count() == 0) {
            Role savedAdminRole = roleRepository.save(new Role("ADMIN"));

            User userAdmin = new User();
            userAdmin.setEmail("admin@gmail.com");
            userAdmin.setFirstName("Admin");
            userAdmin.setLastName("Admin");
            userAdmin.setUsername("admin");

            String encodedPassword = bCryptPasswordEncoder.encode("admin");
            userAdmin.setPassword(encodedPassword);

            userAdmin.setRole(savedAdminRole);
            userAdmin.setEnabled(true);
            userAdmin.setAccountNonExpired(true);
            userAdmin.setAccountNonLocked(true);
            userAdmin.setCredentialsNonExpired(true);
            userRepository.save(userAdmin);

        }
    }
}
