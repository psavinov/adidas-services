package com.adidas.service.review.security;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
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
