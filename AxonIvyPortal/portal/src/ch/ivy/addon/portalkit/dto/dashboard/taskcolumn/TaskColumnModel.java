package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

public class TaskColumnModel extends ColumnModel {

  private static final String CMS_PATH = "CmsPath";
  private static final long serialVersionUID = -6363817685343055544L;

  public Object display(ITask task) {
    if (type == DashboardColumnType.CUSTOM_CASE) {
      ICustomFields customFields = task.getCase().customFields();
      return getCustomFieldValue(customFields, task);
    } else {
      ICustomFields customFields = task.customFields();
      return getCustomFieldValue(customFields, task);
    }
  }

  private Object getCustomFieldValue(ICustomFields customFields, ITask task) {
    if (isNumber()) {
      return customFields.numberField(field).getOrNull();
    } else if (isDate()) {
      return customFields.timestampField(field).getOrNull();
    } else if (isText()) {
      return customFields.textField(field).getOrNull();
    } else {
      return displayStringFieldContent(customFields, task);
    }
  }
  
  private String displayStringFieldContent(ICustomFields customFields, ITask task) {
    String cmsPath = customFields.stringField(field).meta().attribute(CMS_PATH);
    if (cmsPath == null) {
      return customFields.stringField(field).getOrNull();
    }
    cmsPath = cmsPath + "/" + customFields.stringField(field).getOrNull();
    var cms = ContentManagement.of(task.getProcessModelVersion());
    var content = cms.content(cmsPath);
    if (content == null) {
      return customFields.stringField(field).getOrNull();
    }
    return content.get();
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

  /**
   * Only allow quick search for custom String and Text fields
   * 
   */
  @Override
  public boolean canQuickSearch() {
    CustomFieldType type = switch (this.type) {
      case CUSTOM -> type = DashboardWidgetUtils.findTaskCustomFieldType(field);
      case CUSTOM_CASE -> type = DashboardWidgetUtils.findCaseCustomFieldType(field);
      default -> null;
    };
    return type == CustomFieldType.STRING || type == CustomFieldType.TEXT;
  }
}
