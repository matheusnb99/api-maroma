package com.example.apimaroma;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Service
public class FirebaseInitialize {
    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initialize(){
        try {
            System.out.println("-----------------------path");

            InputStream serviceAccount = new ClassPathResource(firebaseConfigPath).getInputStream();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://maroma-5436d/firestore/firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

        }catch (Exception e){
            System.out.println("-----------------------err");
            e.printStackTrace();
        }

    };
}
