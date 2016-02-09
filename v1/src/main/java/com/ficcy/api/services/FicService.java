package com.ficcy.api.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ficcy.api.lib.TokenValidator;

@Path("/{user_external_id}/fic")
public class FicService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUserFics(@Context HttpServletRequest request) {
		Response rtn = Response.status(Response.Status.UNAUTHORIZED).build();
		
		if ((boolean) TokenValidator.validate(request).get("valid") == true) {
			rtn = Response.status(Response.Status.OK).build();
		}	
		return rtn;

	}

}