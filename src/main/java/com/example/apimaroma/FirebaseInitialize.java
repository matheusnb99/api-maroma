package com.example.apimaroma;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
@Primary
public class FirebaseInitialize {
    @PostConstruct
    public void initialize(){
        try {
            System.out.println("-----------------------path");

            FileInputStream serviceAccount =
                    new FileInputStream("./serviceAccountKeyMaroma.json");

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
