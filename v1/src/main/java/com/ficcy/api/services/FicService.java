package com.ficcy.api.services;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ficcy.api.core.Fic;
import com.ficcy.api.dao.FicDAO;
import com.ficcy.api.lib.ResponseHelper;
import com.stormpath.sdk.error.authc.OauthAuthenticationException;

@Path("/{user_external_id}/fic")
public class FicService extends Validatable {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postNewFic(Fic fic, @Context HttpServletRequest request) {
		Response rtn = null;

		try {
			new FicDAO().create(fic, super.getUserNumericalID(request));
			rtn = Response.status(Response.Status.CREATED).build();

		} catch (OauthAuthenticationException e) {

			rtn = ResponseHelper.getError(401, e.getMessage(), "Invalid or missing token");
		} catch (SQLException e) {

			rtn = ResponseHelper.getError(503, e.getMessage(), "Database error");
		} catch (Exception e) {
			rtn = ResponseHelper.getError(500, e.getMessage(), "Internal server error");
		}

		return rtn;

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUserFics(@Context HttpServletRequest request) {
		Response rtn = null;

		try {

			rtn = Response.status(Response.Status.CREATED).entity(new FicDAO().read(super.getUserNumericalID(request)))
					.build();

		} catch (OauthAuthenticationException e) {

			rtn = ResponseHelper.getError(401, e.getMessage(), "Invalid or missing token");
		} catch (SQLException e) {

			rtn = ResponseHelper.getError(503, e.getMessage(), "Database error");
		} catch (Exception e) {

			rtn = ResponseHelper.getError(500, e.getMessage(), "Internal server error");
		}

		return rtn;

	}

}