package com.reccy.api.services;

import java.sql.SQLException;
import java.util.ArrayList;

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

import com.reccy.api.core.Rec;
import com.reccy.api.dao.RecDAO;
import com.reccy.api.lib.ResponseHelper;
import com.stormpath.sdk.error.authc.OauthAuthenticationException;

@Path("/recs")
public class RecService extends Validatable {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRecs(@Context HttpServletRequest request) {

		Response rtn = null;

		try {

			rtn = Response.status(Response.Status.OK).entity(
					new RecDAO().read(super.getUserNumericalID(request), super.getAccount(request).getUsername()))
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
	@Path("/{rec_external_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRec(@PathParam("rec_external_id") String externalID, @Context HttpServletRequest request) {

		Response rtn = null;

		try {

			rtn = Response.status(Response.Status.OK).entity(new RecDAO().read(externalID,
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
	public Response postMultipleRecs(Rec fic, @Context HttpServletRequest request) {

		Response rtn = null;

		try {

			new RecDAO().create(fic, super.getUserNumericalID(request));
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

	@POST
	@Path("/multiple")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postMultipleRecs(ArrayList<Rec> recs, @Context HttpServletRequest request) {

		Response rtn = null;

		try {

			long userID = super.getUserNumericalID(request);
			RecDAO dao = new RecDAO();

			for (Rec rec : recs) {
				dao.create(rec, userID);
			}

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
	@Path("/{rec_external_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRec(@PathParam("rec_external_id") String externalID, @Context HttpServletRequest request) {

		Response rtn = null;

		try {

			new RecDAO().delete(externalID, super.getUserNumericalID(request));
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