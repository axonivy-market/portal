package com.axonivy.portal.developerexamples.util;

import java.util.Date;
import java.util.List;

import com.axonivy.portal.developerexamples.component.customize.cases.CustomizedCaseLazyDataModel;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.util.CaseExporter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class CaseExporterOverride extends CaseExporter {

  public CaseExporterOverride(List<String> columnsVisibility) {
    super(columnsVisibility);
  }

  @Override
  protected String getCustomColumnName(String column) {
    switch (column) {
      case CustomizedCaseLazyDataModel.CUSTOM_VARCHAR_FIELD1:
        return Ivy.cms().co("/DefaultColumns/caseList/customVarCharField1");
      case CustomizedCaseLazyDataModel.CUSTOM_TIMESTAMP_FIELD1:
        return Ivy.cms().co("/DefaultColumns/caseList/customTimestampField1");
      default:
        return "";
    }
  }

  @Override
  protected String getCustomColumnValue(String column, ICase caseItem) {
    switch (column) {
      case CustomizedCaseLazyDataModel.CUSTOM_VARCHAR_FIELD1:
        return caseItem.customFields().stringField(CustomFields.CUSTOM_VARCHAR_FIELD1).getOrNull();
      case CustomizedCaseLazyDataModel.CUSTOM_TIMESTAMP_FIELD1:
        Date customTimeStamp = caseItem.customFields().timestampField(CustomFields.CUSTOM_TIMESTAMP_FIELD1).getOrNull();
        return customTimeStamp != null ? formatDate(customTimeStamp) : "";
      default:
        return "";
    }
  }
}
