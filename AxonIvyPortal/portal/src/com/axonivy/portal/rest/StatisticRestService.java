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

import com.axonivy.portal.dto.statisticChart.StatisticDataDto;
import com.axonivy.portal.service.StatisticChartService;
import com.google.gson.GsonBuilder;

import ch.ivy.addon.portal.chat.GsonUTCDateAdapter;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.security.ISecurityConstants;


@Path(value = "statistic-service")
@RolesAllowed(value = {ISecurityConstants.TOP_LEVEL_ROLE_NAME})
public class StatisticRestService {

  private StatisticChartService service = new StatisticChartService();

  @POST
  @Path(value = "Data")
  @Consumes(value = MediaType.APPLICATION_JSON)
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response getData(StatisticDataDto payload) {
    if (Optional.ofNullable(payload).map(StatisticDataDto::getChartId).isEmpty()) {
      return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    try {
      AggregationResult result = service.getInstance().getData(payload);
      return Response.ok(result).build();
    } catch (NotFoundException e) {
      return buildResponse(Status.NOT_FOUND, e);
    } catch (NoPermissionException e) {
      return buildResponse(Status.FORBIDDEN, e);
    }
  }

  private Response buildResponse(Status status, Exception e) {
    String message = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).setPrettyPrinting()
        .disableHtmlEscaping().create().toJson(e.getMessage());

    return Response.status(status).entity(message).build();
  }
}
