package ch.ivy.addon.portaltemplate.generic.bean;

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

import ch.ivy.addon.portal.generic.bean.UserMenuBean;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.cm.IContentManagementSystem;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

@RunWith(PowerMockRunner.class)
public class UserMenuBeanTest {

  private UserMenuBean userMenuBean;
  private GlobalSettingService globalSettingService;
  final String SHOW_ENVIROMENT_INFO = "SHOW_ENVIRONMENT_INFO";
  final String HIDE_LOGOUT_BUTTON = "HIDE_LOGOUT_BUTTON";
  final String LOGGED_IN_USER_FORMAT = "LOGGED_IN_USER_FORMAT";
  String testUsername = "test";
  

  @Before
  public void init() throws Exception {
    userMenuBean = new UserMenuBean();
    
    mockStatic(Ivy.class);
    IWorkflowSession session = mock(IWorkflowSession.class);
    IContentManagementSystem icms = mock(IContentManagementSystem.class);
    IUser user = mock(IUser.class);
    when(Ivy.session()).thenReturn(session);
    when(session.getSessionUserName()).thenReturn(testUsername);
    when(session.getSessionUser()).thenReturn(user);
    when(user.getFullName()).thenReturn(testUsername);
    when(Ivy.cms()).thenReturn(icms);
    
    globalSettingService = mock(GlobalSettingService.class);
    whenNew(GlobalSettingService.class).withNoArguments().thenReturn(globalSettingService);
    when(globalSettingService.findGlobalSettingValue(SHOW_ENVIROMENT_INFO)).thenReturn("true");
    when(globalSettingService.findGlobalSettingValue(HIDE_LOGOUT_BUTTON)).thenReturn("true");
    when(globalSettingService.findGlobalSettingValue(LOGGED_IN_USER_FORMAT)).thenReturn("USERNAME");
    
    this.userMenuBean.init();
  }

  @Test
  @PrepareForTest({ Ivy.class, UserMenuBean.class })
  public void testGetUserName() {
    assertEquals(userMenuBean.getLoggedInUser(), testUsername);
  }

  @Test
  @PrepareForTest({ Ivy.class, UserMenuBean.class })
  public void testIsShowServerInformation() throws Exception {
    assertEquals(Boolean.TRUE, userMenuBean.isShowServerInformation());
    verifyNew(GlobalSettingService.class).withNoArguments();
    Mockito.verify(globalSettingService).findGlobalSettingValue(SHOW_ENVIROMENT_INFO);
  }

  @Test
  @PrepareForTest({ Ivy.class, UserMenuBean.class })
  public void testIsHiddenLogout() throws Exception {
    assertEquals(Boolean.TRUE, userMenuBean.isHiddenLogout());
    verifyNew(GlobalSettingService.class).withNoArguments();
    Mockito.verify(globalSettingService).findGlobalSettingValue(HIDE_LOGOUT_BUTTON);
  }
}
