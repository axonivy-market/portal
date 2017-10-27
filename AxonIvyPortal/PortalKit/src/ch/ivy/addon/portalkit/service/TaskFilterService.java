package ch.ivy.addon.portalkit.service;


import static ch.ivy.addon.portalkit.enums.FilterType.ALL_USERS;
import static ch.ivy.addon.portalkit.enums.FilterType.ONLY_ME;

import java.util.List;

import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskFilterService extends AbstractExpressService<TaskFilterData> {

  private static final String FILTER_NAME = "filterName";
  private static final String USER_ID = "userId";
  private static final String FILTER_TYPE = "type";

  public List<TaskFilterData> findByUserName() {
    Filter<TaskFilterData> publicFilterQuery =
        repo().search(getType()).textField(FILTER_TYPE).isEqualToIgnoringCase(ALL_USERS.name());
    Filter<TaskFilterData> privateFilterQuery =
        repo().search(getType()).numberField(USER_ID).isEqualTo(Ivy.session().getSessionUser().getId()).and()
            .textField(FILTER_TYPE).isEqualToIgnoringCase(ONLY_ME.name());
    Filter<TaskFilterData> combinedQuery =
        repo().search(getType()).filter(publicFilterQuery).or().filter(privateFilterQuery);
    return combinedQuery.orderBy().textField(FILTER_NAME).ascending().execute().getAll();
  }

  @Override
  public Class<TaskFilterData> getType() {
    return TaskFilterData.class;
  }
}
