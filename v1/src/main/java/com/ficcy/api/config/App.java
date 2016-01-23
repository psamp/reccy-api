package com.ficcy.api.config;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.tenant.Tenant;

public class App {
	private final static Client client = Clients.builder().build();
	private final static Application application;

	static {

		Tenant tenant = client.getCurrentTenant();
		ApplicationList applications = tenant
				.getApplications(Applications.where(Applications.name().eqIgnoreCase("ficcy-api")));

		application = applications.iterator().next();

	}

	public static Client getClient() {
		return client;
	}

	public static Application getApplication() {
		return application;
	}

}
