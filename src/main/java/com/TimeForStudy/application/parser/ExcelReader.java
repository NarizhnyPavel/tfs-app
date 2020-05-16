package com.TimeForStudy.application.parser;

import com.TimeForStudy.application.user.model.UserDto;
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

    public ArrayList<UserDto> getProfessors(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Преподаватели");
        ArrayList<UserDto> professors = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        rowIter.next();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            if (!row.getCell(0).getStringCellValue().isEmpty())
                if (!String.valueOf(row.getCell(1).getNumericCellValue()).isEmpty())
                    professors.add(new UserDto(String.valueOf(row.getCell(1).getNumericCellValue()), row.getCell(0).getStringCellValue(), (byte) 2));
        }
        return professors;
    }

    public ArrayList<Integer> getRooms(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Аудитории");
        ArrayList<Integer> rooms = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        try {
            while (rowIter.hasNext()) {
                XSSFRow row = (XSSFRow) rowIter.next();
                if ((row.getCell(0).getNumericCellValue() != 0))
                    rooms.add((int) row.getCell(0).getNumericCellValue());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return rooms;
    }

    public ArrayList<String> getSubjects(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Дисциплины");
        ArrayList<String> subjects = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        try {
            while (rowIter.hasNext()) {
                XSSFRow row = (XSSFRow) rowIter.next();
                if (!row.getCell(0).getStringCellValue().isEmpty())
                    subjects.add(row.getCell(0).getStringCellValue());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return subjects;
    }

    public ArrayList<Integer> getGroups(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Группы");
        ArrayList<Integer> groups = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        try {
            while (rowIter.hasNext()) {
                XSSFRow row = (XSSFRow) rowIter.next();
                if ((row.getCell(0).getNumericCellValue() != 0))
                    groups.add((int) row.getCell(0).getNumericCellValue());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return groups;
    }
}
