package io.thepro.apiservice.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

@Component
public class SecurityUtils {

	@Autowired
	HttpServletRequest request;

	public String getTokenFromRequest(HttpServletRequest request) {
		String token = null;
		Cookie cookieToken = WebUtils.getCookie(request, "token");
		if (cookieToken != null) {
			token = cookieToken.getValue();
		} else {
			String bearerToken = request.getHeader("Authorization");
			if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
				token = bearerToken.substring(7, bearerToken.length());
			}
		}
		return token;
	}

	public User getPrincipal() {
		User userPrincipal = null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object principal = securityContext.getAuthentication().getPrincipal();
		if (principal instanceof User) {
			userPrincipal = ((User) principal);
		}
		return userPrincipal;
	}

}
