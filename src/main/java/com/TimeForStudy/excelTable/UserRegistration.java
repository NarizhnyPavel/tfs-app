package com.TimeForStudy.excelTable;

import com.TimeForStudy.entity.User;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserRegistration extends Exception {
    private static UserRegistration instance = null;

    private UserRegistration() {
    };

    public static synchronized UserRegistration getInstance() {
        if (instance == null) {
            instance = new UserRegistration();
        }
        return instance;
    }

    private FileDownloader fileDownloader;
    private String fileName = null;
    public void uploadRegistrationFile(final String url) {
        final Thread thread = new Thread() {
            public void run() {
                fileDownloader = new FileDownloader(url, ".xlsx");
                boolean result = fileDownloader.tryToConnect();

                try {
                    fileName = fileDownloader.readFileFromYandexDisk();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (!new File(fileName).canRead()) {
                    return;
                }

            }
        };
        thread.start();

        //TODO we need to make some processBar, when application trying to connect
        try {
            wait(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        XSSFWorkbook workbook = ExcelReader.getInstance().readWorkbook(fileName);
        ArrayList<User> professors = ExcelReader.getInstance().getProfessors(workbook);
        ArrayList<User> superStudents = ExcelReader.getInstance().getSuperStudents(workbook);
        //TODO compare given lists with data from server
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "<html>new " + professors.size() + " Professors" +
                "<br> new " + superStudents.size() + " superStudents <html>");
    }
}
