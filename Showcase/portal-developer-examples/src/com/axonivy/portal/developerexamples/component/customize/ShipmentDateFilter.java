package com.axonivy.portal.developerexamples.component.customize;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class ShipmentDateFilter extends TaskFilter {

  private Date fromShipmentDate;
  private Date toShipmentDate;

  @Override
  public String label() {
    StringBuilder sb = new StringBuilder();
    sb.append("Shipment Date");
    sb.append(" (");
    sb.append("from");
    sb.append("/");
    sb.append("to");
    sb.append(")");
    return sb.toString();
  }

  @Override
  public String value() {
    DateFormat formatter = new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getDateTimePattern(), Ivy.session().getContentLocale());
    if (fromShipmentDate != null && toShipmentDate != null) {
      return String.format(DASH, formatter.format(fromShipmentDate), formatter.format(toShipmentDate));
    }

    if (fromShipmentDate != null) {
      return String.format(GREATER_EQUAL, formatter.format(fromShipmentDate));
    }

    if (toShipmentDate != null) {
      return String.format(LESS_EQUAL, formatter.format(toShipmentDate));
    }
    return getAllLabel();
  }

  @Override
  public TaskQuery buildQuery() {
    if (fromShipmentDate == null && toShipmentDate == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    if (fromShipmentDate != null) {
      query.where().customField().timestampField(CUSTOM_TIMESTAMP_FIELD1).isGreaterOrEqualThan(fromShipmentDate);
    }

    if (toShipmentDate != null) {
      query.where().customField().timestampField(CUSTOM_TIMESTAMP_FIELD1).isLowerOrEqualThan(toShipmentDate);
    }

    return query;
  }

  @Override
  public void resetValues() {
    fromShipmentDate = null;
    toShipmentDate = null;
  }
  
  @Override
  public void validate() {
    if (fromShipmentDate != null && toShipmentDate != null && (fromShipmentDate.compareTo(toShipmentDate) > 0)) {
      FacesContext.getCurrentInstance().validationFailed();
      FacesContext.getCurrentInstance().addMessage(
          "advanced-filter-error-messages",
          new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co(
              "/ch.ivy.addon.portalkit.ui.jsf/common/dateFromBiggerThanTo"), null));
    }
  }

  public Date getFromShipmentDate() {
    return fromShipmentDate;
  }

  public void setFromShipmentDate(Date fromShipmentDate) {
    this.fromShipmentDate = fromShipmentDate;
  }

  public Date getToShipmentDate() {
    return toShipmentDate;
  }

  public void setToShipmentDate(Date toShipmentDate) {
    this.toShipmentDate = toShipmentDate;
  }

}
