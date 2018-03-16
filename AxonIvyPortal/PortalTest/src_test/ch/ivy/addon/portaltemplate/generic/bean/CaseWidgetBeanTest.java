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
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.bean.CaseWidgetBean;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivy.addon.portalkit.util.CaseUtils;

@SuppressWarnings("unchecked")
@RunWith(PowerMockRunner.class)
public class CaseWidgetBeanTest {
  private static final String REMOTE_CASE_KEY = "remoteCase";
  private static final String HOST = "http://localhost:8080/";
  private static final String CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE = "CustomizationAdditionalCaseDetailsPage";
  private static final String DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE = "DefaultAdditionalCaseDetailsPage";

  CaseWidgetBean caseWidgetBean;

  @Before
  public void init() {
    caseWidgetBean = new CaseWidgetBean();
  }
  
  @AfterClass
  public static void cleanUp() {

  }

  @Test
  @PrepareForTest({CaseWidgetBean.class, IvyAdapterService.class, CaseUtils.class})
  public void testGetAdditionalCaseDetailsPageUri() throws Exception {
    mockStatic(IvyAdapterService.class);
    PowerMockito.mockStatic(CaseUtils.class);
    UrlDetector detector = PowerMockito.mock(UrlDetector.class);
    
    RemoteCase remoteCase = createRemoteCaseWithCustomizationAdditionalProperty(StringUtils.EMPTY);    
    Map<String, Object> responses = new HashMap<>();
    responses.put(REMOTE_CASE_KEY, remoteCase);
    
    PowerMockito.when(IvyAdapterService.startSubProcess(Matchers.anyString(), Matchers.anyMap(), Matchers.anyList())).thenReturn(responses);
    PowerMockito.whenNew(UrlDetector.class).withNoArguments().thenReturn(detector);
    PowerMockito.when(CaseUtils.getProcessStartUriWithCaseParameters(Matchers.any(RemoteCase.class), Matchers.anyString())).thenReturn(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE);
    PowerMockito.when(detector.getHost(Matchers.anyString(), Matchers.any(Server.class))).thenReturn(HOST);
    Assert.assertEquals(HOST + DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(remoteCase)); 
  }
  
  @Test
  @PrepareForTest({CaseWidgetBean.class, IvyAdapterService.class})
  public void testGetAdditionalCaseDetailsPageUriWithCustomization() throws Exception {
    mockStatic(IvyAdapterService.class);
    UrlDetector detector = PowerMockito.mock(UrlDetector.class);
    
    RemoteCase remoteCase = createRemoteCaseWithCustomizationAdditionalProperty(CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE);    
    Map<String, Object> responses = new HashMap<>();
    responses.put(REMOTE_CASE_KEY, remoteCase);
    
    PowerMockito.when(IvyAdapterService.startSubProcess(Matchers.anyString(), Matchers.anyMap(), Matchers.anyList())).thenReturn(responses);
    PowerMockito.whenNew(UrlDetector.class).withNoArguments().thenReturn(detector);
    PowerMockito.when(detector.getHost(Matchers.anyString(), Matchers.any(Server.class))).thenReturn(HOST);
    Assert.assertEquals(HOST + CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(remoteCase)); 
  }
  
  private RemoteCase createRemoteCaseWithCustomizationAdditionalProperty(String addtionalCaseDetailsPage){
    RemoteCase remoteCase = new RemoteCase();
    remoteCase.setId(new Long(1));
    Server server = new Server();
    server.setId(new Long(1));
    remoteCase.setServer(server);
    remoteCase.setServerUrl(HOST);
    Map<String, String> additionalProperties = new HashMap<>();
    additionalProperties.put(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString(), addtionalCaseDetailsPage);
    remoteCase.setAdditionalProperties(additionalProperties);    
    return remoteCase;
  }
}
