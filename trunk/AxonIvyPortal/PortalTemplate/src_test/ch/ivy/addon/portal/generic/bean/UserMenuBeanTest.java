package ch.ivy.addon.portal.generic.bean;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
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
    ISecurityContext context = mock(ISecurityContext.class);
    when(Ivy.session()).thenReturn(session);
    when(Ivy.session().getSecurityContext()).thenReturn(context);
    userMenuBean.logout();

    Mockito.verify(session).logoutSessionUser();
    Mockito.verify(context).destroySession(session.getIdentifier());
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
    userMenuBean.setSearchKeyword("");
    userMenuBean.search();
    assertEquals(0, userMenuBean.getFoundWebStartables().size());
    assertEquals(0, userMenuBean.getFoundTasks().size());
  }
}
