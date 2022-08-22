package portalmigration.version94.service;

import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;

public class TaskFilterService extends AbstractFilterService<TaskFilterData> {
  @Override
  public Class<TaskFilterData> getType() {
    return TaskFilterData.class;
  }
}
