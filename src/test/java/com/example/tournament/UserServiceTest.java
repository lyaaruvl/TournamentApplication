package com.example.tournament.services;

import com.example.tournament.dao.TeamDao;
import com.example.tournament.dao.TournamentDao;
import com.example.tournament.dao.UserDao;
import com.example.tournament.models.Team;
import com.example.tournament.models.Tournament;
import com.example.tournament.models.User;
import com.example.tournament.models.enums.Role;
import com.example.tournament.repositories.TeamRepo;
import com.example.tournament.repositories.TourRepo;
import com.example.tournament.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    UserService userService;
    UserRepository userRepository;
    TeamRepo teamRepo;
    TourRepo tourRepo;
    TeamService teamService;
    TournamentService tournamentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        userRepository = Mockito.mock(UserRepository.class);
        //userService = new UserService(userRepository, new BCryptPasswordEncoder(8));
        userService = new UserService(new BCryptPasswordEncoder(8), new UserDao(userRepository));
        teamService = new TeamService(new UserDao(userRepository), new TournamentDao(), new TeamDao());
        tournamentService = new TournamentService(new TeamDao(), new TournamentDao());
        teamRepo = Mockito.mock(TeamRepo.class);
        tourRepo = Mockito.mock(TourRepo.class);
    }

    @Test
    @DisplayName("Проверяем, что пользователь создался корректно")
    public void should_create_user() {
        String email = "email@com.ru";
        String password = "password";
        User user = User.builder()
                .id(1L)
                .password(password)
                .email(email)
                .active(false)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        boolean result = userService.createUser(user);

        assertEquals(user.getId(), 1L);
        assertEquals(user.getRoles().size(), 1);
        assertNotEquals(user.getPassword(), password);
        assertTrue(user.isActive());
        assertTrue(user.getRoles().contains(Role.ROLE_USER));
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверяем, что пользователь забанен")
    public void should_ban_user(){
        String email = "email@com.ru";

        User user = User.builder()
                .id(1L)
                .email(email)
                .active(false)
                .password("password")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        boolean result = userService.createUser(user);

        assertEquals(user.getId(), 1L);
        assertEquals(user.getEmail(),"email@com.ru");
        assertTrue(user.isActive());
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверяем добавление команды в турнир")
    public void should_add_tournament(){
        Team team = Team.builder().id(1L).name("team").build();
        Tournament tournament = Tournament.builder().id(1L).teams(List.of(team)).build();

        when(teamRepo.save(team)).thenReturn(team);
        when(tourRepo.save(tournament)).thenReturn(tournament);

        assertEquals(tournament.getId(), 1L);
        assertEquals(team.getId(), 1L);
        assertTrue(tournament.getTeams().contains(team));
    }
}
