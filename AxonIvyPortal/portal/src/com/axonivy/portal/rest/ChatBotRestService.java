package com.axonivy.portal.rest;

import java.util.Date;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.axonivy.portal.payload.ChatbotPayload;
import com.google.gson.GsonBuilder;

import ch.ivy.addon.portal.chat.ChatMessage;
import ch.ivy.addon.portal.chat.GsonUTCDateAdapter;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bean.SecurityMemberDisplayNameFormatBean;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path(value = "chatbot")
@RolesAllowed(value = { ISecurityConstants.TOP_LEVEL_ROLE_NAME })
public class ChatBotRestService {

  private String userDisplayName;

  @POST
  @Path(value = "{clientId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = { @Content(mediaType = MediaType.APPLICATION_JSON) }) })
  public Response processes(@PathParam("clientId") String clientId, ChatbotPayload payload) {
    if (userDisplayName == null) {
      SecurityMemberDisplayNameFormatBean nameBean = new SecurityMemberDisplayNameFormatBean();
      userDisplayName = nameBean.generateFullDisplayNameForSecurityMember(Ivy.session().getSessionUser(),
          Ivy.session().getSessionUserName());
    }
    try {
      ChatMessage response = new ChatMessage();
      String content = payload.getMessage();
      if (content.contentEquals("link")) {
        mockLinkResponse(response);
      } else if (content.contentEquals("frame")) {
        mockFrameResponse(response);
      } else if (content.contentEquals("json")) {
        mockJsonResponse(response);
      } else if (content.contentEquals("image")) {
        mockImageResponse(response);
      } else {
        mockDefaultResponse(response);
      }

      response.setSentDate(new Date());
      response.setSender(userDisplayName);

      return Response.ok(toJson(response)).build();
    } catch (NotFoundException e) {
      Ivy.log().error(e);
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  private void mockImageResponse(ChatMessage response) {
    response.setMessage("This is an image of My Profile section\n\nhttps://market.axonivy.com/market-cache/portal/portal-guide/11.1.0/_images/my-profile.png");
  }

  private void mockFrameResponse(ChatMessage response) {
    CaseQuery query = CaseQuery.create();
    ICase caze = query.where().state().isEqual(CaseState.DONE).executor().firstResult();

    if (caze == null) {
      response.setMessage("Cannot find case!");
      return;
    }

    var link = PortalNavigator.buildPortalCaseDetailInFrameUrl(caze.uuid());
    response.setMessage("Below is the details of most recently done case\n\n<iframe>" + link + "</iframe>");
  }

  private void mockLinkResponse(ChatMessage response) {
    response.setMessage("This is a link to Axon ivy website\n\nhttps://www.axonivy.com/");
  }

  private void mockJsonResponse(ChatMessage response) {
    response.setMessage("""
        This is an JSON example of Portal Dashboard
        
        
        ```json
        [
         {
             "id" : "axon-ivy",
             "title" : "Axon Ivy",
             "permissions": ["#demo"],
             "url" : "https://www.axonivy.com/"
         },
         {
             "id" : "express",
             "title" : "Portal Express process",
             "permissions": ["Everybody"],
             "url" : "Portal Express process"
         },
         {
             "id" : "re-order-dashboard",
             "titles": [
                 {
                 "locale": "en",
                 "value": "Reorder your dashboards"
                 },
                 {
                 "locale": "de",
                 "value": "Dashboards neu anordnen"
                 }
             ],
             "permissions": ["Employee", "AXONIVY_PORTAL_ADMIN", "#daniel"],
             "url": "Start Processes/ExamplePortalStart/DashboardReorder.ivp",
             "params": {
                 "isPublicDashboard":"false"
             }
         }
     ]
     ```""");
  }

  private void mockDefaultResponse(ChatMessage response) {
    response.setMessage("""
        Please type 'link', 'json', 'image', or 'frame' to see special elements

        Below is long text example:

        Axon Ivy is a Process Automation Platform that simplifies and automates the interaction of humans with their digital systems. The platform is typically responsible for the most precious business cases where companies produce value. Here is how we do it:

        Visualize
        Our platform allows you to document business processes fast and intuitive. A shared view of users, roles, departments and technical systems involved in a business process improves your work. HR recruitment profiles become clearer, bottlenecks become obvious, ideas for effective improvements arise from anyone involved in the process.

        Automate
        Documented processes are good. But what you really want is to drive your highly valuable processes automatically. Employees are often interrupted by searching and filtering data from various tools and feeding this data into other technical systems. Even though value is produced in a well-known business case, there is a lack of a straightforward interface that guides the involved users through the process. Valuable data is often segregated and stored in various dedicated technical systems. With Axon Ivy, you can drive your process automatically. Our platform can easily orchestrate people, data and technical systems. You can generate an initial application that leads users through the process without hiring a software engineer. People can contribute to the process by using their favorite devices such as smartphones or workstations.
                    """);
  }

  private String toJson(Object object) {
    return new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).setPrettyPrinting()
        .disableHtmlEscaping().create().toJson(object);
  }
}
