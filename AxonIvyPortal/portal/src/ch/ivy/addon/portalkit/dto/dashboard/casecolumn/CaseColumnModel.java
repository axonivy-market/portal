package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.cm.exec.ContentResolver;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

public class CaseColumnModel extends ColumnModel {

  private static final String CMS_PATH = "CmsPath";
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
      return displayStringFieldContent(customFields, caze);
    }
  }
  
  private boolean containCmsPathAttr(Iterable<String> iterable) {
    for (String str : iterable) {
        if (str != null && str.equals(CMS_PATH)) {
            return true;
        }
    }
    return false;
}
  
  private String displayStringFieldContent(ICustomFields customFields, ICase caze) {
    if (containCmsPathAttr(customFields.stringField(field).meta().attributeNames())) {
      String cmsPath = customFields.stringField(field).meta().attribute(CMS_PATH);
      if (cmsPath == null) {
        return StringUtils.EMPTY;
      }
      cmsPath = cmsPath + "/" + customFields.stringField(field).getOrNull();
      ContentManagement cms = ContentManagement.of(caze.getProcessModelVersion());
      ContentResolver content = cms.content(cmsPath);
      return content.get();
    }
    return customFields.stringField(field).getOrNull();
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
      } else if (DashboardStandardCaseColumn.CREATOR.getField().equalsIgnoreCase(field)
          && GlobalSettingService.getInstance().isHideCaseCreator()) {
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

  public Boolean caseWithCustomAction(ICase caze) {
    ICustomFieldMeta metaData = caze.customFields().all().stream().filter(f -> f.name().equals(field)).map(f -> f.meta()).findFirst().orElse(null);
    return Objects.nonNull(metaData) ? Boolean.valueOf(metaData.attribute(DashboardWidgetUtils.IS_CUSTOM_ACTION_ATTRIBUTE)) : false;
  }

  public String getCustomProcessPath(ICase caze) {
    String processPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(caze.customFields().stringField(field).getOrNull());
    return processPath + "?uuid=" + caze.uuid();
  }
  
  /**
   * Only allow quick search for custom String and Text fields
   * 
   */
  @Override
  public boolean canQuickSearch() {
    CustomFieldType type = switch (this.type) {
    case CUSTOM -> type = DashboardWidgetUtils.findCaseCustomFieldType(field);
    default -> null;
    };
    return type == CustomFieldType.STRING || type == CustomFieldType.TEXT;
  }
}
