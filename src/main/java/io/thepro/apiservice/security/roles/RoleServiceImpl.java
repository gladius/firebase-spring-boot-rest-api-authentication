package io.thepro.apiservice.security.roles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import io.thepro.apiservice.security.models.SecurityProperties;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

	@Autowired
	FirebaseAuth firebaseAuth;

	@Autowired
	private SecurityProperties securityProps;

	@Override
	public void addRole(String uid, String role) throws Exception {
		try {
			UserRecord user = firebaseAuth.getUser(uid);
			Map<String, Object> claims = new HashMap<>();
			user.getCustomClaims().forEach((k, v) -> claims.put(k, v));
			if (securityProps.getValidApplicationRoles().contains(role)) {
				if (!claims.containsKey(role.toLowerCase())) {
					claims.put(role.toLowerCase(), true);
				}
				firebaseAuth.setCustomUserClaims(uid, claims);
			} else {
				throw new Exception("Not a valid Application role, Allowed roles => "
						+ securityProps.getValidApplicationRoles().toString());
			}

		} catch (FirebaseAuthException e) {
			log.error("Firebase Auth Error ", e);
		}

	}

	@Override
	public void removeRole(String uid, String role) {
		try {
			UserRecord user = firebaseAuth.getUser(uid);
			Map<String, Object> claims = new HashMap<>();
			user.getCustomClaims().forEach((k, v) -> claims.put(k, v));
			if (claims.containsKey(role.toLowerCase())) {
				claims.remove(role.toLowerCase());
			}
			firebaseAuth.setCustomUserClaims(uid, claims);
		} catch (FirebaseAuthException e) {
			log.error("Firebase Auth Error ", e);
		}
	}

}
