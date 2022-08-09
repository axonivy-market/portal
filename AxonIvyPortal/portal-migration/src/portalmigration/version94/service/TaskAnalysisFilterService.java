package portalmigration.version94.service;

import ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData;

public class TaskAnalysisFilterService extends AbstractFilterService<TaskAnalysisFilterData> {
  @Override
  public Class<TaskAnalysisFilterData> getType() {
    return TaskAnalysisFilterData.class;
  }
}
