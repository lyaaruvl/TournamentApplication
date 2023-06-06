package com.example.tournament.exel;

import com.example.tournament.models.Team;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {


    public void exportTeamsToExcel(List<Team> teams, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // создание новой книги Excel
        Sheet sheet = workbook.createSheet("Teams"); // создание нового листа

        // Создание заголовка
        Row headerRow = sheet.createRow(0);
        Cell nameHeaderCell = headerRow.createCell(0);
        nameHeaderCell.setCellValue("Team Name");
        Cell tournamentHeaderCell = headerRow.createCell(1);
        tournamentHeaderCell.setCellValue("Tournament");

        // Заполнение строк данными команд
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            Row row = sheet.createRow(i + 1); // создание новой строки

            Cell nameCell = row.createCell(0);
            nameCell.setCellValue(team.getName());

            Cell tournamentCell = row.createCell(1);
            if (team.getTournament() != null) {
                tournamentCell.setCellValue(team.getTournament().getName());
            } else {
                tournamentCell.setCellValue("N/A");
            }
        }

        // Запись книги в файл
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
}
