package com.example.apimaroma;

public class createFirebaseCredentials {

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
}
