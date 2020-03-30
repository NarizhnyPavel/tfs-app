package com.TimeForStudy.excelTable;

import java.io.File;
import java.io.FileInputStream;

public class RegistrationService {

    private static RegistrationService instance = null;

    public static synchronized RegistrationService getInstance(){
        if (instance == null)
            instance = new RegistrationService();
        return instance;
    }

    public File loadFile(String URL) {
        try {
            FileInputStream inputStream = new FileInputStream("https://getfile.dokpub.com/yandex/get/" + URL);
            //if (inputStream.)
            return null; //inputStream.;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
