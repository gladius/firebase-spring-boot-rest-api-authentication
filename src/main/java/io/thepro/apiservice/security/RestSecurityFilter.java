package io.thepro.apiservice.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestSecurityFilter implements Filter {

	@Autowired
	RestSecurityProperties restSecProps;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		String allowedMethods = String.join(", ", restSecProps.getAllowedmethods());
		String allowedDomains = String.join(", ", restSecProps.getAlloweddomains());
		String allowedHeaders = String.join(", ", restSecProps.getAllowedheaders());
		response.setHeader("Access-Control-Allow-Methods", allowedMethods);
		response.setHeader("Access-Control-Allow-Origin", allowedDomains);
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}
}