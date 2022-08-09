package ch.ivy.addon.portalkit.casefilter.impl;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFinishedDateFilter extends CaseFilter {

  private Date fromFinishedDate;
  private Date toFinishedDate;

  @Override
  public String label() {
    StringBuilder sb = new StringBuilder();
    sb.append(Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/Finished"))
    .append(" (")
    .append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/from"))
    .append("/")
    .append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/to"))
    .append(")");
    return sb.toString();
  }

  @Override
  public String value() {
    
    return ALL;
  }

  @Override
  public CaseQuery buildQuery() {
      return null;
  }

  @Override
  public void resetValues() {
    fromFinishedDate = null;
    toFinishedDate = null;
  }

  @Override
  public void validate() {
    if (fromFinishedDate != null && toFinishedDate != null && (fromFinishedDate.compareTo(toFinishedDate) > 0)) {
      FacesContext.getCurrentInstance().validationFailed();
      FacesContext.getCurrentInstance().addMessage(
          "advanced-filter-error-messages",
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co(
              "/ch.ivy.addon.portalkit.ui.jsf/common/dateFromBiggerThanTo"), null));
    }
  }

  @Override
  public boolean defaultFilter() {
    return true;
  }
  
  public Date getFromFinishedDate() {
    return fromFinishedDate;
  }

  public void setFromFinishedDate(Date fromFinishedDate) {
    this.fromFinishedDate = fromFinishedDate;
  }

  public Date getToFinishedDate() {
    return toFinishedDate;
  }

  public void setToFinishedDate(Date toFinishedDate) {
    this.toFinishedDate = toFinishedDate;
  }

}
