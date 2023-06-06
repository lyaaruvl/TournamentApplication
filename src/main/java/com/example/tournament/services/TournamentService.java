package com.example.tournament.services;

import com.example.tournament.models.Team;
import com.example.tournament.models.Tournament;
import com.example.tournament.repositories.TeamRepo;
import com.example.tournament.repositories.TourRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TourRepo tournamentRepository;
    private final TeamRepo teamRepository;

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long id) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(id);

        if (tournamentOptional.isEmpty()) {
            throw new IllegalStateException("Tournament with id " + id + " does not exist.");
        }

        return tournamentOptional.get();
    }

    @Transactional
    public void addTeamToTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Tournament not found"));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        // Убедитесь, что команда еще не добавлена в турнир
        if (!tournament.getTeams().contains(team)) {
            tournament.getTeams().add(team);
            team.setTournament(tournament);

            tournamentRepository.save(tournament);
            teamRepository.save(team);
        } else {
            throw new IllegalStateException("Team already exists in the tournament");
        }
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }
}
