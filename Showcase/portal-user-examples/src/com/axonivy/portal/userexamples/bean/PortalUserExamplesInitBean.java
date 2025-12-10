package com.axonivy.portal.userexamples.bean;

import com.axonivy.portal.components.enums.PortalVariable;
import com.axonivy.portal.userexamples.enums.PortalUserExamplesVariable;
import com.axonivy.portal.userexamples.utils.JsonUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.ProgramConfig;
import ch.ivyteam.ivy.server.restricted.EngineMode;

public class PortalUserExamplesInitBean extends AbstractProcessStartEventBean {
  
  private static final String PORTAL_CUSTOM_STATISTIC_KEY = "Portal.CustomStatistic";
  private static final String GENERATE_PROCUREMENT_DATA_SIGNAL_CODE = "com:axonivy:portal:portaluserexamples:createProcurementDataForSampleStatistic";

  public PortalUserExamplesInitBean() {
    super("Init sample config", "Append the sample statistic and dashboard config to Portal json");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, ProgramConfig configuration) {
    super.initialize(eventRuntime, configuration);
    getEventBeanRuntime().poll().disable();
    if (EngineMode.isAnyOf(EngineMode.DEMO, EngineMode.DESIGNER_EMBEDDED)) {
      initSampleStatisticConfig();

      // Avoid generating lots of tasks and cases when re-run in Designer
      if (!isSignalAlreadyRun(GENERATE_PROCUREMENT_DATA_SIGNAL_CODE)) {
        Ivy.wf().signals().send(GENERATE_PROCUREMENT_DATA_SIGNAL_CODE);
      }
    }
  }

  public static void initSampleStatisticConfig() {
    initSampleDashboards();
    initSampleStatistics();
  }

  private static void initSampleDashboards() {
    String sampleDashboardsJson = Ivy.var().get(PortalUserExamplesVariable.SAMPLE_DASHBOARD_KEY.getKey());
    String portalDashboardsJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
    String combinedDashboard = JsonUtils.mergeJsonArrays(portalDashboardsJson, sampleDashboardsJson);
    Ivy.var().set(PortalVariable.DASHBOARD.key, combinedDashboard);
  }

  private static void initSampleStatistics() {
    String sampleStatisticJson = Ivy.var().get(PortalUserExamplesVariable.SAMPLE_STATISTIC_KEY.getKey());
    String portalStatisticJson = Ivy.var().get(PORTAL_CUSTOM_STATISTIC_KEY);
    String combinedStatistic = JsonUtils.mergeJsonArrays(portalStatisticJson, sampleStatisticJson);
    Ivy.var().set(PORTAL_CUSTOM_STATISTIC_KEY, combinedStatistic);
  }

  private static boolean isSignalAlreadyRun(String signalCode) {
    return Ivy.wf().signals().history().createSignalEventQuery()
        .where().signalCode().isEqual(signalCode)
        .executor().count() > 0;
  }
}
