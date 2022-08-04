package com.axonivy.portal.developerexamples.exporter;

import static com.axonivy.portal.developerexamples.component.customize.cases.CustomizedCaseLazyDataModel.CUSTOM_CUSTOMER_NAME;
import static com.axonivy.portal.developerexamples.component.customize.cases.CustomizedCaseLazyDataModel.CUSTOM_SHIPMENT_DATE;

import java.util.List;

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
      case CUSTOM_CUSTOMER_NAME:
        return caseItem.customFields().stringField(CUSTOM_CUSTOMER_NAME).getOrNull();
      case CUSTOM_SHIPMENT_DATE:
        return caseItem.customFields().timestampField(CUSTOM_SHIPMENT_DATE).getOrNull();
      default:
        return getCommonColumnValue(column, caseItem);
    }
  }
}
