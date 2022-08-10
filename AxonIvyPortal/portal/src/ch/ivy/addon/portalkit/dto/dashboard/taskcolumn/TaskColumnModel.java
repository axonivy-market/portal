package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

public class TaskColumnModel extends ColumnModel {

  private static final long serialVersionUID = -6363817685343055544L;

  public Object display(ITask task) {
    ICustomFields customFields = task.customFields();
    if (isNumber()) {
      return customFields.numberField(field).getOrNull();
    } else if (isDate()) {
      return customFields.timestampField(field).getOrNull();
    } else if (isText()) {
      return customFields.textField(field).getOrNull();
    } else {
      return customFields.stringField(field).getOrNull();
    }
  }

  public static TaskColumnModel constructColumn(DashboardColumnType fieldType, String field) {
    if (fieldType == DashboardColumnType.STANDARD) {
      if (equals(DashboardStandardTaskColumn.START, field)) {
        return new StartColumnModel();
      } else if (equals(DashboardStandardTaskColumn.PRIORITY, field)) {
        return new PriorityColumnModel();
      } else if (equals(DashboardStandardTaskColumn.ID, field)) {
        return new IdColumnModel();
      } else if (equals(DashboardStandardTaskColumn.NAME, field)) {
        return new NameColumnModel();
      } else if (equals(DashboardStandardTaskColumn.DESCRIPTION, field)) {
        return new DescriptionColumnModel();
      } else if (equals(DashboardStandardTaskColumn.RESPONSIBLE, field)) {
        return new ResponsibleColumnModel();
      } else if (equals(DashboardStandardTaskColumn.STATE, field)) {
        return new StateColumnModel();
      } else if (equals(DashboardStandardTaskColumn.CREATED, field)) {
        return new CreatedDateColumnModel();
      } else if (equals(DashboardStandardTaskColumn.EXPIRY, field)) {
        return new ExpiryDateColumnModel();
      } else if (equals(DashboardStandardTaskColumn.CATEGORY, field)) {
        return new CategoryColumnModel();
      }  else if (equals(DashboardStandardTaskColumn.APPLICATION, field)) {
        return new ApplicationColumnModel();
      } else if (equals(DashboardStandardTaskColumn.ACTIONS, field)) {
        return new ActionsColumnModel();
      }
    }
    return new TaskColumnModel();
  }

  private static boolean equals(DashboardStandardTaskColumn column, String field) {
    return column.getField().equalsIgnoreCase(field);
  }
}
