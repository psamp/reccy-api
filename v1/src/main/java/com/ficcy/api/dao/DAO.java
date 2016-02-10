package com.ficcy.api.dao;

public abstract class DAO {
	private String URL;
	
	{
			this.URL = "jdbc:mysql://localhost:3306/ficcy?user=root&password=rooty&useSSL=true";
			try {
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				
				e.printStackTrace();
			}
	}

	public String getURL() {
		return this.URL;
	}

}
