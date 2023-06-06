package com.example.tournament.controllers;

import com.example.tournament.models.Team;
import com.example.tournament.models.Tournament;
import com.example.tournament.models.User;
import com.example.tournament.services.TeamService;
import com.example.tournament.services.TournamentService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    private final TournamentService tournamentService;

    private static final String EMAIL_USERNAME = "mireasportfamily@yandex.ru";
    private static final String EMAIL_PASSWORD = "nnwywizlsbbhjaoj";
    private static final String SMTP_HOST = "smtp.yandex.ru";
    private static final int SMTP_PORT = 587;


    @Autowired
    public TeamController(TeamService teamService, TournamentService tournamentService) {
        this.teamService = teamService;
        this.tournamentService = tournamentService;
    }


    @GetMapping
    public String TeamForm(Model model) {
        List<Tournament> tournaments = teamService.getAllTournaments();
        List<User> users = teamService.getAllUsers();
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("users", users);
        model.addAttribute("tournaments", tournaments);
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/create")
    public String createTeamForm(Model model) {
        List<Tournament> tournaments = teamService.getAllTournaments();
        List<User> users = teamService.getAllUsers();
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("users", users);
        model.addAttribute("tournaments", tournaments);
        model.addAttribute("teams", teams);
        return "createTeam";
    }

    @PostMapping("/create")
    public String createTeam(@Valid @ModelAttribute("team") Team team, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            List<Tournament> tournaments = teamService.getAllTournaments();
            List<User> users = teamService.getAllUsers();
            List<Team> teams = teamService.getAllTeams();
            model.addAttribute("users", users);
            model.addAttribute("tournaments", tournaments);
            model.addAttribute("teams", teams);
            return "createTeam";
        }
        teamService.saveTeam(team);
        sendRegistrationEmailToAdmin(team);
        return "redirect:/teams";
    }

    private void sendRegistrationEmailToAdmin(Team team) {
        // Настройка свойств для соединения с SMTP-сервером
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        // Создание сеанса для аутентификации
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            // Создание письма
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_USERNAME));
            message.setSubject("Регистрация новой команды");

            // Формирование текста письма
            String emailBody = "Зарегистрирована новая команда для турнира:\n" +
                    "Команда: " + team.getName() + "\n" +
                    "Турнир: " + team.getTournament().getName() + "\n" +
                    "Дата начала турнира: " + team.getTournament().getStartDate() + "\n" +
                    "Дата окончания турнира: " + team.getTournament().getEndDate();
            message.setText(emailBody);

            // Отправка письма
            Transport.send(message);

            System.out.println("Письмо успешно отправлено на адрес " + EMAIL_USERNAME);
        } catch (MessagingException e) {
            System.out.println("Ошибка при отправке письма: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public String showTeam(@PathVariable("id") Long id, Model model) {
        model.addAttribute("team", teamService.getById(id));
        return "show_team";
    }

    @GetMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=teams.xls");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Teams");

        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Tournament");

        List<Team> teams = teamService.getAllTeams();
        int rowNum = 1;
        for (Team team : teams) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(team.getId());
            row.createCell(1).setCellValue(team.getName());
            row.createCell(2).setCellValue(team.getTournament().getName());

            // Добавление участников команды в отдельные ячейки
            int cellIndex = 3;
            for (User user : team.getMembers()) {
                row.createCell(cellIndex++).setCellValue(user.getName());
            }
        }

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        } finally {
            workbook.close();
        }
    }
}
