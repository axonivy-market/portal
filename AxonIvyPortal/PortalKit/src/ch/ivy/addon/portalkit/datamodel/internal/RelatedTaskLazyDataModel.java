package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.TaskSortField;

public class RelatedTaskLazyDataModel extends TaskLazyDataModel {

  private static final long serialVersionUID = 1L;

  public RelatedTaskLazyDataModel() {
    super();
  }
  
  public RelatedTaskLazyDataModel(String taskWidgetComponentId) {
    super(taskWidgetComponentId);
  }
  
  @Override
  public List<String> getDefaultColumns() {
    return Arrays.asList(TaskSortField.PRIORITY.name(), TaskSortField.NAME.name(), TaskSortField.ACTIVATOR.name(), TaskSortField.ID.name(),
        TaskSortField.CREATION_TIME.name(), TaskSortField.EXPIRY_TIME.name(), TaskSortField.STATE.name());
  }
}
