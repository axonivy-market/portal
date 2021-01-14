package ch.ivy.addon.portalkit.publicapi;

import static ch.ivy.addon.portalkit.util.ProcessUtils.getURLByKeyword;

import java.io.IOException;
import java.util.HashMap;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.StandardProcessType;

/**
 * Portal API for navigation not in iFrame
 *
 */
public final class PortalNavigatorAPI {
  private static final String PORTAL_PROCESS_START_NAME = "Start Processes/PortalStart/DefaultApplicationHomePage.ivp";
  private PortalNavigatorAPI() {}
  
  /**
   * Navigate to Portal home
   */
  public static void navigateToPortalHome() {
    String url = getURLByKeyword("DefaultApplicationHomePage.ivp", PORTAL_PROCESS_START_NAME, new HashMap<>());
    if (StringUtils.isNotBlank(url)) {
      redirect(url);
    }
  }

  /**
   * Navigate to PortalEndPage without finishing a task, e.g. clicking on Cancel button then back to previous page:
   * task list or task details or global search NOTES: is only used for the task not started in Portal IFrame
   */
  public static void navigateToPortalEndPage() {
    String defaultEndPage = getDefaultEndPage(); 
    redirect(defaultEndPage + "?endedTaskId=" + Ivy.wfTask().getId());
    Ivy.session().setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
  }
  
  private static String getDefaultEndPage() {
    return IvyExecutor.executeAsSystem(() -> Ivy.html().startRef(Ivy.wf().getStandardProcessImplementation(StandardProcessType.DefaultEndPage).getUserFriendlyRequestPath()));
  }
  
  private static void redirect(String url) {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    } catch (IOException ex) {
      throw new PortalException(ex);
    }
  }
}
