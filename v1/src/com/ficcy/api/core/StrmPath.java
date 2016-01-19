package com.ficcy.api.core;

import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.ClientBuilder;
import com.stormpath.sdk.client.Clients;

public class StrmPath {
	private static ClientBuilder builder;
	final static Client client = builder.build();
	
	{
		builder = Clients.builder(); 
	}

	public static void main(String[] args) {
		
		
		
	}

}
