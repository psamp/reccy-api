package com.ficcy.api.services;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hashids.Hashids;

import com.ficcy.api.config.Config;
import com.ficcy.api.rep.RegistrationRequest;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.directory.CustomData;
import com.stormpath.sdk.resource.ResourceException;

public class UserService {
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerNewUser(RegistrationRequest at) {

		Response rtn = Response.status(Response.Status.CREATED).build();

		Account account = Config.getClient().instantiate(Account.class);
		
		account.setEmail(at.getEmail())
				.setUsername(at.getUsername()).setPassword(at.getPassword()).setGivenName("-").setSurname("-");

		Hashids hash = Config.getHashid(account.getEmail() + System.currentTimeMillis());
		
		CustomData cs = account.getCustomData();
		cs.put("hashid", hash.encode(new Random().nextInt()));

		try {

			Config.getApplication().createAccount(account);

		} catch (ResourceException e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		return rtn;
	}

}
