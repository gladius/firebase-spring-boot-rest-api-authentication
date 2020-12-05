package io.thepro.apiservice.security;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.thepro.apiservice.security.models.SecurityProperties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties restSecProps;

	@Autowired
	private SecurityFilter tokenAuthenticationFilter;

	@Bean
	public AuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
					AuthenticationException e) throws IOException, ServletException {
				Map<String, Object> errorObject = new HashMap<String, Object>();
				int errorCode = 401;
				errorObject.put("message", "Unauthorized access of protected resource, invalid credentials");
				errorObject.put("error", HttpStatus.UNAUTHORIZED);
				errorObject.put("code", errorCode);
				errorObject.put("timestamp", new Timestamp(new Date().getTime()));
				httpServletResponse.setContentType("application/json;charset=UTF-8");
				httpServletResponse.setStatus(errorCode);
				httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorObject));
			}
		};
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(restSecProps.getAllowedOrigins());
		configuration.setAllowedMethods(restSecProps.getAllowedMethods());
		configuration.setAllowedHeaders(restSecProps.getAllowedHeaders());
		configuration.setAllowCredentials(restSecProps.isAllowCredentials());
		configuration.setExposedHeaders(restSecProps.getExposedHeaders());
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().formLogin().disable()
				.httpBasic().disable().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
				.and().authorizeRequests()
				.antMatchers(restSecProps.getAllowedPublicApis().stream().toArray(String[]::new)).permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
				.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
