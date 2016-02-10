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
import com.ficcy.api.core.Rec;
import com.ficcy.api.core.RecFactory;
import com.ficcy.api.core.Reclist;
import com.ficcy.api.lib.DAOHelper;

public class ReclistDAO extends DAO {

	public boolean create(Reclist reclist, long owner) throws SQLException {

		boolean rtn = false;
		Hashids hash = Config.getHashid(reclist.getTitle() + System.currentTimeMillis());

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "INSERT INTO reclist (external_id, created_by, title, description, isprivate) values (?, ?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, hash.encode((int) Math.random() * 1025));
			statement.setLong(2, owner);
			statement.setString(3, reclist.getTitle());
			statement.setString(4, reclist.getDescription());
			statement.setBoolean(5, reclist.isPrivate());

			int result = statement.executeUpdate();

			if (result == 1) {
				rtn = true;
			}
		}

		return rtn;
	}

//	public boolean addRec(long recID, long reclistID) throws SQLException {
//
//		boolean rtn = false;
//
//		try (Connection conn = DriverManager.getConnection(super.getURL())) {
//
//			String sql = "insert into reclist_rec (reclist_id, rec_id) values (?, ?)";
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//
//			statement.setString(1, hash.encode((int) Math.random() * 1025));
//			statement.setLong(2, owner);
//			int result = statement.executeUpdate();
//
//			if (result == 1) {
//				rtn = true;
//			}
//		}
//
//		return rtn;
//	}

//	public List<Reclist> read(long owner, String username) throws SQLException {
//		List<Reclist> rtn = null;
//
//		try (Connection conn = DriverManager.getConnection(super.getURL())) {
//
//			String sql = "SELECT * FROM reclist where user_id = ?";
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setLong(1, owner);
//			ResultSet result = statement.executeQuery();
//
//			rtn = new ArrayList<Reclist>();
//
//			while (result.next()) {
//
//				Rec rec = RecFactory.getInstance(result.getString("external_id"), result.getString("title"),
//						result.getString("author"), result.getString("url"), result.getString("summary"),
//						result.getString("fandom"), RATING.valueOf(result.getString("rating").toUpperCase()));
//
//				rec.setOwner(username);
//
//				rtn.add(rec);
//			}
//
//		}
//
//		return rtn;
//	}

	public Rec read(String externalID, long owner, String username) throws SQLException {

		Rec rtn = null;

		try (Connection conn = DriverManager.getConnection(super.getURL())) {

			String sql = "SELECT * FROM rec where user_id = ? AND fic_id = ?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, owner);
			statement.setLong(2, new DAOHelper().getNumericalIDForRecOrReclist("fic", externalID));
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				rtn = RecFactory.getInstance(result.getString("external_id"), result.getString("title"),
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

			String sql = "delete FROM rec where user_id = ? AND fic_id = ?";

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
