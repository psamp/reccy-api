package com.ficcy.api.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ficcy.api.config.App;
import com.ficcy.api.message.Message;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.resource.ResourceException;

@Path("/user")
public class UserService {
	private Message message;
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getService() {
		
		return new Message(404, "hello :)");
		
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewUser(final Account account) {

		try {

			App.getApplication().createAccount(account);
			message = new Message(201, "Account created");

		} catch (ResourceException e) {
			message = new Message(e.getCode(), e.getMessage());
		}

		return Response.status(message.getCode()).entity(message).build();

	}

}
