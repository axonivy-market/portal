package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.Date;

import com.axonivy.portal.component.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardCustomParamType;

public class CustomDashboardWidgetParam implements Serializable {

  private static final long serialVersionUID = 8083097846247724566L;

  private String name;
  private DashboardCustomParamType type;
  private String value;
  @JsonIgnore
  private Date valueDate;
  @JsonIgnore
  private UserDTO valueUser;
  @JsonIgnore
  private Boolean valueBoolean;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DashboardCustomParamType getType() {
    return type;
  }

  public void setType(DashboardCustomParamType type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Date getValueDate() {
    return valueDate;
  }

  public void setValueDate(Date valueDate) {
    this.valueDate = valueDate;
  }

  public UserDTO getValueUser() {
    return valueUser;
  }

  public void setValueUser(UserDTO valueUser) {
    this.valueUser = valueUser;
  }

  public Boolean getValueBoolean() {
    return valueBoolean;
  }

  public void setValueBoolean(Boolean valueBoolean) {
    this.valueBoolean = valueBoolean;
  }
}
