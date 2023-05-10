package portalmigration.version112.dto;

import java.io.Serializable;

import portalmigration.version112.enums.DashboardCustomParamType;

public class CustomDashboardWidgetParam implements Serializable {

  private static final long serialVersionUID = 8083097846247724566L;

  private String name;
  private DashboardCustomParamType type;
  private String value;

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
}
