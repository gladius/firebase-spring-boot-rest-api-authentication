package io.thepro.apiservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import io.thepro.apiservice.security.models.Credentials;
import io.thepro.apiservice.security.models.Credentials.CredentialType;
import io.thepro.apiservice.security.models.SecurityProperties;
import io.thepro.apiservice.security.models.User;
import io.thepro.apiservice.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	SecurityService securityService;

	@Autowired
	SecurityProperties restSecProps;

	@Autowired
	CookieUtils cookieUtils;

	@Autowired
	SecurityProperties securityProps;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		verifyToken(request);
		filterChain.doFilter(request, response);
	}

	private void verifyToken(HttpServletRequest request) {
		String session = null;
		FirebaseToken decodedToken = null;
		CredentialType type = null;
		boolean strictServerSessionEnabled = securityProps.getFirebaseProps().isEnableStrictServerSession();
		Cookie sessionCookie = cookieUtils.getCookie("session");
		String token = getBearerToken(request);
		try {
			if (sessionCookie != null) {
				System.out.println("~~~~~~~~~~~~~~~~~~~ inside strictServerSessionEnabled  :: ");
				session = sessionCookie.getValue();
				decodedToken = FirebaseAuth.getInstance().verifySessionCookie(session,
						securityProps.getFirebaseProps().isEnableCheckSessionRevoked());
				type = CredentialType.SESSION;
			} else if(!strictServerSessionEnabled){
				System.out.println("~~~~~~~~~~~~~~~~~~~ nope not inside strictServerSessionEnabled  :: ");
				if (token != null && !token.equalsIgnoreCase("undefined")) {
					decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
					type = CredentialType.ID_TOKEN;
				}
			}else {
				System.out.println("~~~~~~~~~~~~~~~~~~~ exited out of all :: ");
			}
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
			log.error("Firebase Exception:: ", e.getLocalizedMessage());
		}
		User user = firebaseTokenToUserDto(decodedToken);
		if (user != null) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
					new Credentials(type, decodedToken, token, session), null);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String getBearerToken(HttpServletRequest request) {
		String bearerToken = null;
		String authorization = request.getHeader("Authorization");
		if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
			bearerToken = authorization.substring(7, authorization.length());
		}
		return bearerToken;
	}

	private User firebaseTokenToUserDto(FirebaseToken decodedToken) {
		User user = null;
		if (decodedToken != null) {
			user = new User();
			user.setUid(decodedToken.getUid());
			user.setName(decodedToken.getName());
			user.setEmail(decodedToken.getEmail());
			user.setPicture(decodedToken.getPicture());
			user.setIssuer(decodedToken.getIssuer());
			user.setEmailVerified(decodedToken.isEmailVerified());
		}
		return user;
	}

}
