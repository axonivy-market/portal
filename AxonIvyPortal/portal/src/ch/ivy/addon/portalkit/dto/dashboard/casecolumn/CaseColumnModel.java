package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

public class CaseColumnModel extends ColumnModel {

  private static final long serialVersionUID = 7358059302396225605L;

  public Object display(ICase caze) {
    ICustomFields customFields = caze.customFields();
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

  public static CaseColumnModel constructColumn(DashboardColumnType fieldType, String field) {
    CaseColumnModel column = new CaseColumnModel();
    if (fieldType == DashboardColumnType.STANDARD) {
      if (DashboardStandardCaseColumn.ID.getField().equalsIgnoreCase(field)) {
        column = new IdColumnModel();
      } else if (DashboardStandardCaseColumn.NAME.getField().equalsIgnoreCase(field)) {
        column = new NameColumnModel();
      } else if (DashboardStandardCaseColumn.DESCRIPTION.getField().equalsIgnoreCase(field)) {
        column = new DescriptionColumnModel();
      } else if (DashboardStandardCaseColumn.CREATOR.getField().equalsIgnoreCase(field)) {
        column = new CreatorColumnModel();
      } else if (DashboardStandardCaseColumn.STATE.getField().equalsIgnoreCase(field)) {
        column = new StateColumnModel();
      } else if (DashboardStandardCaseColumn.CREATED.getField().equalsIgnoreCase(field)) {
        column = new CreatedDateColumnModel();
      } else if (DashboardStandardCaseColumn.FINISHED.getField().equalsIgnoreCase(field)) {
        column = new FinishedDateColumnModel();
      } else if (DashboardStandardCaseColumn.OWNER.getField().equalsIgnoreCase(field)) {
        column = new OwnerColumnModel();
      } else if (DashboardStandardCaseColumn.APPLICATION.getField().equalsIgnoreCase(field)) {
        column = new ApplicationColumnModel();
      } else if (DashboardStandardCaseColumn.ACTIONS.getField().equalsIgnoreCase(field)) {
        column = new ActionsColumnModel();
      }
    }
    return column;
  }

}
