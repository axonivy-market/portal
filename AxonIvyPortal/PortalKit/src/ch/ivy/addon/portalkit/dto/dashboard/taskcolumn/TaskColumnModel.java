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
    TaskColumnModel column = new TaskColumnModel();
    if (fieldType == DashboardColumnType.STANDARD) {
      if (DashboardStandardTaskColumn.START.getField().equalsIgnoreCase(field)) {
        column = new StartColumnModel();
      } else if (DashboardStandardTaskColumn.PRIORITY.getField().equalsIgnoreCase(field)) {
        column = new PriorityColumnModel();
      } else if (DashboardStandardTaskColumn.ID.getField().equalsIgnoreCase(field)) {
        column = new IdColumnModel();
      } else if (DashboardStandardTaskColumn.NAME.getField().equalsIgnoreCase(field)) {
        column = new NameColumnModel();
      } else if (DashboardStandardTaskColumn.DESCRIPTION.getField().equalsIgnoreCase(field)) {
        column = new DescriptionColumnModel();
      } else if (DashboardStandardTaskColumn.RESPONSIBLE.getField().equalsIgnoreCase(field)) {
        column = new ResponsibleColumnModel();
      } else if (DashboardStandardTaskColumn.STATE.getField().equalsIgnoreCase(field)) {
        column = new StateColumnModel();
      } else if (DashboardStandardTaskColumn.CREATED.getField().equalsIgnoreCase(field)) {
        column = new CreatedDateColumnModel();
      } else if (DashboardStandardTaskColumn.EXPIRY.getField().equalsIgnoreCase(field)) {
        column = new ExpiryDateColumnModel();
      } else if (DashboardStandardTaskColumn.CATEGORY.getField().equalsIgnoreCase(field)) {
        column = new CategoryColumnModel();
      }  else if (DashboardStandardTaskColumn.APPLICATION.getField().equalsIgnoreCase(field)) {
          column = new ApplicationColumnModel();
      } else if (DashboardStandardTaskColumn.ACTIONS.getField().equalsIgnoreCase(field)) {
        column = new ActionsColumnModel();
      }     
    }
    return column;
  }
}
