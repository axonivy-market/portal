package com.axonivy.portal.rest;

import java.util.Optional;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.axonivy.portal.dto.StatisticDTO;
import com.axonivy.portal.service.StatisticService;

import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.security.ISecurityConstants;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Hidden
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
  public Response getData(StatisticDTO payload) {
    if (Optional.ofNullable(payload).map(StatisticDTO::getChartId).isEmpty()) {
      return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    try {
      StatisticResponse result = StatisticService.getInstance().getStatisticData(payload);
      return Response.ok(result).build();
    } catch (NotFoundException e) {
      return Response.ok(e.getMessage()).build();
    }
  }

}
