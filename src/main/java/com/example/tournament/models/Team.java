package com.example.tournament.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "teams")
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Имя команды не может быть пустым")
    @Size(min = 3, max = 50, message = "Имя команды должно быть от 3 до 50 символов")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Участники команды не могут быть null")
    @Size(min = 2, max = 10, message = "Команда должна состоять от 2 до 10 участников")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_user",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> members;

    @NotNull(message = "Турнир не может быть null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public User getCreator() {
        if (members != null && !members.isEmpty()) {
            return members.get(0);
        }
        return null;
    }
}
