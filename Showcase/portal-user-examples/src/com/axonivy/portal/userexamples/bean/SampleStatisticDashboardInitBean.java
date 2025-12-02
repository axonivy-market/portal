package com.axonivy.portal.userexamples.bean;

import com.axonivy.portal.components.enums.PortalVariable;
import com.axonivy.portal.userexamples.enums.PortalUserExamplesVariable;
import com.axonivy.portal.userexamples.utils.JsonUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.ProgramConfig;
import ch.ivyteam.ivy.server.restricted.EngineMode;

public class SampleStatisticDashboardInitBean extends AbstractProcessStartEventBean {

  public SampleStatisticDashboardInitBean() {
    super("Init sample config", "Append the sample statistic and dashboard config to Portal json");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, ProgramConfig configuration) {
    super.initialize(eventRuntime, configuration);
    getEventBeanRuntime().poll().disable();
    Ivy.log().warn("I'm in initialize method");
    if (EngineMode.isAnyOf(EngineMode.DEMO, EngineMode.DESIGNER_EMBEDDED)) {
      Ivy.log().warn("I'm before init config");
      initSampleStatisticConfig();
      Ivy.wf().signals().send("createProcurementDataForSampleStatistic");
      Ivy.log().warn("I'm here");
    }
  }

  public static void initSampleStatisticConfig() {
    initSampleDashboards();
    initSampleStatistics();
  }

  private static void initSampleDashboards() {
    String sampleDashboardsJson = Ivy.var().get(PortalUserExamplesVariable.SAMPLE_DASHBOARD_KEY.key);
    String portalDashboardsJson = Ivy.var().get(PortalVariable.DASHBOARD.key);
    String combinedDashboard = JsonUtils.mergeJsonArrays(portalDashboardsJson, sampleDashboardsJson);
    Ivy.var().set(PortalVariable.DASHBOARD.key, combinedDashboard);
  }

  private static void initSampleStatistics() {
    String sampleStatisticJson = Ivy.var().get(PortalUserExamplesVariable.SAMPLE_STATISTIC_KEY.key);
    String portalStatisticJson = Ivy.var().get(PortalVariable.CUSTOM_STATISTIC.key);
    String combinedStatistic = JsonUtils.mergeJsonArrays(portalStatisticJson, sampleStatisticJson);
    Ivy.var().set(PortalVariable.CUSTOM_STATISTIC.key, combinedStatistic);
  }
}
