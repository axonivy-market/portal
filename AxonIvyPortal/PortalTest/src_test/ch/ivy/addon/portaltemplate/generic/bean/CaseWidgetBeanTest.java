package ch.ivy.addon.portaltemplate.generic.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.ivy.addon.portalkit.bean.CaseWidgetBean;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.custom.field.ICustomStringField;

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
  
  @SuppressWarnings("deprecation")
  @Test
  @PrepareForTest({CaseWidgetBean.class, CaseUtils.class, PermissionUtils.class, FacesContext.class})
  public void testGetAdditionalCaseDetailsPageUri() throws Exception {
    PowerMockito.mockStatic(CaseUtils.class);
    mockCustomFieldForCaseDetailsPage(StringUtils.EMPTY);
    PowerMockito.when(CaseUtils.getProcessStartUriWithCaseParameters(ArgumentMatchers.any(ICase.class), ArgumentMatchers.anyString())).thenReturn(DEFAULT_ADDITIONAL_CASE_DETAILS_PAGE);
    
    PowerMockito.mockStatic(FacesContext.class);

    PowerMockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
    PowerMockito.when(facesContext.getExternalContext())
        .thenReturn(externalContext);
    PowerMockito.when(externalContext.getApplicationContextPath()).thenReturn("");
  }
  
  @Test
  @PrepareForTest({CaseWidgetBean.class, PermissionUtils.class, FacesContext.class})
  public void testGetAdditionalCaseDetailsPageUriWithCustomization() throws Exception {
    mockCustomFieldForCaseDetailsPage(CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE);
    PowerMockito.mockStatic(FacesContext.class);

    PowerMockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
    PowerMockito.when(facesContext.getExternalContext())
        .thenReturn(externalContext);
    PowerMockito.when(externalContext.getApplicationContextPath()).thenReturn("");
  }

  private ICase mockCustomFieldForCaseDetailsPage(String detailsPage) {
    ICase iCase = PowerMockito.mock(ICase.class);
    ICustomFields iCustomFields = PowerMockito.mock(ICustomFields.class);
    ICustomStringField iCustomStringField = PowerMockito.mock(ICustomStringField.class);
    PowerMockito.when(iCase.customFields()).thenReturn(iCustomFields);
    PowerMockito.when(iCustomFields.textField(AdditionalProperty.CUSTOMIZATION_ADDITIONAL_CASE_DETAILS_PAGE.toString())).thenReturn(iCustomStringField);
    PowerMockito.when(iCustomStringField.getOrNull()).thenReturn(detailsPage);
    return iCase;
  }
  
}
