package ch.ivy.addon.portaltemplate.generic.bean;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
  private static final String CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE = "CustomizationAdditionalCaseDetailsPage";
  private static final String DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE = "DefaultAdditionalCaseDetailsPage";
  private static final String SUBPROCESS_PARAM_PROPERTY_VALUE = "propertyValue";

  CaseWidgetBean caseWidgetBean;

  @Before
  public void init() {
    caseWidgetBean = new CaseWidgetBean();
  }

  @Test
  @PrepareForTest({CaseWidgetBean.class, IvyAdapterService.class, UrlDetector.class})
  public void testGetAdditionalCaseDetailsPageUri() throws Exception {
    mockStatic(IvyAdapterService.class);
    mockStatic(UrlDetector.class);
    Map<String, Object> responses = new HashMap<>();
    responses.put(SUBPROCESS_PARAM_PROPERTY_VALUE, StringUtils.EMPTY);
    Mockito.when(IvyAdapterService.startSubProcess(Mockito.anyString(), Mockito.anyMap(), Mockito.anyList())).thenReturn(responses);
    Mockito.when(UrlDetector.getProcessStartUriWithCaseParameters(Mockito.any(), Mockito.anyString())).thenReturn(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE);
    Assert.assertEquals(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(createRemoteCase())); 
  }
  
  @Test
  @PrepareForTest({CaseWidgetBean.class, IvyAdapterService.class, UrlDetector.class})
  public void testGetAdditionalCaseDetailsPageUriWithCustomization() throws Exception {
    mockStatic(IvyAdapterService.class);
    mockStatic(UrlDetector.class);
    Map<String, Object> responses = new HashMap<>();
    responses.put(SUBPROCESS_PARAM_PROPERTY_VALUE, CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE);
    Mockito.when(IvyAdapterService.startSubProcess(Mockito.anyString(), Mockito.anyMap(), Mockito.anyList())).thenReturn(responses);
    Assert.assertEquals(CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(createRemoteCase())); 
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
