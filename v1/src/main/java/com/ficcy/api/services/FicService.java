package com.ficcy.api.services;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ficcy.api.core.Fic;
import com.ficcy.api.dao.FicDAO;
import com.ficcy.api.dao.UserDAO;
import com.ficcy.api.lib.ResponseHelper;
import com.ficcy.api.lib.TokenValidator;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.error.authc.OauthAuthenticationException;

@Path("/{user_external_id}/fic")
public class FicService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postNewFic(Fic fic, @Context HttpServletRequest request) throws SQLException {
		Response rtn = null;
		Map<String, Object> validator = TokenValidator.validate(request);

		try {
			if ((boolean) validator.get("valid").equals(true)) {

				String hashid = (String) ((Account) validator.get("account")).getCustomData().get("hashid");
				int userID = (int) this.getUserID(hashid).get("id");

				new FicDAO().create(fic, userID);

			}
		} catch (OauthAuthenticationException e) {
			rtn = ResponseHelper.getError(401, e.getMessage(), "Invalid or missing token");
		} catch (SQLException e) {
			rtn = ResponseHelper.getError(503, e.getMessage(), "Invalid MySQL syntax");
		}

		return rtn;

	}

	private Map<String, Object> getUserID(String hashid) throws SQLException {
		return new UserDAO().read(hashid);
	}

}