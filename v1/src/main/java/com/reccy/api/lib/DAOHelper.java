package com.reccy.api.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.reccy.api.dao.DAO;

public class DAOHelper extends DAO {

	public long getNumericalIDForRecOrReclist(String table, String externalID) throws SQLException {

		long rtn = -99;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "SELECT * FROM " + table.trim() + " where external_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, externalID);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				rtn = result.getLong("fic_id");
			}

		}

		return rtn;
	}

}
