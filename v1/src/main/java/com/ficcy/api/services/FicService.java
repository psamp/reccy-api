package com.ficcy.api.services;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user/{user_external_id}/fic")
public class FicService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<Object, Object>> getAllUserFics() {
		
		return null;

	}

}