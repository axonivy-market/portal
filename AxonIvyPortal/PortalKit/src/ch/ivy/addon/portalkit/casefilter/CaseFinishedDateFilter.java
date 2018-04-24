package ch.ivy.addon.portalkit.casefilter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFinishedDateFilter extends CaseFilter {

  private Date fromFinishedDate;
  private Date toFinishedDate;

  @Override
  public String label() {
    StringBuilder sb = new StringBuilder();
    sb.append(Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/Finished"));
    sb.append(" (");
    sb.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/from"));
    sb.append("/");
    sb.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/to"));
    sb.append(")");
    return sb.toString();
  }

  @Override
  public String value() {
    DateTimeGlobalSettingService service = new DateTimeGlobalSettingService();
    DateFormat formatter = new SimpleDateFormat(service.getDateTimePattern());
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

    CaseQuery query = CaseQuery.create();
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
          null,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co(
              "/ch.ivy.addon.portalkit.ui.jsf/common/dateFromBiggerThanTo"), null));
    }
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
