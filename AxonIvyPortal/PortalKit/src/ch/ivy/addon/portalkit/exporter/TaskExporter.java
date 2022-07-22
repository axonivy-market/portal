package ch.ivy.addon.portalkit.exporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Export Portal task list to Excel
 *
 */
public class TaskExporter extends Exporter {
  
  public TaskExporter(List<String> columnsVisibility) {
    super(columnsVisibility);
  }

  /**
   * <p>
   * Gets column label.
   * </p>
   * <p>
   * In case you adds new columns, these columns need cms to show in excel file
   * </p>
   * <p>
   * You can either add new entry to default folder below in PortalKit or override this method to create your own
   * folder column must be the same with sortField
   * </p>
   * 
   * @param column column name
   * @return column column label
   */
  @Override
  public String getColumnName(String column) {
    String columnName = getSpecialColumnName(column);
    return columnName != null ? columnName
        : Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/".concat(column));
  }

  /**
   * Gets column name that is differ from UI.
   * 
   * @param column
   * @return column name based on session language
   */
  protected String getSpecialColumnName(String column) {
    if (TaskSortField.NAME.name().equals(column)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/taskName");
    } else if (TaskLazyDataModel.DESCRIPTION.equals(column)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/description");
    }
    return null;
  }

  /**
   * Gets task column value.
   * 
   * @param column task field like "PRIORITY", "NAME", "ACTIVATOR", "ID", "CREATION_TIME".. 
   * @param task target task
   * @return task column value
   */
  public Object getColumnValue(String column, ITask task) {
    return getCommonColumnValue(column, task);
  }

  protected Object getCommonColumnValue(String column, ITask task) {
    if (StringUtils.equals(column, TaskLazyDataModel.DESCRIPTION)) {
      return task.getDescription();
    }

    TaskSortField sortField = TaskSortField.valueOf(column);
    switch (sortField) {
      case NAME:
        return StringUtils.isEmpty(task.getName()) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable") : task.getName();
      case ID:
        return String.valueOf(task.getId());
      case ACTIVATOR:
        if (task.getActivatorName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(task.getActivator(), task.getActivatorName());
      case PRIORITY:
        return task.getPriority().toString();
      case STATE:
        return task.getState().toString();
      case CREATION_TIME:
        return task.getStartTimestamp();
      case EXPIRY_TIME:
        return task.getExpiryTimestamp();
      case COMPLETED_ON:
        return task.getEndTimestamp();
      case CATEGORY:
        return task.getCategory().getPath();
      case APPLICATION:
        return task.getApplication().getName();
      default:
        return "";
    }
  }

  /**
   * Generate data for export
   */
  @Override
  protected <T> List<List<Object>> generateData(List<T> tasks) {
    List<List<Object>> rows = new ArrayList<>();
    for (T t : tasks) {
      if (t instanceof ITask) {
        List<Object> row = new ArrayList<>();
        for (String column : columnsVisibility) {
          row.add(getColumnValue(column, (ITask)t));
        }
      rows.add(row);
      }
    }
    return rows;
  }

  /**
   * File name for export
   */
  @Override
  protected String generateFileName(Date creationDate, String extension, String suffix) {
    String fileNameSuffix = createFileNameSuffix(creationDate, suffix); 
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/exportedTasksFileName",
        Arrays.asList(fileNameSuffix, extension));
  }
}
