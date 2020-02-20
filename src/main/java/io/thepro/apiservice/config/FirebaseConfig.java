package io.thepro.apiservice.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

	@Value("${firebase.databaseUrl}")
	String firebaseDatabaseUrl;

	@Primary
	@Bean
	public void firebaseInit() throws IOException {
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials
						.fromStream(new ClassPathResource("/firebase-server-config.json").getInputStream()))
				.setDatabaseUrl(firebaseDatabaseUrl).build();
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
