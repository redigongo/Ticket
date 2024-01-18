package dev.project.ticket.repositories;

import dev.project.ticket.models.Official;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficialRepository extends JpaRepository<Official,Long> {
}
