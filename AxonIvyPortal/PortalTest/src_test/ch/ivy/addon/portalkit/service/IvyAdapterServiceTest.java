package ch.ivy.addon.portalkit.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.globalvars.IGlobalVariableContext;
import ch.ivyteam.log.Logger;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Ivy.class)
public class IvyAdapterServiceTest {

  @Test
  public void testGetMaximumRetryPortalDataSynchonizationTimes() {
    mockStatic(Ivy.class);
    IGlobalVariableContext context = mock(IGlobalVariableContext.class);
    when(context.get("PortalCallWebserviceMaxRetry")).thenReturn("10");
    when(Ivy.var()).thenReturn(context);

    IvyAdapterService service = new IvyAdapterService();
    int result = service.getMaximumRetryPortalDataSynchonizationTimes();

    assertEquals(10, result);
  }

  @Test
  public void testGetDefaultMaximumRetryPortalDataSynchonizationTimes() {
    mockStatic(Ivy.class);
    IGlobalVariableContext context = mock(IGlobalVariableContext.class);
    when(context.get("PortalCallWebserviceMaxRetry")).thenReturn("");
    when(Ivy.var()).thenReturn(context);
    Logger logger = mock(Logger.class);
    when(Ivy.log()).thenReturn(logger);

    IvyAdapterService service = new IvyAdapterService();
    int result = service.getMaximumRetryPortalDataSynchonizationTimes();
    
    Mockito.verify(logger).error(any(String.class), any(Exception.class));
    assertEquals(0, result);
  }
}
