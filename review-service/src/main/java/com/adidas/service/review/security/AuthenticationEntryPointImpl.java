package com.adidas.service.review.security;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * {@link BasicAuthenticationEntryPoint} which defines a realm name for the
 * Review service app.
 *
 * @author Pavel Savinov
 */
@Component
public class AuthenticationEntryPointImpl extends
	BasicAuthenticationEntryPoint {

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("adidas-reviews");

		super.afterPropertiesSet();
	}

}
