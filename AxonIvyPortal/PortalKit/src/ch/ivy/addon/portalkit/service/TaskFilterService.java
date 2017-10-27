package ch.ivy.addon.portalkit.service;


import java.util.Collections;
import java.util.List;

import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskFilterService extends AbstractExpressService<TaskFilterData> {

  public List<TaskFilterData> findByUserName() {
    if (repo().search(getType()).execute().totalCount() > 0) {
      return repo().search(getType()).textField("userName").isEqualToIgnoringCase(Ivy.session().getSessionUserName())
          .orderBy().textField("filterName").ascending().execute().getAll();
    }
    return Collections.emptyList();
  }

  @Override
  public Class<TaskFilterData> getType() {
    return TaskFilterData.class;
  }
}
