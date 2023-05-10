package portalmigration.version112.service;

import org.apache.commons.lang3.StringUtils;

import portalmigration.version112.publicapi.ProcessStartAPI;

public class ProcessStartCollector {

  private static final String EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH = "Start Processes/GenericPredefinedWorkflowStart/GenericPredefinedProcessStart.ivp";

  private static ProcessStartCollector instance;
  private static String expressWorkflowStartLink;

  public static ProcessStartCollector getInstance() {
    if (instance == null) {
      instance = new ProcessStartCollector();
    }
    return instance;
  }

  public String findExpressWorkflowStartLink() {
    if (StringUtils.isEmpty(expressWorkflowStartLink)) {
      expressWorkflowStartLink = ProcessStartAPI
          .findRelativeUrlByProcessStartFriendlyRequestPath(EXPRESS_WORKFLOW_FRIENDLY_REQUEST_PATH);
    }
    return expressWorkflowStartLink;
  }
}
