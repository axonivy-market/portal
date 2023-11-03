package com.axonivy.portal.dto.dashboard.filter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.service.exception.PortalException;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardFilter implements Serializable {
  private static final long serialVersionUID = -2098346832426240167L;

  @JsonIgnore
  public static final String DATE_FORMAT = "MM/dd/yyyy";

  private String field;

  @JsonIgnore
  private Date fromDate;

  @JsonIgnore
  private Date toDate;

  private String from;

  private String to;

  private FilterType type;

  private FilterOperator operator;

  private Long periods;

  private FilterPeriodType periodType;

  private List<String> texts;

  @JsonIgnore
  private boolean isTemp;

  @JsonIgnore
  public boolean isDate() {
    return this.type == FilterType.DATE;
  }

  @JsonIgnore
  public boolean isNumber() {
    return this.type == FilterType.NUMBER;
  }

  @JsonIgnore
  public boolean isText() {
    return this.type == FilterType.TEXT;
  }

  @JsonIgnore
  public boolean isCreator() {
    return this.type == FilterType.CREATOR;
  }

  @JsonIgnore
  public boolean isCategory() {
    return this.type == FilterType.CATEGORY;
  }

  @JsonIgnore
  public boolean isApplication() {
    return this.type == FilterType.APPLICATION;
  }

  @JsonIgnore
  public boolean isState() {
    return this.type == FilterType.STATE;
  }

  public FilterType getType() {
    return type;
  }

  public void setType(FilterType type) {
    this.type = type;
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

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public List<String> getTexts() {
    return texts;
  }

  public void setTexts(List<String> texts) {
    this.texts = texts;
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
        fromDate = DateUtils.parseDate(from, DATE_FORMAT);
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
        toDate = DateUtils.parseDate(to, DATE_FORMAT);
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
    return this.texts.stream().map(this::findSecurityMember).collect(Collectors.toList());
  }

  private SecurityMemberDTO findSecurityMember(String memberName) {
    return ServiceUtilities.findSecurityMemberByName("#".concat(memberName));
  }
}
