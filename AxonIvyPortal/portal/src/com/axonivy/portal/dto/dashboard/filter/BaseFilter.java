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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.service.exception.PortalException;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseFilter implements Serializable{

  private static final long serialVersionUID = -5821933108266790192L;

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
  public static final String DEFAULT = "default";

  private String field;

  private String from;

  private String to;

  @JsonIgnore
  private Date fromDate;

  @JsonIgnore
  private Date toDate;

  private List<String> values;

  private String value;

  private FilterOperator operator;

  @JsonProperty("type")
  private DashboardColumnType filterType;

  @JsonIgnore
  private FilterFormat filterFormat;

  private Long periods;

  private FilterPeriodType periodType;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
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

  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public FilterOperator getOperator() {
    return operator;
  }

  public void setOperator(FilterOperator operator) {
    this.operator = operator;
  }

  public DashboardColumnType getFilterType() {
    return filterType;
  }

  public void setFilterType(DashboardColumnType filterType) {
    this.filterType = filterType;
  }

  @JsonIgnore
  public FilterFormat getFilterFormat() {
    return filterFormat;
  }

  @JsonIgnore
  public void setFilterFormat(FilterFormat filterFormat) {
    this.filterFormat = filterFormat;
  }

  @JsonIgnore
  public Date getFromDate() {
    if (fromDate == null && StringUtils.isNotBlank(getFrom())) {
      try {
        fromDate = DateUtils.parseDate(getFrom(), DATE_FORMAT, DMY_DATE_FORMAT, DATE_FORMAT_WITHOUT_TIME, DMY_DATE_FORMAT_WITHOUT_TIME);
      } catch (ParseException e) {
        throw new PortalException("Cannot parse date " + getFrom(), e);
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
    if (toDate == null && StringUtils.isNotBlank(getTo())) {
      try {
        toDate = DateUtils.parseDate(getTo(), DATE_FORMAT, DMY_DATE_FORMAT, DATE_FORMAT_WITHOUT_TIME, DMY_DATE_FORMAT_WITHOUT_TIME);
      } catch (ParseException e) {
        throw new PortalException("Cannot parse date " + getTo(), e);
      }
    }
    return toDate;
  }

  @JsonIgnore
  public List<SecurityMemberDTO> getResponsibles() {
    return getValues().stream().map(this::findSecurityMember)
        .filter(Objects::nonNull).collect(Collectors.toList());
  }

  private SecurityMemberDTO findSecurityMember(String memberName) {
    return ServiceUtilities.findSecurityMemberByName(memberName);
  }

  @JsonIgnore
  public void setToDate(Date toDate) {
    this.toDate = toDate;
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
  public boolean isCreatedDateField() {
    return DashboardStandardTaskColumn.CREATED.getField().equals(getField());
  }
  
  @JsonIgnore
  public boolean isCategory() {
    return DashboardStandardTaskColumn.CATEGORY.getField().equals(getField());
  }

  @JsonIgnore
  public boolean isState() {
    return DashboardStandardTaskColumn.STATE.getField().equals(getField());
  }
  
  @JsonIgnore
  public boolean isPriority() {
    return DashboardStandardTaskColumn.PRIORITY.getField().equals(getField());
  }
  
  @JsonIgnore
  public boolean isResponsible() {
    return DashboardStandardTaskColumn.RESPONSIBLE.getField().equals(getField());
  }
  
  @JsonIgnore
  public boolean isFinishedDateField() {
    return DashboardStandardTaskColumn.COMPLETED.getField().equals(getField());
  }
  
  @JsonIgnore
  public List<SecurityMemberDTO> getCreators() {
    return getValues().stream().map(this::findUser)
        .filter(Objects::nonNull).collect(Collectors.toList());
  }

  private SecurityMemberDTO findUser(String memberName) {
    return ServiceUtilities.findSecurityMemberByName(memberName);
  }


}
