package com.TimeForStudy.application.parser;

import com.TimeForStudy.application.user.model.UserDto;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ParserService {
    private static ParserService instance = null;

    private String url;
    ArrayList<UserDto> professors;
    ArrayList<Integer> groups;
    ArrayList<String> subjects;
    ArrayList<Integer> classrooms;

    private ParserService(){};

    public static synchronized ParserService getInstance(){
        if (instance == null){
            instance = new ParserService();
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
            ArrayList<UserDto> professors = ExcelReader.getInstance().getProfessors(workbook);
            ArrayList<Integer> groups = ExcelReader.getInstance().getGroups(workbook);
            ArrayList<String> subjects = ExcelReader.getInstance().getSubjects(workbook);
            ArrayList<Integer> classrooms = ExcelReader.getInstance().getRooms(workbook);
            return "успешно";
        } else{
            return "ошибка подключения";
        }

    }
}
