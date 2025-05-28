package ch.ivy.addon.portalkit.exporter;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

/**
 * Export Portal dashboard's task widget to Excel file
 *
 */
public class TaskDashboardExporter extends DashboardWidgetExporter{
  private static final String TASK_BUSINESS_STATE_CMS_PATH = "/ch.ivy.addon.portalkit.ui.jsf/taskBusinessState/";

  /**
   * Constructor
   * 
   * @param visibleColumns list of columns to export
   * @param widgetName
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

    String url = switch (columnField) {
      case NAME -> "/ch.ivy.addon.portalkit.ui.jsf/common/taskName";
      case DESCRIPTION -> "/ch.ivy.addon.portalkit.ui.jsf/common/description";
      case ID -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ID";
      case PRIORITY -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/PRIORITY";
      case RESPONSIBLE -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR";
      case CREATED -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CREATION_TIME";
      case COMPLETED -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/COMPLETED";
      case EXPIRY -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/EXPIRY_TIME";
      case STATE -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE";
      case CATEGORY -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CATEGORY";
      case APPLICATION -> "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/APPLICATION";
      default -> "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + columnField.name();
    };

    return Ivy.cms().co(url);

  }
  
  /**
   * Get header text from the custom column
   * Custom column format: fieldFormat__fieldName__header__fieldType
   * 
   * @param column
   * @return customColumnName
   */
  private String getCustomColumnName(String column) {
    String[] columnParts = getCustomColumnParts(column);
    if (columnParts.length != 4) {
      return "";
    }
    
    return columnParts[2];
  }
  
  private Object getCommonColumnValue(DashboardStandardTaskColumn sortField, ITask taskItem) {
    return switch(sortField) {
      case PRIORITY -> Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + taskItem.getPriority().name());
      case NAME -> StringUtils.isEmpty(taskItem.names().current())
          ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable")
          : taskItem.names().current();
      case RESPONSIBLE -> CollectionUtils.isEmpty(taskItem.responsibles().all())
          ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable")
          : taskItem.responsibles().all()
            .stream()
            .map(item -> SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(item.get(), item.displayName()))
            .collect(Collectors.joining(", "));
      case ID -> String.valueOf(taskItem.getId());
      case CREATED -> taskItem.getStartTimestamp();
      case COMPLETED -> taskItem.getEndTimestamp();
      case EXPIRY -> taskItem.getExpiryTimestamp();
      case STATE -> getTaskBusinessState(taskItem.getBusinessState());
      case CATEGORY -> taskItem.getCategory().getPath();
      case DESCRIPTION -> taskItem.getDescription();
      case APPLICATION -> taskItem.getApplication().getName();
      default -> "";
    };
  }
  
  private Object getCustomColumnValue(String column, ITask taskItem) {
    String[] columnParts = getCustomColumnParts(column);
    if (columnParts.length != 4) {
      return "columnParts not equal to 4";
    }
    
    String fieldName = columnParts[1];
    String type = columnParts[3];
    DashboardColumnFormat fieldFormat = DashboardColumnFormat.valueOf(columnParts[0]);
    if(fieldFormat == null) {
      return "";
    }
    ICustomFields customFields;
    if (type.equals(DashboardColumnFormat.CUSTOM_CASE.name())) {
      customFields = taskItem.getCase().customFields();
    } else if (type.equals(DashboardColumnFormat.CUSTOM_BUSINESS_CASE.name())) {
      customFields = taskItem.getCase().getBusinessCase().customFields();
    } else {
      customFields = taskItem.customFields();
    }
    switch (fieldFormat) {
      case NUMBER: 
        return customFields.numberField(fieldName).getOrNull();
      case STRING:
        return customFields.stringField(fieldName).getOrNull();
      case TEXT:
        return customFields.textField(fieldName).getOrNull();
      case TIMESTAMP:
        return customFields.timestampField(fieldName).getOrNull();
      default:
        return "";
    }
  }

  /**
   * Get task column value.
   * 
   * @param column task field like "START", "PRIORITY", "RESPONSIBLE"...
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

  private String getTaskBusinessState(TaskBusinessState state) {
    return Ivy.cms().co(TASK_BUSINESS_STATE_CMS_PATH + state + "_UPPERCASE");
  }
}
