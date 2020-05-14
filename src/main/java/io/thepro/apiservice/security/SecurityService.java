package io.thepro.apiservice.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.thepro.apiservice.security.models.Credentials;
import io.thepro.apiservice.security.models.SecurityProperties;
import io.thepro.apiservice.security.models.User;
import io.thepro.apiservice.utils.CookieUtils;

@Service
public class SecurityService {

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	CookieUtils cookieUtils;

	@Autowired
	SecurityProperties securityProps;

	public User getUser() {
		User userPrincipal = null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object principal = securityContext.getAuthentication().getPrincipal();
		if (principal instanceof User) {
			userPrincipal = ((User) principal);
		}
		return userPrincipal;
	}

	public Credentials getCredentials() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return (Credentials) securityContext.getAuthentication().getCredentials();
	}

	public boolean isPublic() {
		return securityProps.getAllowedPublicApis().contains(httpServletRequest.getRequestURI());
	}

}
