package com.ficcy.api.lib;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ficcy.api.config.Config;
import com.stormpath.sdk.error.authc.OauthAuthenticationException;
import com.stormpath.sdk.oauth.Authenticators;
import com.stormpath.sdk.oauth.JwtAuthenticationRequest;
import com.stormpath.sdk.oauth.JwtAuthenticationResult;
import com.stormpath.sdk.oauth.Oauth2Requests;

public class TokenValidator {

	public static Map<String, Object> validate(HttpServletRequest request) throws OauthAuthenticationException {
		Map<String, Object> rtn = new HashMap<String, Object>();
		
		try {
		
		JwtAuthenticationRequest jwtRequest = Oauth2Requests.JWT_AUTHENTICATION_REQUEST.builder()
				  .setJwt((String) request.getHeader("Authorization"))
				  .build();
		
		JwtAuthenticationResult jwtResult = Authenticators.JWT_AUTHENTICATOR
				  .forApplication(Config.getApplication())
				  .authenticate(jwtRequest);
		
		rtn.put("valid", true);
		rtn.put("account", jwtResult.getAccount());
		
		} catch(OauthAuthenticationException e) {
			rtn.clear();
			rtn.put("valid", false);
			throw e;
		}
		
		return rtn;
	}

}
