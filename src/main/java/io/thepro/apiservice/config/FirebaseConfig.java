package io.thepro.apiservice.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import io.thepro.apiservice.security.models.SecurityProperties;

@Configuration
public class FirebaseConfig {

	@Autowired
	SecurityProperties secProps;

	@Primary
	@Bean
	public void firebaseInit() throws IOException {
		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.getApplicationDefault())
				.setDatabaseUrl(secProps.getFirebaseProps().getDatabaseUrl()).build();
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
	}

	@Bean
	public Firestore getDatabase() throws IOException {
		FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
				.setCredentials(GoogleCredentials.getApplicationDefault()).build();
		return firestoreOptions.getService();
	}

}
