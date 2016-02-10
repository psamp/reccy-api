package com.ficcy.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDAO extends DAO {

	public boolean create(String hashid) throws SQLException {

		int rowsInserted = 0;
		boolean rtn = false;

		try (java.sql.Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "INSERT INTO user (external_id) values (?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, hashid);

			rowsInserted = statement.executeUpdate();

			if (rowsInserted == 1) {
				rtn = true;
			}
		}
		
		return rtn;
	}
	
	public Map<String, Object> read(String hashid) throws SQLException {
		
		Map<String, Object> rtn = new HashMap<String, Object>();
		
		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "SELECT * FROM user WHERE external_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, hashid);
			
			ResultSet result = statement.executeQuery(); 

			while (result.next()) {
				
				rtn.put("id", result.getLong("user_id"));
				rtn.put("externalId", result.getString("external_id"));
			}
			
		}
		
		return rtn;
	}
	
	public boolean delete(String hashid) throws SQLException {
		
		int rowsDeleted = 0;
		boolean rtn = false;
		
		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "DELETE FROM user WHERE external_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, hashid);

			rowsDeleted = statement.executeUpdate();
			
			if(rowsDeleted == 1) {
				rtn = true;
			}
		}
		
		return rtn;
	}

}
