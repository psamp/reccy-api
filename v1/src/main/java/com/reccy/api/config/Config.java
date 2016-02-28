package com.reccy.api.config;

import org.hashids.Hashids;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.tenant.Tenant;

public class Config {
	private static Client client = Clients.builder().build();
	private static Application application;

	static {

		Tenant tenant = client.getCurrentTenant();
		ApplicationList applications = tenant
				.getApplications(Applications.where(Applications.name().eqIgnoreCase("reccy-api")));

		application = applications.iterator().next();

	}

	public static Client getClient() {
		return client;
	}

	public static Application getApplication() {
		return application;
	}
	
	public static Hashids getHashid(String salt) {
		return new Hashids(salt, 6);
	}

}