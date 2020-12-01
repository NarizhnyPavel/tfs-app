package com.TimeForStudy.application.parser.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.UUID;

/**
 * Сервис для скачивания файла.
 *
 * @author Narizhny Pavel
 */
public class FileDownloader{
    private String address;
    private URL url;
    private ReadableByteChannel readableByteChannel;
    private String extension;

    public FileDownloader(String address, String extension) {
        this.address = address;
        this.extension = extension;
    }

    public boolean tryToConnect() {
        try {
            url = new URL("https://getfile.dokpub.com/yandex/get/" + address);
            readableByteChannel = Channels.newChannel(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String readFileFromYandexDisk() throws IOException {
        if (tryToConnect()) {
            String fileName = UUID.randomUUID().toString() + extension;
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            return fileName;
        }else
            return "connectionError";
    }
}
