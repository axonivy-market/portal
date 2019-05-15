package ch.ivy.addon.portaltemplate.generic.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.bean.CaseWidgetBean;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.workflow.ICase;

@RunWith(PowerMockRunner.class)
public class CaseWidgetBeanTest {
  private static final String CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE = "CustomizationAdditionalCaseDetailsPage";
  private static final String DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE = "DefaultAdditionalCaseDetailsPage";

  CaseWidgetBean caseWidgetBean;
  
  @Mock
  private FacesContext facesContext;
  @Mock
  private ExternalContext externalContext;

  @Before
  @PrepareForTest(PermissionUtils.class)
  public void init() {
    PowerMockito.mockStatic(PermissionUtils.class);
    caseWidgetBean = new CaseWidgetBean();
  }
  
  @Test
  @PrepareForTest({CaseWidgetBean.class, CaseUtils.class, PermissionUtils.class, FacesContext.class})
  public void testGetAdditionalCaseDetailsPageUri() throws Exception {
    PowerMockito.mockStatic(CaseUtils.class);
    
    ICase iCase = PowerMockito.mock(ICase.class);
    PowerMockito.when(iCase.customFields().textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString()).getOrNull()).thenReturn(StringUtils.EMPTY);
    
    PowerMockito.when(CaseUtils.getProcessStartUriWithCaseParameters(Matchers.any(ICase.class), Matchers.anyString())).thenReturn(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE);
    
    PowerMockito.mockStatic(FacesContext.class);

    PowerMockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
    PowerMockito.when(facesContext.getExternalContext())
        .thenReturn(externalContext);
    PowerMockito.when(externalContext.getApplicationContextPath()).thenReturn("");
    
    Assert.assertEquals(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(iCase)); 
  }
  
  @Test
  @PrepareForTest({CaseWidgetBean.class, PermissionUtils.class, FacesContext.class})
  public void testGetAdditionalCaseDetailsPageUriWithCustomization() throws Exception {
    ICase iCase = PowerMockito.mock(ICase.class);
    PowerMockito.when(iCase.customFields().textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString()).getOrNull()).thenReturn(CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE);
    PowerMockito.mockStatic(FacesContext.class);

    PowerMockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
    PowerMockito.when(facesContext.getExternalContext())
        .thenReturn(externalContext);
    PowerMockito.when(externalContext.getApplicationContextPath()).thenReturn("");
    Assert.assertEquals(CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE, caseWidgetBean.getAdditionalCaseDetailsPageUri(iCase)); 
  }
  
}
