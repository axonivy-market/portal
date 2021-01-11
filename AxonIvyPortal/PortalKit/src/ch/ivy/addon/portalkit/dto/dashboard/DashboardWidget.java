package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivyteam.ivy.environment.Ivy;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @Type(value = TaskDashboardWidget.class, name = "task"), 
  @Type(value = ProcessDashboardWidget.class, name = "process"),
  @Type(value = ActionDashboardWidget.class, name = "action")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class DashboardWidget implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;

  protected String id;
  protected String name;
  protected int axisX;
  protected int axisY;
  protected int width;
  protected int height;
  protected boolean autoPosition;

  public DashboardWidget() {}

  public DashboardWidget(String id, String name, int axisX, int axisY, int width, int height) {
    this.id = id;
    this.name = name;
    this.axisX = axisX;
    this.axisY = axisY;
    this.width = width;
    this.height = height;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    if (StringUtils.startsWithIgnoreCase(name, DashboardConfigurationPrefix.CMS)) {
      return Ivy.cms().co(StringUtils.removeStart(name, DashboardConfigurationPrefix.CMS));
    }
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAxisX() {
    return axisX;
  }

  public void setAxisX(int axisX) {
    this.axisX = axisX;
  }

  public int getAxisY() {
    return axisY;
  }

  public void setAxisY(int axisY) {
    this.axisY = axisY;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
  
  public boolean getAutoPosition() {
    return autoPosition;
  }
  
  public void setAutoPosition(boolean autoPosition) {
    this.autoPosition = autoPosition;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DashboardWidget other = (DashboardWidget) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
