package ch.ivy.addon.portal.generic.bean;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portal.generic.events.GlobalSearchEvent;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

@RunWith(PowerMockRunner.class)
public class UserMenuBeanTest {

  private UserMenuBean userMenuBean;

  @Before
  public void init() {
    userMenuBean = new UserMenuBean();
  }

  @Test
  @PrepareForTest(Ivy.class)
  public void testLogout() {
    mockStatic(Ivy.class);
    IWorkflowSession session = mock(IWorkflowSession.class);
    when(Ivy.session()).thenReturn(session);
    userMenuBean.logout();

    Mockito.verify(session).logoutSessionUser();
    verifyNoMoreInteractions(session);
  }

  @Test
  @PrepareForTest(Ivy.class)
  public void testGetUserName() {
    String testUsername = "test";
    mockStatic(Ivy.class);
    IWorkflowSession session = mock(IWorkflowSession.class);
    when(Ivy.session()).thenReturn(session);
    when(session.getSessionUserName()).thenReturn(testUsername);
    assertEquals(userMenuBean.getUserName(), testUsername);
  }

  @Test
  @PrepareForTest(UserMenuBean.class)
  public void testIsShowServerInformation() throws Exception {
    final String SHOW_ENVIROMENT_INFO = "SHOW_ENVIRONMENT_INFO";
    GlobalSettingService globalSettingService = mock(GlobalSettingService.class);
    whenNew(GlobalSettingService.class).withNoArguments().thenReturn(globalSettingService);
    when(globalSettingService.findGlobalSettingValue(SHOW_ENVIROMENT_INFO)).thenReturn("true");
    assertEquals(Boolean.TRUE, userMenuBean.isShowServerInformation());

    verifyNew(GlobalSettingService.class).withNoArguments();
    Mockito.verify(globalSettingService).findGlobalSettingValue(SHOW_ENVIROMENT_INFO);
  }

  @Test
  @PrepareForTest(UserMenuBean.class)
  public void testIsHiddenLogout() throws Exception {
    final String HIDE_LOGOUT_BUTTON = "HIDE_LOGOUT_BUTTON";
    GlobalSettingService globalSettingService = mock(GlobalSettingService.class);
    whenNew(GlobalSettingService.class).withNoArguments().thenReturn(globalSettingService);
    when(globalSettingService.findGlobalSettingValue(HIDE_LOGOUT_BUTTON)).thenReturn("true");
    assertEquals(Boolean.TRUE, userMenuBean.isHiddenLogout());

    verifyNew(GlobalSettingService.class).withNoArguments();
    Mockito.verify(globalSettingService).findGlobalSettingValue(HIDE_LOGOUT_BUTTON);
  }

  @Test
  public void testSearchInCaseEmptyKeyword() {
    userMenuBean.setSearchKeyWord("");
    userMenuBean.search();
    assertEquals(0, userMenuBean.getFoundProcesses().size());
    assertEquals(0, userMenuBean.getFoundTasks().size());
  }

  @Test
  @PrepareForTest(Ivy.class)
  public void testSearchInCaseNotEmptyKeyword() throws Exception {
    String keyWord = "task";
    userMenuBean.setSearchKeyWord(keyWord);

    GlobalSearchEvent globalSearchEvent = new GlobalSearchEvent(keyWord, StringUtils.EMPTY, null, null);

    mockStatic(Ivy.class);
    IWorkflowContext workflowContext = mock(IWorkflowContext.class);
    when(Ivy.wf()).thenReturn(workflowContext);
    IApplication application = mock(IApplication.class);
    when(workflowContext.getApplication()).thenReturn(application);
    IWorkflowSession session = mock(IWorkflowSession.class);
    when(session.getAttribute(SessionAttribute.SERVER_ID.toString())).thenReturn(1L);
    when(session.getAttribute(SessionAttribute.SELECTED_APP.toString())).thenReturn("Portal");
    when(Ivy.session()).thenReturn(session);

    userMenuBean.search();

    verifyPrivate(UserMenuBean.class).invoke("sendSystemEvent", globalSearchEvent.toSystemEvent());
  }

  @Test
  public void testSetDataForSearchResultInCaseNoSearchResult() {
    List<IProcessStart> processes = new ArrayList<>();
    List<RemoteTask> tasks = new ArrayList<>();
    List<RemoteCase> cases = new ArrayList<>();
    userMenuBean.setDataForSearchResult(processes, tasks, cases);

    assertEquals(0, userMenuBean.getFoundProcesses().size());
    assertEquals(0, userMenuBean.getFoundTasks().size());
    assertEquals(Boolean.TRUE, userMenuBean.isHasNoRecordsFound());
  }

  @Test
  public void testSetDataForSearchResultInCaseHaveSearchResult() {
    List<IProcessStart> processes = new ArrayList<>();
    IProcessStart processStart = mock(IProcessStart.class);
    processes.add(processStart);
    List<RemoteTask> tasks = new ArrayList<>();
    RemoteTask task = new RemoteTask();
    tasks.add(task);
    List<RemoteCase> cases = new ArrayList<>();
    RemoteCase remoteCase = new RemoteCase();
    cases.add(remoteCase);
    userMenuBean.setDataForSearchResult(processes, tasks, cases);

    assertEquals(1, userMenuBean.getFoundProcesses().size());
    assertEquals(1, userMenuBean.getFoundTasks().size());
    assertEquals(1, userMenuBean.getFoundCases().size());
    assertEquals(Boolean.FALSE, userMenuBean.isHasNoRecordsFound());
  }
}
