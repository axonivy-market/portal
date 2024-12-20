package com.axonivy.portal.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.axonivy.portal.payload.SearchPayload;
import com.axonivy.portal.response.GlobalSearchResponse;
import com.axonivy.portal.service.GlobalSearchService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path(value = "global-search")
@RolesAllowed(value = { ISecurityConstants.TOP_LEVEL_ROLE_NAME })
public class GlobalSearchRestService {

  private GlobalSearchService service = GlobalSearchService.getInstance();

  @POST
  @Path(value = "processes")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }) })
  public Response processes(SearchPayload payload) {
    try {
      GlobalSearchResponse result = service.searchProcesses(payload);
      return Response.ok(result).build();
    } catch (NotFoundException e) {
      Ivy.log().error(e);
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @POST
  @Path(value = "tasks")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }) })
  public Response tasks(SearchPayload payload) {
    try {
      GlobalSearchResponse result = service.searchTasks(payload);
      return Response.ok(result).build();
    } catch (NotFoundException e) {
      Ivy.log().error(e);
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @POST
  @Path(value = "cases")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }) })
  public Response cases(SearchPayload payload) {
    try {
      GlobalSearchResponse result = service.searchCases(payload);
      return Response.ok(result).build();
    } catch (NotFoundException e) {
      Ivy.log().error(e);
      return Response.status(Status.NOT_FOUND).build();
    }
  }
}
