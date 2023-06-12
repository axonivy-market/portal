package ch.ivy.addon.portaltemplate.generic.bean;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portal.generic.bean.ServerInformationBean;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

@RunWith(PowerMockRunner.class)
public class ServerInformationBeanTest {
  
  private ServerInformationBean informationBean;
  
  @Before
  public void initData(){
    informationBean = new ServerInformationBean();
  }
  
  @Test
  @PrepareForTest({Ivy.class,IApplication.class})
  public void testGetEnvironmentInCaseNotHaveActiveEnv() {
    IApplication application = mock(IApplication.class);
    mockStatic(Ivy.class);
    mockStatic(IApplication.class);
    when(IApplication.current()).thenReturn(application);
    when(application.getActiveEnvironment()).thenReturn(StringUtils.EMPTY);
    ServerInformationBean informationBean = new ServerInformationBean();
    assertEquals("Default", informationBean.getEnvironment());
  }
  
  @Test
  @PrepareForTest({Ivy.class,IApplication.class})
  public void testGetEnvironmentInCaseHaveActiveEnv() {
    IApplication application = mock(IApplication.class);
    mockStatic(Ivy.class);
    mockStatic(IApplication.class);
    when(IApplication.current()).thenReturn(application);
    when(application.getActiveEnvironment()).thenReturn("Server");
    assertEquals("Server", informationBean.getEnvironment());
  }
}
