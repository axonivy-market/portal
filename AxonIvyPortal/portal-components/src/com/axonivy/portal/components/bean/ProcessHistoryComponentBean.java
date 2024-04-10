package com.axonivy.portal.components.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.datamodel.internal.CaseHistoryLazyDataModel;
import com.axonivy.portal.components.util.HtmlUtils;
import com.axonivy.portal.components.util.UserFormatUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@ViewScoped
public class ProcessHistoryComponentBean implements Serializable {

  private static final long serialVersionUID = -7686350127605652402L;
  private CaseHistoryLazyDataModel dataModel = new CaseHistoryLazyDataModel("", false);
  private String businessEntityId;

  public void initData(String businessEntityId, boolean isCaseOwnerEnabled) {
    dataModel = new CaseHistoryLazyDataModel(businessEntityId, isCaseOwnerEnabled);
  }
  
  public CaseHistoryLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(CaseHistoryLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public String getBusinessEntityId() {
    return businessEntityId;
  }

  public void setBusinessEntityId(String businessEntityId) {
    this.businessEntityId = businessEntityId;
  }

  public String formatCaseDescription(String text) {
    String extractedText = HtmlUtils.parseTextFromHtml(text);
    if (StringUtils.isBlank(extractedText)) {
      return Ivy.cms().co("/Dialogs/com/axonivy/portal/components/ProcessHistory/NoDescription");
    }
    return extractedText;
  }

  public String getState(ICase iCase) {
    if (iCase == null) {
      return "";
    }
    return getDisplayState(iCase.getState());
  }

  public String getDisplayState(CaseState caseState) {
    if (caseState == CaseState.CREATED || caseState == CaseState.RUNNING) {
      return Ivy.cms().co("/Dialogs/com/axonivy/portal/components/ProcessHistory/CaseState/RUNNING");
    }
    return Ivy.cms().co("/Dialogs/com/axonivy/portal/components/ProcessHistory/CaseState/" + caseState);
  }

  public String format(String fullName, String username) {
    return UserFormatUtils.format(fullName, username);
  }

  public String formatWithTip(String fullName, String username) {
    return UserFormatUtils.formatWithTip(fullName, username);
  }
}
