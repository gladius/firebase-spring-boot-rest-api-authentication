package io.thepro.apiservice.controllers;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.SessionCookieOptions;

import io.thepro.apiservice.security.SecurityService;
import io.thepro.apiservice.security.models.Credentials.CredentialType;
import io.thepro.apiservice.security.models.SecurityProperties;
import io.thepro.apiservice.utils.CookieUtils;

@RestController
@ConditionalOnExpression("${security.firebase-props.enable-session-cookie:false}")
public class SessionAuthController {

	@Autowired
	SecurityService securityService;

	@Autowired
	CookieUtils cookieUtils;

	@Autowired
	SecurityProperties restSecProps;

	@PostMapping("/session/login")
	public void sessionLogin(HttpServletRequest request) {
		String idToken = request.getHeader("Id-Token");
		int sessionExpiryDays = restSecProps.getFirebaseProps().getSessionExpiryInDays();
		long expiresIn = TimeUnit.DAYS.toMillis(sessionExpiryDays);
		SessionCookieOptions options = SessionCookieOptions.builder().setExpiresIn(expiresIn).build();
		try {
			String sessionCookieValue = FirebaseAuth.getInstance().createSessionCookie(idToken, options);
			cookieUtils.setSecureCookie("session", sessionCookieValue,
					(int) TimeUnit.DAYS.toMinutes(sessionExpiryDays));
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/session/logout")
	public void sessionLogout() {
		if (securityService.getCredentials().getType() == CredentialType.SESSION
				&& restSecProps.getFirebaseProps().isEnableLogoutEverywhere()) {
			try {
				FirebaseAuth.getInstance().revokeRefreshTokens(securityService.getUser().getUid());
			} catch (FirebaseAuthException e) {
				e.printStackTrace();
			}
		}
		cookieUtils.deleteSecureCookie("session");
	}

}
