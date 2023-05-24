package ch.ivy.addon.portalkit.exporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
public class CaseDashboardExporter extends Exporter{

  public static final String CUSTOM_FIELD_FORMAT = "%s__%s__%s";

  /**
   * Constructor
   * 
   * @param columnsVisibility list of columns to export
   */
  public CaseDashboardExporter(List<String> columnsVisibility) {
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

  /**
   * Gets case column value.
   * 
   * @param column case field like "NAME", "CREATOR", "CREATION_TIME"..
   * @param caseItem target case
   * @return case column value
   */
  public Object getColumnValue(String column, ICase caseItem) {
    DashboardStandardCaseColumn sortField = DashboardStandardCaseColumn.findBy(column);
    if (sortField == null) {
      return getCustomColumnValue(column, caseItem);
    }
    return getCommonColumnValue(sortField, caseItem);
  }

  /**
   * Get common column value
   * @param column
   * @param caseItem
   * @return case column value
   */
  protected Object getCommonColumnValue(DashboardStandardCaseColumn sortField, ICase caseItem) {
    switch (sortField) {
      case NAME:
        return StringUtils.isEmpty(caseItem.names().current()) ? Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/caseNameNotAvailable") : caseItem.names().current();
      case ID:
        return String.valueOf(caseItem.getId());
      case DESCRIPTION:
        return caseItem.descriptions().current();
      case STATE:
        return CaseUtils.convertToUserFriendlyCaseState(caseItem.getState());
      case CREATOR:
        if (caseItem.getCreatorUserName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(caseItem.getCreatorUser(), caseItem.getCreatorUserName());
      case CREATED:
        return caseItem.getStartTimestamp();
      case FINISHED:
        return caseItem.getEndTimestamp();
      case OWNER:
        if (caseItem.getOwnerName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(caseItem.getOwner(), caseItem.getOwnerName());
      case CATEGORY:
        return caseItem.getCategory().getPath();
      case APPLICATION:
        return caseItem.getApplication().getName();
      default:
        return "";
    }
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

    switch (fieldFormat) {
      case NUMBER: 
        return caseItem.customFields().numberField(fieldName).getOrNull();
      case STRING:
        return caseItem.customFields().stringField(fieldName).getOrNull();
      case TEXT:
        return caseItem.customFields().textField(fieldName).getOrNull();
      case TIMESTAMP:
        return caseItem.customFields().timestampField(fieldName).getOrNull();
      default:
        return "";
    }
  }

  /**
   * Generate data for export
   */
  @Override
  protected <T> List<List<Object>> generateData(List<T> cases) {
    List<List<Object>> rows = new ArrayList<>();
    for (T t : cases) {
      if (t instanceof ICase) {
        List<Object> row = new ArrayList<>();
        for (String column : columnsVisibility) {
          row.add(getColumnValue(column, (ICase)t));
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
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/exportedCasesFileName",
        Arrays.asList(fileNameSuffix, extension));
  }

  private String[] getCustomColumnParts(String customColumn) {
    return customColumn.split(CUSTOM_FIELD_SEPARATOR);
  }
}
