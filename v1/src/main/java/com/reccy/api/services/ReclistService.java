//package com.reccy.api.services;
//
//import java.sql.SQLException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import com.ficcy.api.core.Reclist;
//import com.ficcy.api.dao.RecDAO;
//import com.ficcy.api.dao.ReclistDAO;
//import com.ficcy.api.lib.ResponseHelper;
//import com.stormpath.sdk.error.authc.OauthAuthenticationException;
//
//@Path("/reclists")
//public class ReclistService extends Validatable {
//
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAllRecs(@Context HttpServletRequest request) {
//
//		Response rtn = null;
//
//		try {
//
//			rtn = Response.status(Response.Status.OK).entity(
//					new RecDAO().read(super.getUserNumericalID(request), super.getAccount(request).getUsername()))
//					.build();
//
//		} catch (OauthAuthenticationException e) {
//
//			rtn = ResponseHelper.getError(401, e.getMessage(), "Invalid or missing token");
//		} catch (SQLException e) {
//
//			rtn = ResponseHelper.getError(503, e.getMessage(), "Database error");
//		} catch (Exception e) {
//
//			rtn = ResponseHelper.getError(500, e.getMessage(), "Internal server error");
//		}
//
//		return rtn;
//
//	}
//
//	@GET
//	@Path("/{reclist_external_id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getReclist(@PathParam("reclist_external_id") String externalID,
//			@Context HttpServletRequest request) {
//
//		Response rtn = null;
//
//		try {
//
//			rtn = Response.status(Response.Status.OK).entity(new ReclistDAO().read(externalID,
//					super.getUserNumericalID(request), super.getAccount(request).getUsername())).build();
//
//		} catch (OauthAuthenticationException e) {
//
//			rtn = ResponseHelper.getError(401, e.getMessage(), "Invalid or missing token");
//		} catch (SQLException e) {
//
//			rtn = ResponseHelper.getError(503, e.getMessage(), "Database error");
//		} catch (Exception e) {
//
//			rtn = ResponseHelper.getError(500, e.getMessage(), "See message");
//		}
//
//		return rtn;
//
//	}
//
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response postNewReclist(Reclist rec, @Context HttpServletRequest request) {
//
//		Response rtn = null;
//
//		try {
//
//			new ReclistDAO().create(rec, super.getUserNumericalID(request));
//			rtn = Response.status(Response.Status.CREATED).build();
//
//		} catch (OauthAuthenticationException e) {
//
//			rtn = ResponseHelper.getError(401, e.getMessage(), "Invalid or missing token");
//		} catch (SQLException e) {
//
//			rtn = ResponseHelper.getError(503, e.getMessage(), "Database error");
//		} catch (Exception e) {
//			rtn = ResponseHelper.getError(500, e.getMessage(), "Internal server error");
//		}
//
//		return rtn;
//
//	}
//
////	@DELETE
////	@Path("/{rec_external_id}")
////	@Produces(MediaType.APPLICATION_JSON)
////	public Response deleteRec(@PathParam("rec_external_id") String externalID, @Context HttpServletRequest request) {
////
////		Response rtn = null;
////
////		try {
////
////			new RecDAO().delete(externalID, super.getUserNumericalID(request));
////			rtn = Response.status(Response.Status.NO_CONTENT).build();
////
////		} catch (OauthAuthenticationException e) {
////
////			rtn = ResponseHelper.getError(401, e.getMessage(), "Invalid or missing token");
////		} catch (SQLException e) {
////
////			rtn = ResponseHelper.getError(503, e.getMessage(), "Database error");
////		} catch (Exception e) {
////			rtn = ResponseHelper.getError(500, e.getMessage(), "Internal server error");
////		}
////
////		return rtn;
////
////	}
//
//}