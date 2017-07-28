package ch.ivy.addon.portalkit.taskfilter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskCreationDateFilter extends TaskFilter {

  private Date fromCreationDate;
  private Date toCreationDate;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskView/create");
  }

  @Override
  public String value() {
    DateTimeGlobalSettingService service = new DateTimeGlobalSettingService();
    DateFormat formatter = new SimpleDateFormat(service.getDateTimePattern());
    if (fromCreationDate != null && toCreationDate != null) {
      return String.format(DASH, formatter.format(fromCreationDate), formatter.format(toCreationDate));
    }

    if (fromCreationDate != null) {
      return String.format(GREATER_EQUAL, formatter.format(fromCreationDate));
    }

    if (toCreationDate != null) {
      return String.format(LESS_EQUAL, formatter.format(toCreationDate));
    }
    return ALL;
  }

  @Override
  public TaskQuery buildQuery() {
    if (fromCreationDate == null && toCreationDate == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    if (fromCreationDate != null) {
      query.where().startTimestamp().isGreaterOrEqualThan(fromCreationDate);
    }

    if (toCreationDate != null) {
      query.where().startTimestamp().isLowerOrEqualThan(toCreationDate);
    }
    return query;
  }

  @Override
  public void resetValues() {
    fromCreationDate = null;
    toCreationDate = null;
  }

  public Date getFromCreationDate() {
    return fromCreationDate;
  }

  public void setFromCreationDate(Date fromCreationDate) {
    this.fromCreationDate = fromCreationDate;
  }

  public Date getToCreationDate() {
    return toCreationDate;
  }

  public void setToCreationDate(Date toCreationDate) {
    this.toCreationDate = toCreationDate;
  }
}
