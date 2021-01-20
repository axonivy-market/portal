package com.axonivy.portal.developerexamples.exporter;

import java.util.List;

import com.axonivy.portal.developerexamples.component.customize.cases.CustomizedCaseLazyDataModel;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.exporter.CaseExporter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class CustomizedCaseExporter extends CaseExporter {

  public CustomizedCaseExporter(List<String> columnsVisibility) {
    super(columnsVisibility);
  }

  @Override
  public String getColumnName(String column) {
    String columnName = getSpecialColumnName(column);
    return columnName != null ? columnName
        : Ivy.cms().co("/DefaultColumns/caseList/" + column);
  }

  @Override
  public Object getColumnValue(String column, ICase caseItem) {
    switch (column) {
      case CustomizedCaseLazyDataModel.CUSTOM_VARCHAR_FIELD1:
        return caseItem.customFields().stringField(CustomFields.CUSTOM_VARCHAR_FIELD1).getOrNull();
      case CustomizedCaseLazyDataModel.CUSTOM_TIMESTAMP_FIELD1:
        return caseItem.customFields().timestampField(CustomFields.CUSTOM_TIMESTAMP_FIELD1).getOrNull();
      default:
        return getCommonColumnValue(column, caseItem);
    }
  }
}
