package com.axonivy.portal.components.publicapi;

import java.util.Arrays;
import java.util.List;

import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

/**
 * <p>Finds an {@code IWebStartable} by its process relative link.</p>
 * <p>This method searches for an {@code IWebStartable} based on the provided process path. It returns the {@code IWebStartable} if found.</p>
 * <p>The process path can be either a relative link to a process or an external URL.</p>
 *
 * <p>Example:</p>
 * <pre>{@code
 * IWebStartable startable = IWebStartableAPI.findIWebStartableByProcessRelativeLink("designer/pro/portal/1549F58C18A6C562/DefaultApplicationHomePage.ivp");
 * }</pre>
 *
 * <p>If the {@code processPath} starts with "/", the method removes the leading slash before processing.</p>
 *
 * @param processPath The process path or external link to search for.
 * @return The {@code IWebStartable} if found.
 * @throws PortalException if the {@code IWebStartable} cannot be found.
 */
public class IWebStartableAPI {
  
  private IWebStartableAPI() {}

  public static IWebStartable findIWebStartableByProcessRelativeLink(String processPath) {
    List<IWebStartable> iWebStartables = IWorkflowSession.current().getAllStartables().toList();
    IWebStartable targetStartable = null;
    if (processPath.startsWith("/")) {
      processPath = processPath.substring(1);
    }
    String[] processPathSegments = processPath.split("/");
    String remainingPath = String.join("/", Arrays.copyOfRange(processPathSegments, 1, processPathSegments.length));
    Ivy.log().error(remainingPath);
    for (IWebStartable startable : iWebStartables) {
      String startableRelativeLink = startable.getLink().getRelative();
      if (startableRelativeLink.endsWith(remainingPath)) {
        targetStartable = startable;
        break;
      }
    }
    if (targetStartable == null) {
      throw new PortalException(String.format("Cannot find IWebStartable by process path [%s].", processPath));
    }
    return targetStartable;
  }
}
