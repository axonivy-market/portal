package com.axonivy.portal.components.publicapi;

import java.util.Arrays;
import java.util.List;

import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.IvyConstants;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

/**
 * <p>
 * Finds an {@code IWebStartable} by its process relative link.
 * </p>
 * <p>
 * This method searches for an {@code IWebStartable} based on the provided process path. It returns the
 * {@code IWebStartable} if found.
 * </p>
 * <p>
 * The process path can be either a relative link to a process or an external URL.
 * </p>
 *
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * {code
 *   IWebStartable startable = IWebStartableAPI
 *       .findIWebStartableByProcessRelativeLink("designer/pro/portal/1549F58C18A6C562/DefaultApplicationHomePage.ivp");
 * }
 * </pre>
 *
 * <p>
 * If the {@code processPath} starts with "/", the method removes the leading slash before processing.
 * </p>
 *
 * @param processPath The process path or external link to search for.
 * @return The {@code IWebStartable} if found.
 * @throws PortalException if the {@code IWebStartable} cannot be found.
 */
@SuppressWarnings("restriction")
public class IWebStartableAPI {

  private IWebStartableAPI() {}


  /**
   * Finds a web-startable process based on its relative link, specifically designed for the path format used in Axon
   * Ivy Portal versions 10 and above.
   *
   * <p>
   * **v10+ Path Format:**
   * </p>
   * <ul>
   * <li>The path should start with the project name, followed by the application name, and finally the request start
   * element name with the `.ivp` extension.</li>
   * <li>Example: `project/application/ processElementId/MyProcess.ivp`</li>
   * <li>Leading slashes are automatically removed.</li>
   * </ul>
   *
   * @param processPath The relative path to the process in the v10+ format.
   * @return The corresponding {@link IWebStartable} object if found.
   * @throws PortalException if the web-startable process cannot be found.
   */
  public static IWebStartable findIWebStartableByProcessRelativeLinkVer10(String processPath) {
    List<IWebStartable> iWebStartables = IWorkflowSession.current().getAllStartables().toList();
    IWebStartable targetStartable = null;
    processPath = removeLeadingSlash(processPath);
    String[] processPathSegments = processPath.split("/");
    String remainingPath = String.join("/", Arrays.copyOfRange(processPathSegments, 1, processPathSegments.length));
    targetStartable = findTargetStartable(iWebStartables, remainingPath);
    if (targetStartable == null) {
      throw new PortalException(String.format("Cannot find IWebStartable by process path [%s].", processPath));
    }
    return targetStartable;
  }

  /**
   * Finds a web-startable process based on its relative link, specifically designed for the path format used in Axon
   * Ivy Portal versions 8 and before.
   *
   * <p>
   * **8 Path Format:**
   * </p>
   * <ul>
   * <li>The path should start with the Context URL, followed by the request path process, application, project name,
   * process element id and finally the request start element name with the `.ivp` extension.</li>
   * <li>Example: `ivy/pro/application/project-name/ process-element-id/MyProcess.ivp`</li>
   * <li>Leading slashes are automatically removed.</li>
   * </ul>
   *
   * @param processPath The relative path to the process in the v10+ format.
   * @return The corresponding {@link IWebStartable} object if found.
   * @throws PortalException if the web-startable process cannot be found.
   */
  public static IWebStartable findIWebStartableByProcessRelativeLinkVer8(String processPath) {
    List<IWebStartable> iWebStartables = IWorkflowSession.current().getAllStartables().toList();
    IWebStartable targetStartable = null;
    processPath = removeLeadingSlash(processPath);
    String[] oldProcessPathSegments = processPath.split("/");
    String[] modifiedSegments = Arrays.copyOfRange(oldProcessPathSegments, 1, oldProcessPathSegments.length);
    String application = modifiedSegments[1];
    modifiedSegments[0] = application;
    modifiedSegments[1] = IvyConstants.REQUEST_PATH_PROCESS;
    String remainingPath = String.join("/", modifiedSegments);
    targetStartable = findTargetStartable(iWebStartables, remainingPath);
    if (targetStartable == null) {
      throw new PortalException(String.format("Cannot find IWebStartable by process path [%s].", processPath));
    }
    return targetStartable;
  }

  private static IWebStartable findTargetStartable(List<IWebStartable> iWebStartables, String targetPath) {
    for (IWebStartable startable : iWebStartables) {
      String startableRelativeLink = startable.getLink().getRelative();
      if (startableRelativeLink.endsWith(targetPath)) {
        return startable;
      }
    }
    return null;
  }

  private static String removeLeadingSlash(String processPath) {
    if (processPath.startsWith("/")) {
      processPath = processPath.substring(1);
    }
    return processPath;
  }
}
