package ch.ivy.addon.portalkit.rest;

import java.util.Date;
import java.util.Objects;

import javax.annotation.security.RolesAllowed;
import javax.naming.NoPermissionException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.GsonBuilder;

import ch.ivy.addon.portal.chat.GsonUTCDateAdapter;
import ch.ivy.addon.portalkit.dto.statisticChart.StatisticDataDto;
import ch.ivy.addon.portalkit.service.StatisticChartService;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;


@Path(value = "statistic-service")
@RolesAllowed(value = {"Everybody"})
public class StatisticRestService {

  private StatisticChartService service = new StatisticChartService();

  @GET
  @Path(value = "Data")
  @Produces(value = "application/json")
  public Response getData() {
    return Response.ok().build();
  }

  @POST
  @Path(value = "Data")
  @Consumes(value = "application/json")
  @Produces(value = "application/json")
  public Response getData(StatisticDataDto payload) {
    if (Objects.isNull(payload) || Objects.isNull(payload.getChartId())) {
      return Response.status(Status.NOT_ACCEPTABLE).build();
    } else {
      try {
        AggregationResult result = service.getInstance().callBack(payload);
        return Response.ok(result).build();
      } catch (NotFoundException e) {
        return Response.status(Status.NOT_FOUND).entity(toJson(e.getMessage())).build();
      } catch (NoPermissionException e) {
        return Response.status(Status.FORBIDDEN).entity(toJson(e.getMessage())).build();
      }
    }
  }

  private String toJson(Object object) {
    return new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).setPrettyPrinting()
        .disableHtmlEscaping().create().toJson(object);
  }
}
