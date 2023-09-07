package com.axonivy.portal.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.axonivy.portal.dto.NotificationDto;
import com.axonivy.portal.service.NotificationService;

import ch.ivyteam.ivy.security.ISecurityConstants;

@RolesAllowed(value = { ISecurityConstants.TOP_LEVEL_ROLE_NAME })
@Path("notification")
public class NotificationRestService {

  private final NotificationService notificationService;

  public NotificationRestService() {
    notificationService = NotificationService.getInstance();
  }

  @GET
  @Path("/test")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response getTest() {
    List<NotificationDto> test = notificationService.getNotification();
    return Response.ok(test).build();
  }

  @GET
  @Path("/unread")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(value = MediaType.APPLICATION_JSON)
  public Response getUnread() {
    long countUnread = notificationService.countUnread();
    return Response.ok(countUnread).build();
  }
}
