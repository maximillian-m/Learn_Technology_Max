package com.maximillian.logging.firebasePushNotification.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Configuration
public class FireBaseConfig {
    @Value("${application.firebase.credentials}")
    private String fireBaseCredentials;


    // This class initializes the firebase credentials for both the messaging and the normal google credentials for Oauth or any google/
    //specific operations......
    @Bean
    GoogleCredentials googleCredentials(){
        try {
        if (fireBaseCredentials != null) {
                InputStream credentialStream = new ByteArrayInputStream(fireBaseCredentials.getBytes());
                return GoogleCredentials.fromStream(credentialStream);
        }
            // Use standard credentials chain. Useful when running inside GKE
            return GoogleCredentials.getApplicationDefault();
        } catch (Exception ex){
            throw new RuntimeException("error processing firebase credentials");
        }
    }
    @Bean
    FirebaseApp firebaseApp(GoogleCredentials googleCredentials){
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp){
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
