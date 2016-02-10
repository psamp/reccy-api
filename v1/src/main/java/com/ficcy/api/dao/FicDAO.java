package com.ficcy.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hashids.Hashids;

import com.ficcy.api.config.Config;
import com.ficcy.api.constants.RATING;
import com.ficcy.api.core.Fic;
import com.ficcy.api.core.FicFactory;

public class FicDAO extends DAO {

	public boolean create(Fic fic, long owner) throws SQLException {

		int rowsInserted = 0;
		boolean rtn = false;
		Hashids hash = Config.getHashid(fic.getSummary() + System.currentTimeMillis());

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "INSERT INTO fic (user_id, external_id, title, author, url, summary, fandom, rating) values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, owner);
			statement.setString(2, hash.encode((int) Math.random() * 1025));
			statement.setString(3, fic.getTitle());
			statement.setString(4, fic.getAuthor());
			statement.setString(5, fic.getUrl().toString());
			statement.setString(6, fic.getSummary());
			statement.setString(7, fic.getFandom());
			statement.setString(8, fic.getRating().toString());

			rowsInserted = statement.executeUpdate();

			if (rowsInserted == 1) {
				rtn = true;
			}
		}

		return rtn;
	}

	public List<Fic> read(long owner) throws SQLException {
		List<Fic> rtn = null;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "SELECT * FROM fic where user_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, owner);
			ResultSet result = statement.executeQuery();

			rtn = new ArrayList<Fic>();

			while (result.next()) {

				rtn.add(FicFactory.getInstance(result.getString("external_id"), result.getString("title"),
						result.getString("author"), result.getString("url"), result.getString("summary"),
						result.getString("fandom"), RATING.valueOf(result.getString("rating").toUpperCase())));
			}

		}

		return rtn;
	}

}
