package com.axonivy.portal.components.publicapi;

import java.util.Arrays;
import java.util.List;

import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IWebStartableAPI {

  private IWebStartableAPI() {}

  public static IWebStartable findIWebStartableByProcessRelativeLink(String processPath) {
    // Retrieve all IWebStartable instances from the current session
    List<IWebStartable> iWebStartables = IWorkflowSession.current().getAllStartables().toList();
    IWebStartable targetStartable = null;

    // Remove leading slash if present
    if (processPath.startsWith("/")) {
      processPath = processPath.substring(1);
    }

    // Extract the ending segment of the processPath
    // For example, from
    // "/designer/pro/portal-components-examples/176465FBFE257CF3/showInvestmentRequestCustomFields.ivp"
    // we want to extract "showInvestmentRequestCustomFields.ivp"
    String[] processPathSegments = processPath.split("/");
    String remainingPath = String.join("/", Arrays.copyOfRange(processPathSegments, 1, processPathSegments.length));
    // Iterate over the list of startables
    Ivy.log().error(remainingPath);
    for (IWebStartable startable : iWebStartables) {
      String startableRelativeLink = startable.getLink().getRelative();
      Ivy.log().error(startableRelativeLink); // Log each startable relative link

      // Check if the startable relative link ends with the processPathEndingSegment
      if (startableRelativeLink.endsWith(remainingPath)) {
        targetStartable = startable;
        break; // Exit the loop once the target startable is found
      }
    }

    // If no matching startable is found, throw an exception
    if (targetStartable == null) {
      throw new PortalException(String.format("Cannot find IWebStartable by process path [%s].", processPath));
    }

    // Log and return the found startable
    Ivy.log().error(targetStartable.getLink().getRelative());
    return targetStartable;
  }

}
