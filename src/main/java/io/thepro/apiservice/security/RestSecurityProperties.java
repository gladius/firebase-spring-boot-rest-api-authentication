package io.thepro.apiservice.security;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("security")
@Data
public class RestSecurityProperties {

	List<String> alloweddomains;
	List<String> allowedheaders;
	List<String> allowedmethods;
	List<String> allowedpublicapis;

}
