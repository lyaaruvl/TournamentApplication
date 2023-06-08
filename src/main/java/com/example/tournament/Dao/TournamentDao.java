package com.example.tournament.Dao;
import com.example.tournament.models.Tournament;
import com.example.tournament.repositories.TourRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TournamentDao {
    private final TourRepo tourRepo;

    public List<Tournament> findAll(){
        return tourRepo.findAll();
    }

    public Tournament findById(long id){
        return tourRepo.findById(id).orElseThrow(()-> new IllegalStateException("Tournament id " + id + " not found"));
    }

    public Tournament save(Tournament tournament){
        return tourRepo.save(tournament);
    }
}
