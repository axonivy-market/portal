package ch.ivy.addon.portalkit.exporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Export Portal case list to Excel
 *
 */
public class CaseExporter extends Exporter{
  
  /**
   * Constructor
   * @param columnsVisibility list of columns to export
   */
  public CaseExporter(List<String> columnsVisibility) {
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
        : Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + column);
  }

  /**
   * Gets column name that is differ from UI.
   * 
   * @param column 
   * @return column name based on session language
   */
  protected String getSpecialColumnName(String column) {
    if (CaseSortField.NAME.name().equals(column)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseName");
    } else if (CaseLazyDataModel.DESCRIPTION.equals(column)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/description");
    }
    return null;
  }

  /**
   * Gets case column value.
   * 
   * @param column case field like "NAME", "CREATOR", "CREATION_TIME"..
   * @param caseItem target case
   * @return case column value
   */
  public Object getColumnValue(String column, ICase caseItem) {
    return getCommonColumnValue(column, caseItem);
  }

  /**
   * Get common column value
   * @param column
   * @param caseItem
   * @return case column value
   */
  protected Object getCommonColumnValue(String column, ICase caseItem) {
    if (StringUtils.equals(column, CaseLazyDataModel.DESCRIPTION)) {
      return caseItem.descriptions().current();
    }

    CaseSortField sortField = CaseSortField.valueOf(column);
    switch (sortField) {
      case NAME:
        return StringUtils.isEmpty(caseItem.names().current()) ? Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/caseNameNotAvailable") : caseItem.names().current();
      case ID:
        return String.valueOf(caseItem.getId());
      case CREATOR:
        if (caseItem.getCreatorUserName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(caseItem.getCreatorUser(), caseItem.getCreatorUserName());
      case OWNER:
        if (caseItem.getOwnerName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(caseItem.getOwner(), caseItem.getOwnerName());
      case CREATION_TIME:
        return caseItem.getStartTimestamp();
      case FINISHED_TIME:
        return caseItem.getEndTimestamp();
      case STATE:
        return caseItem.getState().toString();
      case CATEGORY:
        return caseItem.getCategory().getPath();
      case APPLICATION:
        return caseItem.getApplication().getName();
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
}
