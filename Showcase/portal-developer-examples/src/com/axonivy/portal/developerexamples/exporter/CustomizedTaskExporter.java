package com.axonivy.portal.developerexamples.exporter;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_TIMESTAMP_FIELD1;
import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD5;

import java.util.List;

import com.axonivy.portal.developerexamples.component.customize.CustomizedTaskLazyDataModel;

import ch.ivy.addon.portalkit.exporter.TaskExporter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class CustomizedTaskExporter extends TaskExporter {

  public CustomizedTaskExporter(List<String> columnsVisibility) {
    super(columnsVisibility);
  }

  @Override
  protected String getColumnName(String column) {
    String columnName = getSpecialColumnName(column);
    return columnName != null ? columnName : Ivy.cms().co("/DefaultColumns/".concat(column));

  }

  @Override
  protected Object getColumnValue(String column, ITask task) {
    switch (column) {
      case CustomizedTaskLazyDataModel.CUSTOM_VAR_CHAR_FIELD5:
        return task.customFields().stringField(CUSTOM_VARCHAR_FIELD5).getOrNull();
      case CustomizedTaskLazyDataModel.CUSTOM_TIMESTAMP_FIELD12:
        return task.customFields().timestampField(CUSTOM_TIMESTAMP_FIELD1).getOrNull();
      default:
        return getCommonColumnValue(column, task);
    }
  }
}
