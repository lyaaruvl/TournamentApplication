package com.example.tournament.services;

import com.example.tournament.dao.TeamDao;
import com.example.tournament.dao.TournamentDao;
import com.example.tournament.models.Team;
import com.example.tournament.models.Tournament;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TeamDao teamDao;
    private final TournamentDao tournamentDao;

    public List<Tournament> getAllTournaments() {
        return tournamentDao.findAll();
    }

    public Tournament getTournamentById(Long id) {
        return tournamentDao.findById(id);
    }

    @Transactional
    public void addTeamToTournament(Long tournamentId, Long teamId) {
        Tournament tournament = tournamentDao.findById(tournamentId);
        Team team = teamDao.findById(teamId);

        // Убедитесь, что команда еще не добавлена в турнир
        if (!tournament.getTeams().contains(team)) {
            tournament.getTeams().add(team);
            team.setTournament(tournament);

            tournamentDao.save(tournament);
            teamDao.save(team);
        } else {
            throw new IllegalStateException("Team already exists in the tournament");
        }
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentDao.save(tournament);
    }
}
