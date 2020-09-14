package io.thepro.apiservice.security.models;

import lombok.Data;

@Data
public class CookieProperties {
	private String domain;
	private String path;
	private boolean httpOnly;
	private boolean secure;
	private int maxAgeInMinutes;
}
