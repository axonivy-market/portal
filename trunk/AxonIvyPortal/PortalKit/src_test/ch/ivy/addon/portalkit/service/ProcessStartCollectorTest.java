package ch.ivy.addon.portalkit.service;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Ivy.class, WorkflowNavigationUtil.class})
public class ProcessStartCollectorTest {

  @Test
  public void testFindProcessStartByUserFriendlyRequestPathWithInactiveApplication() {
    IApplication application = mockApplication(ActivityState.INACTIVE);

    ProcessStartCollector processStartCollector = new ProcessStartCollector(application);
    IProcessStart actualProcessStart =
        processStartCollector.findProcessStartByUserFriendlyRequestPath(StringUtils.EMPTY);

    Assert.assertNull(actualProcessStart);
  }

  @Test
  public void testFindProcessStartByUserFriendlyRequestPathWithInactiveProcessModel() {
    IProcessModel processModel = mockProcessModel(ActivityState.INACTIVE);
    IApplication application = mockApplication(ActivityState.ACTIVE);
    Mockito.when(application.getProcessModelsSortedByName()).thenReturn(Arrays.asList(processModel));

    ProcessStartCollector processStartCollector = new ProcessStartCollector(application);
    IProcessStart actualProcessStart =
        processStartCollector.findProcessStartByUserFriendlyRequestPath(StringUtils.EMPTY);

    Assert.assertNull(actualProcessStart);
  }

  @Test
  public void testFindProcessStartByUserFriendlyRequestPathWithInactiveProcessModelVersion() {
    IProcessModelVersion processModelVersion = mockProcessModelVersion(ActivityState.INACTIVE);
    IProcessModel processModel = mockProcessModel(ActivityState.ACTIVE);
    Mockito.when(processModel.getReleasedProcessModelVersion()).thenReturn(processModelVersion);
    IApplication application = mockApplication(ActivityState.ACTIVE);
    Mockito.when(application.getProcessModelsSortedByName()).thenReturn(Arrays.asList(processModel));

    ProcessStartCollector processStartCollector = new ProcessStartCollector(application);
    IProcessStart actualProcessStart =
        processStartCollector.findProcessStartByUserFriendlyRequestPath(StringUtils.EMPTY);

    Assert.assertNull(actualProcessStart);
  }

  @Test
  public void testFindProcessStartByUserFriendlyRequestPathReturnNotFoundProcessStart() {
    IProcessModelVersion processModelVersion = mockProcessModelVersion(ActivityState.ACTIVE);
    IProcessModel processModel = mockProcessModel(ActivityState.ACTIVE);
    Mockito.when(processModel.getReleasedProcessModelVersion()).thenReturn(processModelVersion);
    IApplication application = mockApplication(ActivityState.ACTIVE);
    Mockito.when(application.getProcessModelsSortedByName()).thenReturn(Arrays.asList(processModel));
    mockWorkflowProcessModelVersionFindProcessStart(processModelVersion, StringUtils.EMPTY);

    ProcessStartCollector processStartCollector = new ProcessStartCollector(application);
    IProcessStart actualProcessStart =
        processStartCollector.findProcessStartByUserFriendlyRequestPath(StringUtils.EMPTY);

    Assert.assertNull(actualProcessStart);
  }

  @Test
  public void testFindProcessStartByUserFriendlyRequestPathReturnProcessStart() {
    IProcessModelVersion processModelVersion = mockProcessModelVersion(ActivityState.ACTIVE);
    IProcessModel processModel = mockProcessModel(ActivityState.ACTIVE);
    Mockito.when(processModel.getReleasedProcessModelVersion()).thenReturn(processModelVersion);
    IApplication application = mockApplication(ActivityState.ACTIVE);
    Mockito.when(application.getProcessModelsSortedByName()).thenReturn(Arrays.asList(processModel));
    String requestPath = "Process Start";
    IProcessStart expectedProcessStart =
        mockWorkflowProcessModelVersionFindProcessStart(processModelVersion, requestPath);

    ProcessStartCollector processStartCollector = new ProcessStartCollector(application);
    IProcessStart actualProcessStart = processStartCollector.findProcessStartByUserFriendlyRequestPath(requestPath);

    Assert.assertEquals(expectedProcessStart, actualProcessStart);
  }

  private IApplication mockApplication(ActivityState activityState) {
    IApplication application = Mockito.mock(IApplication.class);
    Mockito.when(application.getActivityState()).thenReturn(activityState);
    return application;
  }

  private IProcessModel mockProcessModel(ActivityState activityState) {
    IProcessModel processModel = Mockito.mock(IProcessModel.class);
    Mockito.when(processModel.getActivityState()).thenReturn(activityState);
    return processModel;
  }

  private IProcessModelVersion mockProcessModelVersion(ActivityState activityState) {
    IProcessModelVersion processModelVersion = Mockito.mock(IProcessModelVersion.class);
    Mockito.when(processModelVersion.getActivityState()).thenReturn(activityState);
    return processModelVersion;
  }

  private IProcessStart mockWorkflowProcessModelVersionFindProcessStart(IProcessModelVersion processModelVersion,
      String requestPath) {
    IWorkflowProcessModelVersion workflowProcessModelVersion = Mockito.mock(IWorkflowProcessModelVersion.class);
    IProcessStart processStart = null;
    if (!requestPath.isEmpty()) {
      processStart = Mockito.mock(IProcessStart.class);
    }
    Mockito.when(workflowProcessModelVersion.findProcessStartByUserFriendlyRequestPath(requestPath)).thenReturn(
        processStart);
    PowerMockito.mockStatic(WorkflowNavigationUtil.class);
    PowerMockito.when(WorkflowNavigationUtil.getWorkflowProcessModelVersion(processModelVersion)).thenReturn(
        workflowProcessModelVersion);
    return processStart;
  }
}
