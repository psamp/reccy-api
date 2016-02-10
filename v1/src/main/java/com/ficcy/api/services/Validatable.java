package com.ficcy.api.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ficcy.api.config.Config;
import com.ficcy.api.dao.UserDAO;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.error.authc.OauthAuthenticationException;
import com.stormpath.sdk.oauth.Authenticators;
import com.stormpath.sdk.oauth.JwtAuthenticationRequest;
import com.stormpath.sdk.oauth.JwtAuthenticationResult;
import com.stormpath.sdk.oauth.Oauth2Requests;

abstract class Validatable {

	/**
	 * Uses user's external ID to run a database lookup for user's internal,
	 * numerical id.
	 * 
	 * @author psampson
	 * @param Servlet
	 *            request with an auth token
	 * @return User's numerical id.
	 */
	long getUserNumericalID(HttpServletRequest request) throws SQLException {

		String hashid = (String) ((Account) new TokenValidator().validate(request).get("account")).getCustomData()
				.get("hashid");

		return (long) new UserDAO().read(hashid).get("id");

	}

	Account getAccount(HttpServletRequest request) throws SQLException {

		return (Account) new TokenValidator().validate(request).get("account");
	}

	private class TokenValidator {

		/**
		 * Attempts to validate an incoming request based on auth token.
		 * 
		 * @author psampson
		 * @param A
		 *            servlet request with an auth token
		 * @return Map containing user's account and whether the token is valid, or a map containing false
		 */
		Map<String, Object> validate(HttpServletRequest request) throws OauthAuthenticationException {

			Map<String, Object> rtn = new HashMap<String, Object>();

			try {

				rtn.put("account", this.getTokenAuthenticationResult(request).getAccount());
				rtn.put("valid", true);

			} catch (OauthAuthenticationException e) {

				rtn.put("valid", false);
				throw e;
			}

			return rtn;
		}

		private JwtAuthenticationResult getTokenAuthenticationResult(HttpServletRequest request) {

			JwtAuthenticationRequest jwtRequest = Oauth2Requests.JWT_AUTHENTICATION_REQUEST.builder()
					.setJwt((String) request.getHeader("Authorization")).build();

			return Authenticators.JWT_AUTHENTICATOR.forApplication(Config.getApplication()).authenticate(jwtRequest);

		}

	}

}
