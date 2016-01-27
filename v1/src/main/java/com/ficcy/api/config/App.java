package com.ficcy.api.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/v1")
public class App extends ResourceConfig {

	public App() {
		packages("com.ficcy.api.services");
	}

}