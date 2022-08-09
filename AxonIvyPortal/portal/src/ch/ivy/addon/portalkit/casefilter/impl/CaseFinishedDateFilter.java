package ch.ivy.addon.portalkit.casefilter.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.util.CaseUtils;
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
    DateFormat formatter = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getDateTimePattern(), Ivy.session().getContentLocale());
    if (fromFinishedDate != null && toFinishedDate != null) {
      return String.format(DASH, formatter.format(fromFinishedDate), formatter.format(toFinishedDate));
    }

    if (fromFinishedDate != null) {
      return String.format(GREATER_EQUAL, formatter.format(fromFinishedDate));
    }

    if (toFinishedDate != null) {
      return String.format(LESS_EQUAL, formatter.format(toFinishedDate));
    }
    return ALL;
  }

  @Override
  public CaseQuery buildQuery() {
    if (fromFinishedDate == null && toFinishedDate == null) {
      return null;
    }

    CaseQuery query = CaseUtils.createBusinessCaseQuery();
    if (fromFinishedDate != null) {
      query.where().endTimestamp().isGreaterOrEqualThan(fromFinishedDate);
    }

    if (toFinishedDate != null) {
      query.where().endTimestamp().isLowerOrEqualThan(toFinishedDate);
    }
    return query;
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
