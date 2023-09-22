package com.axonivy.portal.rest;

import java.util.Date;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.naming.NoPermissionException;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.axonivy.portal.dto.ClientStatisticDto;
import com.axonivy.portal.service.ClientStatisticService;
import com.google.gson.GsonBuilder;

import ch.ivy.addon.portal.chat.GsonUTCDateAdapter;
import ch.ivy.addon.portalkit.statistics.ClientStatisticResponse;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path(value = "statistic-data-service")
@RolesAllowed(value = { ISecurityConstants.TOP_LEVEL_ROLE_NAME })
public class ClientStatisticRestService {

  @POST
  @Path(value = "Data")
  @Consumes(value = MediaType.APPLICATION_JSON)
  @Produces(value = MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get chart data by Id", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = AggregationResult.class)) }),
      @ApiResponse(responseCode = "404", description = "Chart Id not found"),
      @ApiResponse(responseCode = "403", description = "Don't have permission to get the data"),
      @ApiResponse(responseCode = "406", description = "Invalid call") })
  public Response getData(ClientStatisticDto payload) {
    if (Optional.ofNullable(payload).map(ClientStatisticDto::getChartId).isEmpty()) {
      return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    try {
      ClientStatisticResponse result = ClientStatisticService.getInstance().getData(payload);
      return Response.ok(result).build();
    } catch (NotFoundException e) {
      return buildResponse(Status.NOT_FOUND, e);
    } catch (NoPermissionException e) {
      return Response.status(Status.FORBIDDEN)
          .entity(Ivy.cms()
              .co("/Dialogs/com/axonivy/portal/dashboard/component/ClientStatisticWidget/NoPermissionChartMessage"))
          .build();
    }
  }

  private Response buildResponse(Status status, Exception e) {
    String message = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).setPrettyPrinting()
        .disableHtmlEscaping().create().toJson(e.getMessage());

    return Response.status(status).entity(message).build();
  }
}
