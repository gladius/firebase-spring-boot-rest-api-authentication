package io.thepro.apiservice.security.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;

@Service
@ConditionalOnExpression("${test.login.enabled:false}")
public class TestAuthService {

	@Autowired
	FirebaseAuth firebaseAuth;

	@Value("classpath:data/test_users.json")
	private Resource resourceFile;

	@Autowired
	private ObjectMapper jsonMapper;

	private HashMap<String, TestUser> testUsers;

	@PostConstruct
	public void init() throws IOException {
		testUsers = jsonMapper.readValue(resourceFile.getInputStream(), new TypeReference<HashMap<String, TestUser>>() {
		});
	}

	public String authorizeTestLogin(String email) throws Exception {
		if (testUsers.containsKey(email)) {
			TestUser testUser = testUsers.get(email);
			Map<String, Object> developerClaims = new HashMap<String, Object>();
			developerClaims.put("name", testUser.getName());
			developerClaims.put("email", testUser.getEmail());
			developerClaims.put("ROLE_SUPER", testUser.isSuper());
			return firebaseAuth.createCustomToken(testUser.getUid().toString(), developerClaims);
		} else {
			throw new Exception("Invalid User");
		}
	}

}