package com.axonivy.portal.developerexamples.exporter;

import static com.axonivy.portal.developerexamples.component.customize.CustomizedTaskLazyDataModel.CUSTOM_CUSTOMER_NAME;
import static com.axonivy.portal.developerexamples.component.customize.CustomizedTaskLazyDataModel.CUSTOM_SHIPMENT_DATE;

import java.util.List;

import ch.ivy.addon.portalkit.exporter.TaskExporter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class CustomizedTaskExporter extends TaskExporter {

  public CustomizedTaskExporter(List<String> columnsVisibility) {
    super(columnsVisibility);
  }

  @Override
  public String getColumnName(String column) {
    String columnName = getSpecialColumnName(column);
    return columnName != null ? columnName : Ivy.cms().co("/DefaultColumns/".concat(column));

  }

  @Override
  public Object getColumnValue(String column, ITask task) {
    switch (column) {
      case CUSTOM_CUSTOMER_NAME:
        return task.customFields().stringField(CUSTOM_CUSTOMER_NAME).getOrNull();
      case CUSTOM_SHIPMENT_DATE:
        return task.customFields().timestampField(CUSTOM_SHIPMENT_DATE).getOrNull();
      default:
        return getCommonColumnValue(column, task);
    }
  }
}
