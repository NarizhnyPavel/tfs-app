package com.TimeForStudy.application.excelTable;

import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.otherDataClasses.UserRoles;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelReader {
    private static ExcelReader instance = null;

    private ExcelReader(){}

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

    public ArrayList<UserEntity> getProfessors(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("Professors");
        ArrayList<UserEntity> professors = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            if (!row.getCell(0).getStringCellValue().isEmpty())
                if (!String.valueOf(row.getCell(1).getNumericCellValue()).isEmpty())
                professors.add(new UserEntity(row.getCell(0).getStringCellValue(), String.valueOf(row.getCell(1).getNumericCellValue()), UserRoles.PROFESSOR));
        }
        return professors;
    }

    public ArrayList<UserEntity> getSuperStudents(XSSFWorkbook wb){
        XSSFSheet sheet = wb.getSheet("SuperStudents");
        ArrayList<UserEntity> superStudents = new ArrayList<>();
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        try {
            while (rowIter.hasNext()) {
                XSSFRow row = (XSSFRow) rowIter.next();
                if (!row.getCell(0).getStringCellValue().isEmpty())
                    if (!String.valueOf(row.getCell(1).getNumericCellValue()).isEmpty())
                        superStudents.add(new UserEntity(row.getCell(0).getStringCellValue(), String.valueOf(row.getCell(1).getNumericCellValue()), UserRoles.SUPER_STUDENT));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return superStudents;
    }
}
