package com.ficcy.api.services;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ficcy.api.core.Fic;
import com.ficcy.api.dao.FicDAO;
import com.ficcy.api.lib.ResponseHelper;
import com.stormpath.sdk.error.authc.OauthAuthenticationException;

@Path("/fic")
public class FicService extends Validatable {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFics(@Context HttpServletRequest request) {

		Response rtn = null;

		try {

			rtn = Response.status(Response.Status.OK).entity(
					new FicDAO().read(super.getUserNumericalID(request), super.getAccount(request).getUsername()))
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

	@GET
	@Path("/{fic_external_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFic(@PathParam("fic_external_id") String externalID, @Context HttpServletRequest request) {

		Response rtn = null;

		try {

			rtn = Response.status(Response.Status.OK).entity(new FicDAO().read(externalID,
					super.getUserNumericalID(request), super.getAccount(request).getUsername())).build();

		} catch (OauthAuthenticationException e) {

			rtn = ResponseHelper.getError(401, e.getMessage(), "Invalid or missing token");
		} catch (SQLException e) {

			rtn = ResponseHelper.getError(503, e.getMessage(), "Database error");
		} catch (Exception e) {

			rtn = ResponseHelper.getError(500, e.getMessage(), "See message");
		}

		return rtn;

	}

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

	@DELETE
	@Path("/{fic_external_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFic(@PathParam("fic_external_id") String externalID, @Context HttpServletRequest request) {

		Response rtn = null;

		try {

			new FicDAO().delete(externalID, super.getUserNumericalID(request));
			rtn = Response.status(Response.Status.NO_CONTENT).build();

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