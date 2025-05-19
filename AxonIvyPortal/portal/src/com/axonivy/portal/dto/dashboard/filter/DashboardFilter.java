package com.axonivy.portal.dto.dashboard.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomTimestamp;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldExpiryDate;
import com.axonivy.portal.util.filter.field.task.custom.TaskFilterFieldCustomTimestamp;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardFilter extends BaseFilter implements Serializable {
  private static final long serialVersionUID = -2098346832426240167L;
  @JsonIgnore
  public static final String APPLICATION = "application";
  
  @JsonIgnore
  private FilterField filterField;

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
    return FINISHED_DATE.equals(this.field);
  }

  @JsonIgnore
  public boolean isExpiryDateField() {
    return filterField instanceof TaskFilterFieldExpiryDate;
  }

  @JsonIgnore
  public boolean isNumberField() {
    return (getFilterFormat() == FilterFormat.NUMBER) && !isDefaultField();
  }

  @JsonIgnore
  public boolean isCreator() {
    return getField() == DashboardStandardCaseColumn.CREATOR.getField();
  }

  @JsonIgnore 
  public boolean isApplication() {
    return APPLICATION.equals(getField());
  }

  @JsonIgnore
  public boolean isId() {
    return ID.equals(getField());
  }

  @JsonIgnore
  public boolean isTextField() {
    return (getFilterFormat() == FilterFormat.TEXT || getFilterFormat() == FilterFormat.STRING) && !isCategory()
        && !isId() && !isApplication() && !isDefaultField();
  }

  @JsonIgnore
  public boolean isDefaultField() {
    return StringUtils.isBlank(getField());
  }

  @JsonIgnore
  public FilterField getFilterField() {
    return filterField;
  }

  @JsonIgnore
  public void setFilterField(FilterField filterField) {
    this.filterField = filterField;
  }

  @JsonIgnore
  public boolean isTemp() {
    return isTemp;
  }

  @JsonIgnore
  public void setTemp(boolean isTemp) {
    this.isTemp = isTemp;
  }

  @Override
  @JsonIgnore
  public List<SecurityMemberDTO> getCreators() {
    return getValues().stream().map(this::findUser)
        .filter(Objects::nonNull).collect(Collectors.toList());
  }

  private SecurityMemberDTO findUser(String memberName) {
    return ServiceUtilities.findSecurityMemberByName("#".concat(memberName));
  }

  @JsonIgnore
  public String getNumberPattern() {
    return numberPattern;
  }

  @JsonIgnore
  public void setNumberPattern(String numberPattern) {
    this.numberPattern = numberPattern;
  }
  
  public boolean canWorkOn() {
    return StatisticConstants.CAN_WORK_ON.equals(getField());
  }
}
