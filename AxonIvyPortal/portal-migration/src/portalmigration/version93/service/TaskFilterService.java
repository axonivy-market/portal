package portalmigration.version93.service;


import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;
import portalmigration.enums.PortalVariable;

public class TaskFilterService extends AbstractFilterService<TaskFilterData> {
  @Override
  public Class<TaskFilterData> getType() {
    return TaskFilterData.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.TASK_FILTER.key;
  }
}
