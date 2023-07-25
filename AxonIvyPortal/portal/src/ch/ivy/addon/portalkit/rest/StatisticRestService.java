package ch.ivy.addon.portalkit.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.ivy.addon.portalkit.dto.statisticChart.callbackData;


@Path(value = "statistic-service")
@RolesAllowed(value = {"Everybody"})
public class StatisticRestService {

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
    return Response.ok().build();
  }
}
