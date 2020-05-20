package com.TimeForStudy.application.parser.service;

import com.TimeForStudy.application.user.model.UserDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Парсинг таблицы.
 *
 * @author Narizhny Pavel
 */
public class Parser {
    private static Parser instance = null;

    private String url;
    ArrayList<UserDto> professors;
    ArrayList<Integer> groups;
    ArrayList<String> subjects;
    ArrayList<Integer> classrooms;

    private Parser(){};

    public static synchronized Parser getInstance(){
        if (instance == null){
            instance = new Parser();
        }
        return instance;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private String loadFromUrl() throws IOException {
        FileDownloader fileDownloader = new FileDownloader(url, ".xlsx");
        String fileName = fileDownloader.readFileFromYandexDisk();
        if (fileName.compareTo("connectionError") == 0){
            File file = new File(fileName);
            XSSFWorkbook workbook = ExcelReader.getInstance().readWorkbook(fileName);
            professors = ExcelReader.getInstance().getProfessors(workbook);
            groups = ExcelReader.getInstance().getGroups(workbook);
            subjects = ExcelReader.getInstance().getSubjects(workbook);
            classrooms = ExcelReader.getInstance().getRooms(workbook);
            return "Успешно";
        } else{
            return "Ошибка подключения";
        }

    }
}
