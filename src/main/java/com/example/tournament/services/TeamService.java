package com.example.tournament.services;

import com.example.tournament.exel.ExcelExporter;
import com.example.tournament.models.Team;
import com.example.tournament.models.Tournament;
import com.example.tournament.models.User;
import com.example.tournament.repositories.TeamRepo;
import com.example.tournament.repositories.TourRepo;
import com.example.tournament.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final UserService userService;
    private final TeamRepo teamRepository;
    private final TourRepo tournamentRepository;
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public void exportTeams(String filePath) {
        List<Team> teams = teamRepository.findAll();

        ExcelExporter excelExporter = new ExcelExporter();
        try {
            excelExporter.exportTeamsToExcel(teams, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
