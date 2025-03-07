package com.axonivy.portal.dto.dashboard.filter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldFinishedDate;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomTimestamp;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldExpiryDate;
import com.axonivy.portal.util.filter.field.task.custom.TaskFilterFieldCustomTimestamp;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.service.exception.PortalException;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardFilter implements Serializable {
  private static final long serialVersionUID = -2098346832426240167L;

  @JsonIgnore
  public static final String CATEGORY = "category";
  @JsonIgnore
  public static final String STATE = "state";
  @JsonIgnore
  public static final String APPLICATION = "application";
  @JsonIgnore
  public static final String ID = "id";
  @JsonIgnore
  public static final String CREATED_DATE = "startTimestamp";
  @JsonIgnore
  public static final String DATE_FORMAT = "MM/dd/yyyy HH:mm";
  @JsonIgnore
  public static final String DATE_FORMAT_WITHOUT_TIME = "MM/dd/yyyy";
  @JsonIgnore
  public static final String DMY_DATE_FORMAT = "dd.MM.yyyy HH:mm";
  @JsonIgnore
  public static final String DMY_DATE_FORMAT_WITHOUT_TIME = "dd.MM.yyyy";
  @JsonIgnore
  private static final String DEFAULT = "default";

  private String field;

  private String from;

  private String to;

  private List<String> values;

  private String value;

  private FilterOperator operator;

  private Long periods;

  private FilterPeriodType periodType;

  @JsonProperty("type")
  private DashboardColumnType filterType;

  @JsonIgnore
  private FilterField filterField;

  @JsonIgnore
  private Date fromDate;

  @JsonIgnore
  private Date toDate;

  @JsonIgnore
  private FilterFormat filterFormat;

  @JsonIgnore
  private boolean isTemp;

  @JsonIgnore
  private String numberPattern;

  @JsonIgnore
  public boolean isCustomDateField() {
    return filterField instanceof CaseFilterFieldCustomTimestamp
        || filterField instanceof TaskFilterCaseFieldCustomTimestamp
        || filterField instanceof TaskFilterFieldCustomTimestamp;
  }

  @JsonIgnore
  public boolean isFinishedDateField() {
    return filterField instanceof CaseFilterFieldFinishedDate;
  }

  @JsonIgnore
  public boolean isCreatedDateField() {
    return CREATED_DATE.equals(this.field);
  }

  @JsonIgnore
  public boolean isExpiryDateField() {
    return filterField instanceof TaskFilterFieldExpiryDate;
  }

  @JsonIgnore
  public boolean isNumberField() {
    return (this.filterFormat == FilterFormat.NUMBER) && !isDefaultField();
  }

  @JsonIgnore
  public boolean isCreator() {
    return this.field == DashboardStandardCaseColumn.CREATOR.getField();
  }

  @JsonIgnore
  public boolean isResponsible() {
    return this.field == DashboardStandardTaskColumn.RESPONSIBLE.getField();
  }

  @JsonIgnore
  public boolean isCategory() {
    return CATEGORY.equals(this.field);
  }

  @JsonIgnore 
  public boolean isApplication() {
    return APPLICATION.equals(this.field);
  }

  @JsonIgnore
  public boolean isState() {
    return STATE.equals(this.field);
  }

  @JsonIgnore
  public boolean isPriority() {
    return this.field == DashboardStandardTaskColumn.PRIORITY.getField();
  }

  @JsonIgnore
  public boolean isId() {
    return ID.equals(this.field);
  }

  @JsonIgnore
  public boolean isTextField() {
    return (this.filterFormat == FilterFormat.TEXT || this.filterFormat == FilterFormat.STRING) && !isCategory()
        && !isId() && !isApplication() && !isDefaultField();
  }

  @JsonIgnore
  public boolean isDefaultField() {
    return StringUtils.isBlank(this.field);
  }

  @JsonIgnore
  public FilterFormat getFilterFormat() {
    return filterFormat;
  }

  @JsonIgnore
  public void setFilterFormat(FilterFormat filterFormat) {
    this.filterFormat = filterFormat;
  }

  public FilterOperator getOperator() {
    return operator;
  }

  public void setOperator(FilterOperator operator) {
    this.operator = operator;
  }

  public FilterPeriodType getPeriodType() {
    return periodType;
  }

  public void setPeriodType(FilterPeriodType periodType) {
    this.periodType = periodType;
  }

  public Long getPeriods() {
    return periods;
  }

  public void setPeriods(Long periods) {
    this.periods = periods;
  }

  @JsonIgnore
  public FilterField getFilterField() {
    return filterField;
  }

  @JsonIgnore
  public void setFilterField(FilterField filterField) {
    this.filterField = filterField;
  }

  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  @JsonIgnore
  public boolean isTemp() {
    return isTemp;
  }

  @JsonIgnore
  public void setTemp(boolean isTemp) {
    this.isTemp = isTemp;
  }

  @JsonIgnore
  public Date getFromDate() {
    if (fromDate == null && StringUtils.isNotBlank(from)) {
      try {
        fromDate = DateUtils.parseDate(from, DATE_FORMAT, DMY_DATE_FORMAT, DATE_FORMAT_WITHOUT_TIME, DMY_DATE_FORMAT_WITHOUT_TIME);
      } catch (ParseException e) {
        throw new PortalException("Cannot parse date " + from, e);
      }
    }
    return fromDate;
  }

  @JsonIgnore
  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  @JsonIgnore
  public Date getToDate() {
    if (toDate == null && StringUtils.isNotBlank(to)) {
      try {
        toDate = DateUtils.parseDate(to, DATE_FORMAT, DMY_DATE_FORMAT, DATE_FORMAT_WITHOUT_TIME, DMY_DATE_FORMAT_WITHOUT_TIME);
      } catch (ParseException e) {
        throw new PortalException("Cannot parse date " + to, e);
      }
    }
    return toDate;
  }

  @JsonIgnore
  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  @JsonIgnore
  public List<SecurityMemberDTO> getCreators() {
    return this.values.stream().map(this::findUser)
        .filter(Objects::nonNull).collect(Collectors.toList());
  }

  @JsonIgnore
  public List<SecurityMemberDTO> getResponsibles() {
    return this.values.stream().map(this::findSecurityMember)
        .filter(Objects::nonNull).collect(Collectors.toList());
  }

  private SecurityMemberDTO findSecurityMember(String memberName) {
    return ServiceUtilities.findSecurityMemberByName(memberName);
  }

  private SecurityMemberDTO findUser(String memberName) {
    return ServiceUtilities.findSecurityMemberByName("#".concat(memberName));
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @JsonIgnore
  public String getNumberPattern() {
    return numberPattern;
  }

  @JsonIgnore
  public void setNumberPattern(String numberPattern) {
    this.numberPattern = numberPattern;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public DashboardColumnType getFilterType() {
    return filterType;
  }

  public void setFilterType(DashboardColumnType filterType) {
    this.filterType = filterType;
  }
}
