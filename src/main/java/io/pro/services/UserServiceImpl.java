package io.pro.services;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public void saveUser(String idToken) throws Exception {
		String uid = getUserIdFromIdToken(idToken);
		System.out.println("User Id :: " + uid);

	}

	public String getUserIdFromIdToken(String idToken) throws Exception {
		String uid = null;
		try {
			uid = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get().getUid();
		} catch (InterruptedException | ExecutionException e) {
			throw new Exception("User Not Authenticated");
		}
		return uid;
	}

}
