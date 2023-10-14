package com.example.demo.tournament.repository.api;
import com.example.demo.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface TournamentRepository extends JpaRepository<Tournament, UUID> {
    Optional<Tournament> findById(UUID id);
}
