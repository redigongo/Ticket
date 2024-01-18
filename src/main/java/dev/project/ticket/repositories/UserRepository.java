package dev.project.ticket.repositories;


import dev.project.ticket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndEnabledTrue(String email);

    Optional<User> findByEmail(String email);
}
