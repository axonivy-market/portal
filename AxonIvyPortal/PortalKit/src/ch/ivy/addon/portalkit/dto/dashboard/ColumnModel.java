package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

public class ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  protected String header;
  protected String property;
  protected String width;
  protected String styleClass;
  protected String propertyStyleClass;
  protected boolean toggleable = true;
  protected boolean sortable = true;
  protected DashboardColumnType type = DashboardColumnType.STRING;
  
  @JsonIgnore
  protected boolean visible = true;
  
  public Object display(ITask task) {
    ICustomFields customFields = task.customFields();
    if (StringUtils.startsWithIgnoreCase(property, DashboardConfigurationPrefix.CUSTOM_FIELD_NUMBER)) {
      return customFields.numberField(StringUtils.removeStartIgnoreCase(property, DashboardConfigurationPrefix.CUSTOM_FIELD_NUMBER)).getOrNull();
    } else if (StringUtils.startsWithIgnoreCase(property, DashboardConfigurationPrefix.CUSTOM_FIELD_TIMESTAMP)) {
      return customFields.timestampField(StringUtils.removeStartIgnoreCase(property, DashboardConfigurationPrefix.CUSTOM_FIELD_TIMESTAMP)).getOrNull();
    } else if (StringUtils.startsWithIgnoreCase(property, DashboardConfigurationPrefix.CUSTOM_FIELD_TEXT)) {
      return customFields.textField(StringUtils.removeStartIgnoreCase(property, DashboardConfigurationPrefix.CUSTOM_FIELD_TEXT)).getOrNull();
    } else {
      return customFields.stringField(StringUtils.removeStartIgnoreCase(property, DashboardConfigurationPrefix.CUSTOM_FIELD_STRING)).getOrNull();
    }
  }
  
  protected String cms(String path) {
    return Ivy.cms().co(path);
  }

  public String getHeader() {
    if (StringUtils.startsWithIgnoreCase(header, DashboardConfigurationPrefix.CMS)) {
      return Ivy.cms().co(StringUtils.removeStart(header, DashboardConfigurationPrefix.CMS));
    }
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public void setStyleClass(String styleClass) {
    this.styleClass = styleClass;
  }
  
  public String getPropertyStyleClass() {
    return propertyStyleClass;
  }
  
  public void setPropertyStyleClass(String propertyStyleClass) {
    this.propertyStyleClass = propertyStyleClass;
  }
  
  public boolean getVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public boolean getToggleable() {
    return toggleable;
  }

  public void setToggleable(boolean toggleable) {
    this.toggleable = toggleable;
  }
  
  public boolean getSortable() {
    return sortable;
  }
  
  public void setSortable(boolean sortable) {
    this.sortable = sortable;
  }

  public DashboardColumnType getType() {
    return type;
  }
  
  public void setType(DashboardColumnType type) {
    this.type = type;
  }
}
