package com.example.tournament.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tournaments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Имя турнира не может быть пустым")
    @Size(min = 3, max = 50, message = "Имя турнира должно быть от 3 до 50 символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Место проведения турнира не может быть пустым")
    @Size(min = 3, max = 100, message = "Место проведения турнира должно быть от 3 до 100 символов")
    @Column(name = "location")
    private String location;

    @NotEmpty(message = "Дата начала турнира не может быть пустой")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Дата начала турнира должна быть в формате YYYY-MM-DD")
    @Column(name = "start_date")
    private String startDate;

    @NotEmpty(message = "Дата окончания турнира не может быть пустой")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Дата окончания турнира должна быть в формате YYYY-MM-DD")
    @Column(name = "end_date")
    private String endDate;

    @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Team> teams;

    public Tournament(String name, String location, String startDate, String endDate) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}