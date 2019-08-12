package com.danny.soap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class FileWSImpl implements FileWS {

    @Override
    public void upload(DataHandler attachment) {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = attachment.getInputStream();
            outputStream = new FileOutputStream(
                    new File("C:\\Users\\Danish\\Desktop\\SOAP_MTOM_uploads\\test.jpg"));
            byte[] b = new byte[100000];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public DataHandler donwload() {
        return new DataHandler(new FileDataSource(
                new File("C:\\Users\\Danish\\Desktop\\SOAP_MTOM_uploads\\test.jpg")));
    }

}
