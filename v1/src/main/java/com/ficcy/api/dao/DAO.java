package com.ficcy.api.dao;

abstract class DAO {
	private String URL;
	
	{
			this.URL = "jdbc:mysql://localhost/ficcy?user=root&password=rooty&useSSL=true";
	}

	public String getURL() {
		return URL;
	}

}
