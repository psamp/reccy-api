package com.ficcy.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hashids.Hashids;

import com.ficcy.api.config.Config;
import com.ficcy.api.core.Fic;

public class FicDAO extends DAO {
	
	public boolean create(Fic fic, int owner) throws SQLException {

		int rowsInserted = 0;
		boolean rtn = false;
		Hashids hash = Config.getHashid(fic.getSummary() + System.currentTimeMillis());

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "insert into fic (user_id, external_id, title, author, url, summary, fandom, rating) values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, owner);
			statement.setString(2, hash.encode((int) Math.random() * 1025));
			statement.setString(3, fic.getTitle());
			statement.setString(4, fic.getAuthor());
			statement.setString(5, fic.getUrl().toString());
			statement.setString(6, fic.getSummary());
			statement.setString(7, fic.getFandom());
			statement.setString(8, fic.getRating().toString());

			if (rowsInserted == 1) {
				rtn = true;
			}
		}
		
		return rtn;
	}
	
}
