package com.example.tournament.repositories;

import com.example.tournament.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepo extends JpaRepository<Tournament, Long> {
}

