package com.axonivy.portal.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.axonivy.portal.payload.IvyToolPayload;

import ch.ivy.addon.portalkit.dto.ai.IvyTool;
import ch.ivyteam.ivy.security.ISecurityConstants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path(value = "assistant")
@RolesAllowed(value = { ISecurityConstants.TOP_LEVEL_ROLE_NAME })
public class AssistantRestService {

  @POST
  @Path(value = "/ivyTool")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = { @Content(mediaType = MediaType.APPLICATION_JSON) }) })
  public Response proceedIvyTool(IvyToolPayload payload) {
    IvyTool tool = BusinessEntityConverter.jsonValueToEntity(payload.getToolJson(), IvyTool.class);
    return Response.ok(tool.getResult()).build();
  }
}
