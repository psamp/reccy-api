package com.reccy.api.lib;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResponseHelper {
	
	public static Response getResponse(Object payload, int status) {
		return Response.status(status).entity(payload).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
	
	public static Response getError(int status, String message, String devMessage) {
		
		Map<String, String> error = new HashMap<String, String>();
		
		error.put("status", "" + status);
		error.put("message", message);
		error.put("devMessage", devMessage);
		
		return Response.status(status).entity(error).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
	
	

}
