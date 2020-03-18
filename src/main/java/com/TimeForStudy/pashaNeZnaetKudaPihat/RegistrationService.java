package com.TimeForStudy.pashaNeZnaetKudaPihat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

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
<<<<<<< HEAD
            if (inputStream.)
            return inputStream;
=======
            if (inputStream.markSupported()) {
                return inputStream.;
            }
>>>>>>> my commit
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
