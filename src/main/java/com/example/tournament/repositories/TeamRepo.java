package com.example.tournament.repositories;

import com.example.tournament.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team, Long> {

}

