package com.example.tournament.services;

import com.example.tournament.dao.TeamDao;
import com.example.tournament.dao.TournamentDao;
import com.example.tournament.models.Team;
import com.example.tournament.models.Tournament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TournamentServiceTest {

    TeamDao teamDao;
    TournamentDao tournamentDao;
    TournamentService tournamentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        teamDao = Mockito.mock(TeamDao.class);
        tournamentDao = Mockito.mock(TournamentDao.class);
        tournamentService = new TournamentService(teamDao, tournamentDao);
    }

    @Test
    @DisplayName("Проверяем добавление команды на соревнование")
    public void should_add_team_to_tournament() {
        Tournament tournament = Tournament.builder()
                .teams(new ArrayList<>())
                .id(1L)
                .build();
        Team team = Team.builder()
                .id(2L)
                .name("Team")
                .build();
        when(teamDao.findById(2L)).thenReturn(team);
        when(tournamentDao.findById(1L)).thenReturn(tournament);
        when(teamDao.save(team)).thenReturn(team);
        when(tournamentDao.save(tournament)).thenReturn(tournament);
        tournamentService.addTeamToTournament(1L, 2L);
        assertEquals(tournament.getTeams().size(), 1);
        assertEquals(tournament.getTeams().get(0).getId(), 2L);
    }

}