package com.axonivy.portal.rest;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.axonivy.portal.dto.StatisticDto;
import com.axonivy.portal.service.StatisticService;

import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.security.ISecurityConstants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path(value = "statistics")
@RolesAllowed(value = { ISecurityConstants.TOP_LEVEL_ROLE_NAME })
public class StatisticRestService {

  @POST
  @Path(value = "data")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get chart data by Id", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = AggregationResult.class)) }),
      @ApiResponse(responseCode = "406", description = "Invalid call") })
  public Response getData(StatisticDto payload) {
    if (Optional.ofNullable(payload).map(StatisticDto::getChartId).isEmpty()) {
      return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    try {
      StatisticResponse result = StatisticService.getInstance().getStatisticData(payload);
      return Response.ok(result).build();
    } catch (NotFoundException e) {
      return Response.ok(e.getMessage()).build();
    } catch (NoPermissionException e) {
      return Response.ok(e.getMessage()).build();
    }
  }

}
