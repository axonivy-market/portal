package ch.ivy.addon.portaltemplate.generic.bean;

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
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;

@SuppressWarnings("unchecked")
@RunWith(PowerMockRunner.class)
public class CaseWidgetBeanTest {
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
  @PrepareForTest({CaseWidgetBean.class, CaseUtils.class})
  public void testGetAdditionalCaseDetailsPageUri() throws Exception {
    PowerMockito.mockStatic(CaseUtils.class);
    
    ICase iCase = PowerMockito.mock(ICase.class);
    PowerMockito.when(iCase.getAdditionalProperty(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString())).thenReturn(StringUtils.EMPTY);
    
    PowerMockito.when(CaseUtils.getProcessStartUriWithCaseParameters(Matchers.any(ICase.class), Matchers.anyString())).thenReturn(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE);
    Assert.assertEquals(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(iCase)); 
  }
  
  @Test
  @PrepareForTest({CaseWidgetBean.class})
  public void testGetAdditionalCaseDetailsPageUriWithCustomization() throws Exception {
    ICase iCase = PowerMockito.mock(ICase.class);
    PowerMockito.when(iCase.getAdditionalProperty(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString())).thenReturn(CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE);
    
    Assert.assertEquals(CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(iCase)); 
  }
  
}
