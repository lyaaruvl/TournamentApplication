package com.example.tournament.controllers;

import com.example.tournament.models.Tournament;
import com.example.tournament.services.TeamService;
import com.example.tournament.services.TournamentService;
import com.example.tournament.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/")
    public String homePage(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {

        model.addAttribute("user", userService.getUserByPrincipal(principal));


        List<Tournament> tournaments = tournamentService.getAllTournaments();
        model.addAttribute("tournaments", tournaments);

        return "main";
    }
}

