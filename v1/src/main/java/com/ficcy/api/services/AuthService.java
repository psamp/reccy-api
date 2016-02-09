package com.ficcy.api.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hashids.Hashids;

import com.ficcy.api.config.Config;
import com.ficcy.api.dao.UserDAO;
import com.ficcy.api.lib.ResponseHelper;
import com.ficcy.api.rep.AuthRequest;
import com.ficcy.api.rep.RegistrationRequest;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.directory.CustomData;
import com.stormpath.sdk.oauth.Authenticators;
import com.stormpath.sdk.oauth.Oauth2Requests;
import com.stormpath.sdk.oauth.OauthGrantAuthenticationResult;
import com.stormpath.sdk.oauth.PasswordGrantRequest;
import com.stormpath.sdk.resource.ResourceException;

@Path("/auth")
public class AuthService {

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerNewUser(RegistrationRequest at) {

		Response rtn = Response.status(Response.Status.CREATED).build();

		Account account = Config.getClient().instantiate(Account.class).setEmail(at.getEmail())
				.setUsername(at.getUsername()).setPassword(at.getPassword()).setGivenName("-").setSurname("-");

		Hashids hash = Config.getHashid(account.getEmail() + System.currentTimeMillis());
		CustomData cs = account.getCustomData();
		String hashid = hash.encode((int) Math.random() * 1025);
		cs.put("hashid", hashid);

		try {

			Config.getApplication().createAccount(account);
			new UserDAO().create(hashid);

		} catch (ResourceException e) {
			
			rtn = ResponseHelper.getError(e.getStatus(), e.getMessage(),
					"Make sure email is valid/not taken, password has one uppercase letter, one digit, and is at least eight characters; and username is not taken");
		} catch (SQLException e) {
			
			rtn = ResponseHelper.getError(503, e.getMessage(),
					"Malformed MySQL syntax");
		}

		return rtn;
	}

	@POST
	@Path("/token")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getToken(AuthRequest ar) {

		Response rtn = null;
		Map<String, String> payload = new HashMap<String, String>();

		try {

			PasswordGrantRequest pgr = Oauth2Requests.PASSWORD_GRANT_REQUEST.builder().setLogin(ar.getLogin())
					.setPassword(ar.getPassword()).build();

			OauthGrantAuthenticationResult authResult = Authenticators.PASSWORD_GRANT_AUTHENTICATOR
					.forApplication(Config.getApplication()).authenticate(pgr);

			payload.put("access_token", authResult.getAccessTokenString());
			payload.put("token_type", "Bearer");
			payload.put("expires_in", "" + authResult.getExpiresIn());

			rtn = ResponseHelper.getResponse(payload, 200);

		} catch (Exception e) {

			rtn = ResponseHelper.getError(401, e.getMessage(), "Verify user/email and password");
		}

		return rtn;
	}
	

}
