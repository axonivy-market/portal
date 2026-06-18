package com.axonivy.portal.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.SingleProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestDashboardWidgetUtils {

  @Test
  void updateProcessStartIdForCombined_doesNothing_whenNotCombinedMode() throws Exception {
    var processWidget = new SingleProcessDashboardWidget();
    processWidget.setDisplayMode(ProcessWidgetMode.COMPACT_MODE);

    var process = new DashboardProcess();
    process.setStartLink("pro/start");

    invokeUpdateProcessStartIdForCombined(processWidget, process);

    assertThat(process.getProcessElementId()).isNull();
  }

  @Test
  void updateProcessStartIdForCombined_doesNothing_whenProcessElementIdAlreadySet() throws Exception {
    var processWidget = new SingleProcessDashboardWidget();
    processWidget.setDisplayMode(ProcessWidgetMode.COMBINED_MODE);

    var process = new DashboardProcess();
    process.setStartLink("pro/start");
    process.setProcessElementId("existing-element-id");

    invokeUpdateProcessStartIdForCombined(processWidget, process);

    assertThat(process.getProcessElementId()).isEqualTo("existing-element-id");
  }

  @Test
  void updateProcessStartIdForCombined_leavesElementIdNull_whenNoMatchingProcessStart() throws Exception {
    var processWidget = new SingleProcessDashboardWidget();
    processWidget.setDisplayMode(ProcessWidgetMode.COMBINED_MODE);

    var process = new DashboardProcess();
    process.setStartLink("no/matching/process/start");

    invokeUpdateProcessStartIdForCombined(processWidget, process);

    assertThat(process.getProcessElementId()).isNull();
  }

  private void invokeUpdateProcessStartIdForCombined(ProcessDashboardWidget processWidget, DashboardProcess process)
      throws Exception {
    Method method = DashboardWidgetUtils.class.getDeclaredMethod("updateProcessStartIdForCombined",
        ProcessDashboardWidget.class, DashboardProcess.class);
    method.setAccessible(true);
    method.invoke(null, processWidget, process);
  }
}
