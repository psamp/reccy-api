package com.ficcy.api.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ficcy.api.config.Config;
import com.ficcy.api.rep.AuthRequest;
import com.stormpath.sdk.oauth.Authenticators;
import com.stormpath.sdk.oauth.Oauth2Requests;
import com.stormpath.sdk.oauth.OauthGrantAuthenticationResult;
import com.stormpath.sdk.oauth.PasswordGrantRequest;

@Path("/auth")
public class AuthService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Object, Object> getToken(AuthRequest ar) {

		Map<Object, Object> rtn = new HashMap<Object, Object>();

		PasswordGrantRequest pgr = Oauth2Requests.PASSWORD_GRANT_REQUEST.builder().setLogin(ar.getLogin())
				.setPassword(ar.getPassword()).build();

		OauthGrantAuthenticationResult authResult = Authenticators.PASSWORD_GRANT_AUTHENTICATOR
				.forApplication(Config.getApplication()).authenticate(pgr);

		rtn.put("token", authResult);

		return rtn;
	}

}
