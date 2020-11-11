package io.thepro.apiservice.security;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.SessionCookieOptions;

import io.thepro.apiservice.security.models.Credentials.CredentialType;
import io.thepro.apiservice.security.models.SecurityProperties;
import io.thepro.apiservice.security.models.User;

@RestController
@RequestMapping("session")
public class SecurityAuthController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private CookieService cookieUtils;

	@Autowired
	private SecurityProperties secProps;

	@PostMapping("login")
	public void sessionLogin(HttpServletRequest request) {
		String idToken = securityService.getBearerToken(request);
		int sessionExpiryDays = secProps.getFirebaseProps().getSessionExpiryInDays();
		long expiresIn = TimeUnit.DAYS.toMillis(sessionExpiryDays);
		SessionCookieOptions options = SessionCookieOptions.builder().setExpiresIn(expiresIn).build();
		try {
			String sessionCookieValue = FirebaseAuth.getInstance().createSessionCookie(idToken, options);
			cookieUtils.setSecureCookie("session", sessionCookieValue, sessionExpiryDays);
			cookieUtils.setCookie("authenticated", Boolean.toString(true), sessionExpiryDays);
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
		}
	}

	@PostMapping("logout")
	public void sessionLogout() {
		if (securityService.getCredentials().getType() == CredentialType.SESSION
				&& secProps.getFirebaseProps().isEnableLogoutEverywhere()) {
			try {
				FirebaseAuth.getInstance().revokeRefreshTokens(securityService.getUser().getUid());
			} catch (FirebaseAuthException e) {
				e.printStackTrace();
			}
		}
		cookieUtils.deleteSecureCookie("session");
		cookieUtils.deleteCookie("authenticated");
	}
	
	@PostMapping("me")
	public User getUser() {
		return securityService.getUser();
	}

	@GetMapping("create/token")
	public String getCustomToken() throws FirebaseAuthException {
		return FirebaseAuth.getInstance().createCustomToken(String.valueOf(securityService.getUser().getUid()));
	}

}
