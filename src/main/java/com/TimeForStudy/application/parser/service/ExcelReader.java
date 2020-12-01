package com.TimeForStudy.application.parser.service;

import com.TimeForStudy.application.common.IdNameDto;
import com.TimeForStudy.application.subject.model.SubjectDto;
import com.TimeForStudy.application.user.model.AddUserDto;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelReader {
    private static ExcelReader instance = null;

    private ExcelReader(){};

    public static synchronized ExcelReader getInstance(){
        if (instance == null){
            instance = new ExcelReader();
        }
        return instance;
    }

    public XSSFWorkbook readWorkbook(String filename) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook (new FileInputStream(filename));
            return wb;
        }
        catch (Exception e) {
            return null;
        }
    }

    public ArrayList<AddUserDto> getProfessors(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Преподаватели");
        ArrayList<AddUserDto> professors = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        rowIter.next();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            if (row.getCell(0) != null && !row.getCell(0).getStringCellValue().isEmpty())
                if (!String.valueOf(row.getCell(1).getStringCellValue()).isEmpty())
                    professors.add(new AddUserDto(String.valueOf(row.getCell(1).getStringCellValue()),
                            String.valueOf(row.getCell(1).getStringCellValue()), row.getCell(0).getStringCellValue(), "", "", null));
        }
        return professors;
    }

    public ArrayList<SubjectDto> getSubjects(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Дисциплины");
        ArrayList<SubjectDto> subjects = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        rowIter.next();
        try {
            while (rowIter.hasNext()) {
                XSSFRow row = (XSSFRow) rowIter.next();
                if (row.getCell(0) != null && !row.getCell(0).getStringCellValue().isEmpty())
                    subjects.add(SubjectDto.of(null, row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return subjects;
    }

    public ArrayList<IdNameDto> getRooms(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Аудитории");
        ArrayList<IdNameDto> rooms = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        try {
            while (rowIter.hasNext()) {
                XSSFRow row = (XSSFRow) rowIter.next();
                if (row.getCell(0) != null && (row.getCell(0).getNumericCellValue() != 0))
                    rooms.add(IdNameDto.of(null, String.valueOf(row.getCell(0).getNumericCellValue())));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return rooms;
    }

    public ArrayList<IdNameDto> getGroups(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Группы");
        ArrayList<IdNameDto> groups = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        try {
            while (rowIter.hasNext()) {
                XSSFRow row = (XSSFRow) rowIter.next();
                if (row.getCell(0) != null && !(row.getCell(0).getStringCellValue().isEmpty()))
                    groups.add(IdNameDto.of(null, row.getCell(0).getStringCellValue()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return groups;
    }
}
