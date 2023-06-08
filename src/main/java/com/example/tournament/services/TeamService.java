package com.example.tournament.services;

import com.example.tournament.dao.TeamDao;
import com.example.tournament.dao.TournamentDao;
import com.example.tournament.dao.UserDao;
import com.example.tournament.exel.ExcelExporter;
import com.example.tournament.models.Team;
import com.example.tournament.models.Tournament;
import com.example.tournament.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final UserDao userDao;
    private final TournamentDao tournamentDao;
    private final TeamDao teamDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public List<Tournament> getAllTournaments() {
        return tournamentDao.findAll();
    }

    public Team saveTeam(Team team) {
        return teamDao.save(team);
    }

    public List<Team> getAllTeams() {
        return teamDao.findAll();
    }

    public Team getById(Long id) {
        return teamDao.findById(id);
    }

    public void exportTeams(String filePath) {
        List<Team> teams = teamDao.findAll();

        ExcelExporter excelExporter = new ExcelExporter();
        try {
            excelExporter.exportTeamsToExcel(teams, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
