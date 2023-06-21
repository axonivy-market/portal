package ch.ivy.addon.portalkit.exporter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Export Portal dashboard's task widget to Excel file
 *
 */
public class TaskDashboardExporter extends DashboardWidgetExporter{

  /**
   * Constructor
   * 
   * @param visibleColumns list of columns to export
   * 
   */
  public TaskDashboardExporter(List<String> visibleColumns, String widgetName) {
    super(visibleColumns, widgetName, "/ch.ivy.addon.portalkit.ui.jsf/dashboard/export/exportedTasksFileName");
  }

  @Override
  public String getColumnName(String column) {
    DashboardStandardTaskColumn columnField = DashboardStandardTaskColumn.findBy(column);
    if(null == columnField) {
      return getCustomColumnName(column);
    }
    
    switch (columnField) {
      case PRIORITY:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/PRIORITY");
      case NAME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/taskName");
      case RESPONSIBLE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR");
      case ID:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ID");
      case CREATED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CREATION_TIME");
      case EXPIRY:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/EXPIRY_TIME");
      case STATE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE");
      case CATEGORY:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CATEGORY");
      case DESCRIPTION:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/description");
      default:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + columnField.name());
    }
  }
  
  /**
   * Get header text from the custom column
   * Custom column format: fieldType__fieldName__header
   * 
   * @param column
   * @return
   */
  private String getCustomColumnName(String column) {
    String[] columnParts = getCustomColumnParts(column);
    if (columnParts.length != 3) {
      return "";
    }
    
    return columnParts[2];
  }
  
  private Object getCommonColumnValue(DashboardStandardTaskColumn sortField, ITask taskItem) {
    switch(sortField) {
    case PRIORITY:
      return taskItem.getPriority().name();
    case NAME:
      return StringUtils.isEmpty(taskItem.names().current()) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable" ): taskItem.names().current();
    case RESPONSIBLE:
      if(taskItem.getActivator() == null) {
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
      }
      return taskItem.getActivator().getDisplayName();
    case ID:
      return String.valueOf(taskItem.getId());
    case CREATED:
      return taskItem.getStartTimestamp();
    case EXPIRY:
      return taskItem.getExpiryTimestamp();
    case STATE:
      return taskItem.getState();
    case CATEGORY:
      return taskItem.getCategory().getPath();
    case DESCRIPTION:
      return taskItem.getDescription();
    default:
      return "";
    }
  }
  
  private Object getCustomColumnValue(String column, ITask taskItem) {
    String[] columnParts = getCustomColumnParts(column);
    if(columnParts.length != 3) {
      return "columnParts not equal to 3";
    }
    
    String fieldName = columnParts[1];
    DashboardColumnFormat fieldFormat = DashboardColumnFormat.valueOf(columnParts[0]);
    if(fieldFormat == null) {
      return "";
    }
    
    switch (fieldFormat) {
      case NUMBER: 
        return taskItem.customFields().numberField(fieldName).getOrNull();
      case STRING:
        return taskItem.customFields().stringField(fieldName).getOrNull();
      case TEXT:
        return taskItem.customFields().textField(fieldName).getOrNull();
      case TIMESTAMP:
        return taskItem.customFields().timestampField(fieldName).getOrNull();
      default:
        return "";
    }
  }

  /**
   * Get task column value.
   * 
   * @param column task field like "START", "PRIORITY", "RESPONSIBLE"...
   * @param taskItem target task 
   * @return task column value 
   */
  @Override
  public <T> Object getColumnValue(String column, T item) {
    DashboardStandardTaskColumn sortField = DashboardStandardTaskColumn.findBy(column);
    if(sortField == null) {
      return getCustomColumnValue(column, (ITask) item);
    }
    return getCommonColumnValue(sortField, (ITask) item);
  }

}
