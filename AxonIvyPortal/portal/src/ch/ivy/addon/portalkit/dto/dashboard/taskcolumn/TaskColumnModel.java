package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.util.Optional;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

public class TaskColumnModel extends ColumnModel {

  private static final long serialVersionUID = -6363817685343055544L;

  public Object display(ITask task) {
    if (type == DashboardColumnType.CUSTOM_CASE) {
      ICustomFields customFields = task.getCase().customFields();
      return getCustomFieldValue(customFields);
    } else if (type == DashboardColumnType.CUSTOM_BUSINESS_CASE) {
      ICustomFields customFields = task.getCase().getBusinessCase().customFields();
      return getCustomFieldValue(customFields);
    } else {
      ICustomFields customFields = task.customFields();
      return getCustomFieldValue(customFields);
    }
  }

  private Object getCustomFieldValue(ICustomFields customFields) {
    if (isNumber()) {
      return customFields.numberField(field).getOrNull();
    } else if (isDate()) {
      return customFields.timestampField(field).getOrNull();
    } else if (isText()) {
      return customFields.textField(field).getOrNull();
    } else {
      return displayStringFieldContent(customFields);
    }
  }

  public static TaskColumnModel constructColumn(DashboardColumnType fieldType, String field) {
    if (fieldType != DashboardColumnType.STANDARD || field == null) {
      return new TaskColumnModel();
    }

    return Optional.ofNullable(DashboardStandardTaskColumn.findBy(field)).map(columnType -> switch (columnType) {
      case START -> new StartColumnModel();
      case PRIORITY -> new PriorityColumnModel();
      case ID -> new IdColumnModel();
      case NAME -> new NameColumnModel();
      case DESCRIPTION -> new DescriptionColumnModel();
      case RESPONSIBLE -> new ResponsibleColumnModel();
      case STATE -> new StateColumnModel();
      case CREATED -> new CreatedDateColumnModel();
      case COMPLETED -> new CompletedDateColumnModel();
      case EXPIRY -> new ExpiryDateColumnModel();
      case CATEGORY -> new CategoryColumnModel();
      case APPLICATION -> new ApplicationColumnModel();
      case ACTIONS -> new ActionsColumnModel();
    }).orElse(new TaskColumnModel());
  }

  /**
   * Only allow quick search for custom String and Text fields
   * 
   */
  @Override
  public boolean canQuickSearch() {
    CustomFieldType type = switch (this.type) {
      case CUSTOM -> type = DashboardWidgetUtils.findTaskCustomFieldType(field);
      case CUSTOM_CASE -> type = DashboardWidgetUtils.findCaseCustomFieldType(field);
      case CUSTOM_BUSINESS_CASE -> type = DashboardWidgetUtils.findCaseCustomFieldType(field);
      default -> null;
    };
    return type == CustomFieldType.STRING || type == CustomFieldType.TEXT;
  }
}
