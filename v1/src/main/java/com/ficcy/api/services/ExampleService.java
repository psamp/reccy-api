package com.ficcy.api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class ExampleService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<Object, Object>> getService() {

		ArrayList<Map<Object, Object>> rtn = new ArrayList<Map<Object, Object>>();

		Map<Object, Object> one = new HashMap<Object, Object>();

		one.put("name", "Princess");
		one.put("favColor", "Purple");

		Map<Object, Object> two = new HashMap<Object, Object>();

		two.put("one", 1);
		two.put("two", 2);

		rtn.add(one);
		rtn.add(two);

		return rtn;

	}

}