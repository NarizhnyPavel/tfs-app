package com.TimeForStudy.pashaNeZnaetKudaPihat;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.otherDataClasses.UserRoles;
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

    public ArrayList<User> getProfessors(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Professors");
        ArrayList<User> professors = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            if (!row.getCell(0).getStringCellValue().isEmpty())
                if (!String.valueOf(row.getCell(1).getNumericCellValue()).isEmpty())
                professors.add(new User(row.getCell(0).getStringCellValue(), String.valueOf(row.getCell(1).getNumericCellValue()), UserRoles.PROFESSOR));
        }
        return professors;
    }

    public ArrayList<User> getSuperStudents(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("SuperStudents");
        ArrayList<User> superStudents = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        try {
            while (rowIter.hasNext()) {
                XSSFRow row = (XSSFRow) rowIter.next();
                if (!row.getCell(0).getStringCellValue().isEmpty())
                    if (!String.valueOf(row.getCell(1).getNumericCellValue()).isEmpty())
                        superStudents.add(new User(row.getCell(0).getStringCellValue(), String.valueOf(row.getCell(1).getNumericCellValue()), UserRoles.SUPER_STUDENT));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return superStudents;
    }
}
