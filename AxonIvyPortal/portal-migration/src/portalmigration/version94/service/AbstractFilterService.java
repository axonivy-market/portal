package portalmigration.version94.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.filter.AbstractFilterData;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;
import portalmigration.service.BusinessDataService;

public abstract class AbstractFilterService<T extends AbstractFilterData<?>> extends BusinessDataService<T> {
  private static final String FILTER_GROUP_ID = "filterGroupId";
  private final int MAX_QUERY_SIZE = 10000;

  public List<T> getFiltersByGroupId(Long filterGroupId) {
    try {
      Filter<T> filterQuery = repo().search(getType()).numberField(FILTER_GROUP_ID).isEqualTo(filterGroupId);
      var queryResult = filterQuery.execute();
      var totalCount = queryResult.totalCount();
      List<T> filters = new ArrayList<>();
      if (totalCount > MAX_QUERY_SIZE) {
        filters = getAllFiltersWhenTotalSizeIsMoreMaxQuerySize(filterQuery, totalCount);
      } else {
        filters.addAll(filterQuery.limit(0, Math.toIntExact(totalCount)).execute().getAll());
      }
      return filters;
    } catch (Exception e) {
      Ivy.log().error(e);
      return new ArrayList<>();
    }
  }

  private List<T> getAllFiltersWhenTotalSizeIsMoreMaxQuerySize(Filter<T> query, long totalCount) {
    List<T> result = new ArrayList<>();
    Result<T> queryResult;
    int index = 0, querySize = 0, totalSize = Math.toIntExact(totalCount);
    do {
      querySize = (index + MAX_QUERY_SIZE) > totalSize ? (totalSize - index) : MAX_QUERY_SIZE;
      queryResult = query.limit(index, querySize).execute();
      result.addAll(queryResult.getAll());
      index = index + MAX_QUERY_SIZE;
    } while (index < totalSize);

    return result;
  }
}
