package ch.ivy.addon.portalkit.rest;

import java.util.Objects;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ch.ivy.addon.portalkit.dto.statisticChart.callbackData;
import ch.ivy.addon.portalkit.service.StatisticChartService;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;


@Path(value = "statistic-service")
@RolesAllowed(value = {"Everybody"})
public class StatisticRestService {

  private StatisticChartService service = new StatisticChartService();

  @GET
  @Path(value = "callback")
  @Produces(value = "application/json")
  public Response callback() {
    return Response.ok().build();
  }

  @POST
  @Path(value = "callback")
  @Consumes(value = "application/json")
  @Produces(value = "application/json")
  public Response callback(callbackData payload) {
    if (Objects.isNull(payload) || Objects.isNull(payload.getChartId())) {
      return Response.status(Status.NOT_ACCEPTABLE).build();
    } else {
      AggregationResult result = service.getInstance().callBack(payload);
      if (Objects.isNull(result)) {
        return Response.status(Status.NOT_FOUND).build();
      } else {
        return Response.ok(result).build();
      }
    }
  }
}
