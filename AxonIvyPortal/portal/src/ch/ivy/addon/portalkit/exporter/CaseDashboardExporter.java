package ch.ivy.addon.portalkit.exporter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Export Portal dashboard's case widget to Excel
 *
 */
public class CaseDashboardExporter extends DashboardWidgetExporter{

  /**
   * Constructor
   * @param columnsVisibility list of columns to export
   * @param widgetName
   */
  public CaseDashboardExporter(List<String> columnsVisibility, String widgetName) {
    super(columnsVisibility, widgetName, "/ch.ivy.addon.portalkit.ui.jsf/dashboard/export/exportedCasesFileName");
  }

  /**
   * <p>
   * Gets column label.
   * </p>
   * 
   * @param column column name
   * @return column column label
   */
  @Override
  public String getColumnName(String column) {
    DashboardStandardCaseColumn columnField = DashboardStandardCaseColumn.findBy(column);
    if (columnField == null) {
      return getCustomColumnName(column);
    }

    switch(columnField) {
      case NAME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/caseName");
      case DESCRIPTION:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/description");
      case CREATED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATION_TIME");
      case FINISHED:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/FINISHED_TIME");
      default:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + columnField.name());
    }
  }

  
  /**
   * Get header text from the custom column
   * Custom column format: fieldType__fieldName__header
   * @param column
   * @return custom column name
   */
  private String getCustomColumnName(String column) {
    String[] columnParts = getCustomColumnParts(column);
    if (columnParts.length != 3) {
      return "";
    }
    return columnParts[2];
  }

  /**
   * Get common column value
   * @param sortField
   * @param caseItem
   * @return case column value
   */
  private Object getCommonColumnValue(DashboardStandardCaseColumn sortField, ICase caseItem) {
    return switch (sortField) {
      case NAME -> StringUtils.isEmpty(caseItem.names().current()) ? Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/caseNameNotAvailable") : caseItem.names().current();
      case ID -> String.valueOf(caseItem.getId());
      case DESCRIPTION -> caseItem.descriptions().current();
      case STATE -> CaseUtils.convertToUserFriendlyCaseState(caseItem.getBusinessState());
      case CREATOR -> {
        if (caseItem.getCreatorUserName() == null) {
          yield Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        yield SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(caseItem.getCreatorUser(), caseItem.getCreatorUserName());
      }
      case CREATED -> caseItem.getStartTimestamp();
      case FINISHED -> caseItem.getEndTimestamp();
      case OWNER ->{
        if (caseItem.getOwnerName() == null) {
          yield Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        yield SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(caseItem.getOwner(), caseItem.getOwnerName());
      }
      case CATEGORY -> caseItem.getCategory().getPath();
      case APPLICATION -> caseItem.getApplication().getName();
      default -> "";
    };
  }

  /**
   * Get custom column value
   * Custom column format: fieldType__fieldName__header
   * 
   * @param column
   * @param caseItem
   * @return case column value
   */
  private Object getCustomColumnValue(String column, ICase caseItem) {
    String[] columnParts = getCustomColumnParts(column);
    if (columnParts.length != 3) {
      return "";
    }

    String fieldName = columnParts[1];
    DashboardColumnFormat fieldFormat = DashboardColumnFormat.valueOf(columnParts[0]);
    if (fieldFormat == null) {
      return "";
    }

    return switch (fieldFormat) {
      case NUMBER -> caseItem.customFields().numberField(fieldName).getOrNull();
      case STRING -> caseItem.customFields().stringField(fieldName).getOrNull();
      case TEXT -> caseItem.customFields().textField(fieldName).getOrNull();
      case TIMESTAMP -> caseItem.customFields().timestampField(fieldName).getOrNull();
      default -> "";
    };
  }

  /**
   * Gets case column value.
   * 
   * @param column case field like "NAME", "CREATOR", "CREATION_TIME"..
   * @param item target case
   * @return case column value
   */
  @Override
  public <T> Object getColumnValue(String column, T item) {
    DashboardStandardCaseColumn sortField = DashboardStandardCaseColumn.findBy(column);
    if (sortField == null) {
      return getCustomColumnValue(column, (ICase) item);
    }
    return getCommonColumnValue(sortField, (ICase) item);
  }
}
