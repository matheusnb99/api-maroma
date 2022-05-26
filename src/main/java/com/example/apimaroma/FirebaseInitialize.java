package com.example.apimaroma;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.net.*;

@Service
public class FirebaseInitialize {
    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initialize(){

        InputStream serviceAccount = null;
        InputStream serviceJarAccount = null;
        File keyJarValue = null;
        try {
            serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream();

            String content = new ClassPathResourceReader(firebaseConfigPath).getContent();
            InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));


            System.out.println("-----------------------path");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://maroma-5436d/firestore/firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(serviceAccount);
        }


    };
}
