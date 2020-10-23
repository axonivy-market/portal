package com.axonivy.portal.developerexamples.util;

import java.util.Date;
import java.util.List;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD1;
import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD5;

import com.axonivy.portal.developerexamples.component.customize.CustomizedTaskLazyDataModel;

import ch.ivy.addon.portalkit.util.TaskExporter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class TaskExporterOverride extends TaskExporter {

  public TaskExporterOverride(List<String> columnsVisibility) {
    super(columnsVisibility);
  }

  @Override
  protected String getCustomColumnName(String column) {
    switch (column) {
      case CustomizedTaskLazyDataModel.CUSTOM_VAR_CHAR_FIELD5:
        return Ivy.cms().co("/DefaultColumns/customVarCharField5");
      case CustomizedTaskLazyDataModel.CUSTOM_TIMESTAMP_FIELD12:
        return Ivy.cms().co("/DefaultColumns/customTimestampField1");
      default:
        return "";
    }
  }

  @Override
  protected String getCustomColumnValue(String column, ITask task) {
    switch (column) {
      case CustomizedTaskLazyDataModel.CUSTOM_VAR_CHAR_FIELD5:
        return task.customFields().stringField(CUSTOM_VARCHAR_FIELD5).getOrNull();
      case CustomizedTaskLazyDataModel.CUSTOM_TIMESTAMP_FIELD12:
        Date customTimeStamp = task.customFields().timestampField(CUSTOM_TIMESTAMP_FIELD1).getOrNull();
        return customTimeStamp != null ? formatDate(customTimeStamp) : "";
      default:
        return "";
    }
  }
}
