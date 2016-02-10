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
import com.ficcy.api.lib.DAOHelper;

public class FicDAO extends DAO {

	public boolean create(Fic fic, long owner) throws SQLException {

		int result = 0;
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

			result = statement.executeUpdate();

			if (result == 1) {
				rtn = true;
			}
		}

		return rtn;
	}

	public List<Fic> read(long owner, String username) throws SQLException {
		List<Fic> rtn = null;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "SELECT * FROM fic where user_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, owner);
			ResultSet result = statement.executeQuery();

			rtn = new ArrayList<Fic>();

			while (result.next()) {

				Fic fic = FicFactory.getInstance(result.getString("external_id"), result.getString("title"),
						result.getString("author"), result.getString("url"), result.getString("summary"),
						result.getString("fandom"), RATING.valueOf(result.getString("rating").toUpperCase()));

				fic.setOwner(username);

				rtn.add(fic);
			}

		}

		return rtn;
	}

	public Fic read(String externalID, long owner, String username) throws SQLException {

		Fic rtn = null;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "SELECT * FROM fic where user_id = ? AND fic_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, owner);
			statement.setLong(2, new DAOHelper().getNumericalIDForFicOrFiclist("fic", externalID));
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				rtn = FicFactory.getInstance(result.getString("external_id"), result.getString("title"),
						result.getString("author"), result.getString("url"), result.getString("summary"),
						result.getString("fandom"), RATING.valueOf(result.getString("rating").toUpperCase()));

				rtn.setOwner(username);

			}

		}

		return rtn;
	}

	public boolean delete(String externalID, long owner) throws SQLException {

		boolean rtn = false;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "delete FROM fic where user_id = ? AND fic_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, owner);
			statement.setLong(2, new DAOHelper().getNumericalIDForFicOrFiclist("fic", externalID));
			int result = statement.executeUpdate();

			if (result == 1) {
				rtn = true;
			}

		}

		return rtn;
	}
}
