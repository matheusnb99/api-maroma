package com.example.apimaroma;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.storage.Storage;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseInitialize {
    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;


    private final org.slf4j.Logger log = LoggerFactory.getLogger(FirebaseInitialize.class);
    private final Environment environment;

    private StorageOptions storageOptions;
    private String bucketName;
    private String projectId;

    public FirebaseInitialize(Environment environment) {
        this.environment = environment;
    }


    @PostConstruct
    public void initialize() {

        InputStream serviceAccount = null;

        bucketName = environment.getRequiredProperty("FIREBASE_BUCKET_NAME");
        projectId = environment.getRequiredProperty("FIREBASE_PROJECT_ID");

        try {
            serviceAccount = createFirebaseCredential();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://maroma-5436d/firestore/firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(serviceAccount);
        }
    };


    private InputStream createFirebaseCredential() throws Exception {
        FirebaseCredentials firebaseCredential = new FirebaseCredentials();
        //private key
        String privateKey = environment.getRequiredProperty("FIREBASE_PRIVATE_KEY").replace("\\n", "\n");

        firebaseCredential.setType(environment.getRequiredProperty("FIREBASE_TYPE"));
        firebaseCredential.setProject_id(projectId);
        firebaseCredential.setPrivate_key_id("FIREBASE_PRIVATE_KEY_ID");
        firebaseCredential.setPrivate_key(privateKey);
        firebaseCredential.setClient_email(environment.getRequiredProperty("FIREBASE_CLIENT_EMAIL"));
        firebaseCredential.setClient_id(environment.getRequiredProperty("FIREBASE_CLIENT_ID"));
        firebaseCredential.setAuth_uri(environment.getRequiredProperty("FIREBASE_AUTH_URI"));
        firebaseCredential.setToken_uri(environment.getRequiredProperty("FIREBASE_TOKEN_URI"));
        firebaseCredential.setAuth_provider_x509_cert_url(environment.getRequiredProperty("FIREBASE_AUTH_PROVIDER_X509_CERT_URL"));
        firebaseCredential.setClient_x509_cert_url(environment.getRequiredProperty("FIREBASE_CLIENT_X509_CERT_URL"));

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredential);

        //convert jsonString string to InputStream using Apache Commons
        return IOUtils.toInputStream(jsonString);
    }
}