package ch.ivy.addon.portalkit.service;

import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.ivyteam.ivy.workflow.IProcessStart;

public class AiProcessService {

  private static final String AI_DASHBOARD_FRIENDLY_REQUEST_PATH = "Start Processes/AiStart/AssistantDashboard.ivp";

  private static AiProcessService instance;

  public static AiProcessService getInstance() {
    if (instance == null) {
      instance = new AiProcessService();
    }
    return instance;
  }

  public IProcessStart findAssistantDashboardProcess() {
    return ProcessStartUtils.findProcessStartByUserFriendlyRequestPath(
        AI_DASHBOARD_FRIENDLY_REQUEST_PATH);
  }
}
