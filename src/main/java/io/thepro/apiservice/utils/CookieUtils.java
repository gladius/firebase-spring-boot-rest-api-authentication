package io.thepro.apiservice.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import io.thepro.apiservice.security.models.SecurityProperties;

@Service
public class CookieUtils {

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	HttpServletResponse httpServletResponse;

	@Autowired
	SecurityProperties restSecProps;

	public Cookie getCookie(String name) {
		return WebUtils.getCookie(httpServletRequest, name);
	}

	public void setSecureCookie(String name, String value, int expiryInMinutes) {
		int expiresInSeconds = expiryInMinutes * 60 * 60;
		Cookie cookie = new Cookie(name, value);
		cookie.setHttpOnly(restSecProps.getCookieProps().isHttpOnly());
		cookie.setSecure(restSecProps.getCookieProps().isSecure());
		cookie.setPath(restSecProps.getCookieProps().getPath());
		cookie.setDomain(restSecProps.getCookieProps().getDomain());
		cookie.setMaxAge(expiresInSeconds);
		httpServletResponse.addCookie(cookie);
	}

	public void setSecureCookie(String name, String value) {
		int expiresInMinutes = restSecProps.getCookieProps().getMaxAgeInMinutes();
		setSecureCookie(name, value, expiresInMinutes);
	}

	public void deleteSecureCookie(String name) {
		int expiresInSeconds = 0;
		Cookie cookie = new Cookie(name, null);
		cookie.setHttpOnly(restSecProps.getCookieProps().isHttpOnly());
		cookie.setSecure(restSecProps.getCookieProps().isSecure());
		cookie.setPath(restSecProps.getCookieProps().getPath());
		cookie.setDomain(restSecProps.getCookieProps().getDomain());
		cookie.setMaxAge(expiresInSeconds);
		httpServletResponse.addCookie(cookie);
	}

}
