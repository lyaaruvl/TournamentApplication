package com.example.tournament.controllers;

import com.example.tournament.models.Tournament;
import com.example.tournament.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    private TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String getTournaments(Model model) {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        model.addAttribute("tournaments", tournaments);
        return "tournaments";
    }
    @GetMapping("/create")
    public String createTournaments(Model model) {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        model.addAttribute("tournaments", tournaments);
        return "createTour";
    }
    @GetMapping("/{id}")
    public String getTournament(@PathVariable Long id, Model model) {
        Tournament tournament = tournamentService.getTournamentById(id);
        model.addAttribute("tournament", tournament);
        return "tour-info";
    }

    @PostMapping("/create")
    public String createTournament(@Valid Tournament tournament, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            List<Tournament> tournaments = tournamentService.getAllTournaments();
            model.addAttribute("tournaments", tournaments);
            return "createTour";
        }

        tournamentService.createTournament(tournament);
        return "redirect:/tournaments";
    }

}
