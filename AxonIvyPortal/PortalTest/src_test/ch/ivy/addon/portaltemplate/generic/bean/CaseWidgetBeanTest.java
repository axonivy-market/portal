package ch.ivy.addon.portaltemplate.generic.bean;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.bean.CaseWidgetBean;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.support.UrlDetector;

@SuppressWarnings("unchecked")
@RunWith(PowerMockRunner.class)
public class CaseWidgetBeanTest {
  private static final String HOST = "http://localhost:8080/";
  private static final String CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE = "CustomizationAdditionalCaseDetailsPage";
  private static final String DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE = "DefaultAdditionalCaseDetailsPage";
  private static final String SUBPROCESS_PARAM_PROPERTY_VALUE = "propertyValue";

  CaseWidgetBean caseWidgetBean;

  @Before
  public void init() {
    caseWidgetBean = new CaseWidgetBean();
  }
  
  @AfterClass
  public static void cleanUp() {

  }

  @Test
  @PrepareForTest({CaseWidgetBean.class, IvyAdapterService.class})
  public void testGetAdditionalCaseDetailsPageUri() throws Exception {
    mockStatic(IvyAdapterService.class);
    Map<String, Object> responses = new HashMap<>();
    responses.put(SUBPROCESS_PARAM_PROPERTY_VALUE, StringUtils.EMPTY);
    PowerMockito.when(IvyAdapterService.startSubProcess(Mockito.anyString(), Mockito.anyMap(), Mockito.anyList())).thenReturn(responses);
    UrlDetector detector = PowerMockito.mock(UrlDetector.class);
    PowerMockito.whenNew(UrlDetector.class).withNoArguments().thenReturn(detector);
    PowerMockito.when(detector.getProcessStartUriWithCaseParameters(Mockito.any(), Mockito.anyString())).thenReturn(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE);
    PowerMockito.when(detector.getHost(Mockito.any(Server.class))).thenReturn(HOST);
    Assert.assertEquals(HOST + DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(createRemoteCase())); 
  }
  
  @Test
  @PrepareForTest({CaseWidgetBean.class, IvyAdapterService.class})
  public void testGetAdditionalCaseDetailsPageUriWithCustomization() throws Exception {
    mockStatic(IvyAdapterService.class);
    Map<String, Object> responses = new HashMap<>();
    responses.put(SUBPROCESS_PARAM_PROPERTY_VALUE, CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE);
    Mockito.when(IvyAdapterService.startSubProcess(Mockito.anyString(), Mockito.anyMap(), Mockito.anyList())).thenReturn(responses);
    UrlDetector detector = PowerMockito.mock(UrlDetector.class);
    PowerMockito.whenNew(UrlDetector.class).withNoArguments().thenReturn(detector);
    PowerMockito.when(detector.getHost(Mockito.any(Server.class))).thenReturn(HOST);
    Assert.assertEquals(HOST + CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(createRemoteCase())); 
  }
  
  private RemoteCase createRemoteCase(){
    RemoteCase remoteCase = new RemoteCase();
    remoteCase.setId(new Long(1));
    Server server = new Server();
    server.setId(new Long(1));
    remoteCase.setServer(server);
    return remoteCase;
  }
}
