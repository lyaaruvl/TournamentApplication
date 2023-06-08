package com.example.tournament.Dao;

import com.example.tournament.models.Team;
import com.example.tournament.repositories.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamDao {
    private final TeamRepo teamRepo;

    public List<Team> findAllTeam(){
        return  teamRepo.findAll();
    }
    public Team findById(long id){
        return teamRepo.findById(id).orElseThrow(()-> new IllegalStateException("Team id " + id + " not found"));
    }
    public Team save(Team team){
        return teamRepo.save(team);
    }
}
