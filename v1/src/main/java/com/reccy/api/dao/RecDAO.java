package com.reccy.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hashids.Hashids;

import com.reccy.api.config.Config;
import com.reccy.api.constants.RATING;
import com.reccy.api.core.Rec;
import com.reccy.api.core.RecFactory;
import com.reccy.api.lib.DAOHelper;

public class RecDAO extends DAO {

	public boolean create(Rec rec, long owner) throws SQLException {

		boolean rtn = false;
		Hashids hash = Config.getHashid(rec.getSummary() + System.currentTimeMillis());

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "INSERT INTO rec (user_id, external_id, title, author, url, summary, about, rating) values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, owner);
			statement.setString(2, hash.encode((int) Math.random() * 1025));
			statement.setString(3, rec.getTitle());
			statement.setString(4, rec.getAuthor());
			statement.setString(5, rec.getUrl().toString());
			statement.setString(6, rec.getSummary());
			statement.setString(7, rec.getAbout());
			statement.setString(8, rec.getRating().toString());

			int result = statement.executeUpdate();

			if (result == 1) {
				rtn = true;
			}
		}

		return rtn;
	}

	public List<Rec> read(long owner, String username) throws SQLException {
		List<Rec> rtn = null;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "SELECT * FROM rec where user_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, owner);
			ResultSet result = statement.executeQuery();

			rtn = new ArrayList<Rec>();

			while (result.next()) {

				Rec rec = RecFactory.getInstance(result.getString("external_id"), result.getString("title"),
						result.getString("author"), result.getString("url"), result.getString("summary"),
						result.getString("about"), RATING.valueOf(result.getString("rating").toUpperCase()));

				rec.setOwner(username);

				rtn.add(rec);
			}

		}

		return rtn;
	}

	public Rec read(String externalID, long owner, String username) throws SQLException {

		Rec rtn = null;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "SELECT * FROM rec where user_id = ? AND rec_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, owner);
			statement.setLong(2, new DAOHelper().getNumericalIDForRecOrReclist("fic", externalID));
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				rtn = RecFactory.getInstance(result.getString("external_id"), result.getString("title"),
						result.getString("author"), result.getString("url"), result.getString("summary"),
						result.getString("about"), RATING.valueOf(result.getString("rating").toUpperCase()));

				rtn.setOwner(username);

			}

		}

		return rtn;
	}

	public boolean delete(String externalID, long owner) throws SQLException {

		boolean rtn = false;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "delete FROM rec where user_id = ? AND rec_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, owner);
			statement.setLong(2, new DAOHelper().getNumericalIDForRecOrReclist("fic", externalID));
			int result = statement.executeUpdate();

			if (result == 1) {
				rtn = true;
			}

		}

		return rtn;
	}
}
