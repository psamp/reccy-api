package com.ficcy.api.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ficcy.api.auth.UserService;

@ApplicationPath("/v1")
public class ResourceList extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UserService.class);
		return s;
	}

}
