package io.thepro.apiservice.security.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnExpression("${test.login.enabled:false}")
@RequestMapping("test")
public class TestAuthController {

	@Autowired
	TestAuthService testAuthService;

	@PostMapping("/login/{email}")
	public String testLogin(@PathVariable("email") String email) throws Exception {
		System.out.println("test login uid :: " + email);
		return testAuthService.authorizeTestLogin(email);
	}

}