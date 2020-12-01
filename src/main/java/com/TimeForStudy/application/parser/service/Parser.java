package com.TimeForStudy.application.parser.service;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.user.model.AddUserDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    public ArrayList<AddUserDto> professors;
    public ArrayList<IdNameDto> groups;
    public ArrayList<SubjectDto> subjects;
    public ArrayList<IdNameDto> classrooms;

    private Parser(){};

    public static synchronized Parser getInstance(){
        if (instance == null){
            instance = new Parser();
        }
        return instance;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String loadFromUrl()  {
        FileDownloader fileDownloader = new FileDownloader(url, ".xlsx");
        String fileName = null;
        try {
            fileName = fileDownloader.readFileFromYandexDisk();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileName.compareTo("connectionError") != 0){
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
