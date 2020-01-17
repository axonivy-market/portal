package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;

public class CaseColumnsConfigurationService extends BusinessDataService<CaseColumnsConfiguration> {

  @Override
  public Class<CaseColumnsConfiguration> getType() {
    return CaseColumnsConfiguration.class;
  }

  public CaseColumnsConfiguration getConfiguration(Long applicationId, Long userId, Long processModelId) {
    Filter<CaseColumnsConfiguration> query =
        repo().search(getType())
              .numberField("applicationId").isEqualTo(applicationId)
              .and().numberField("userId").isEqualTo(userId)
              .and().numberField("processModelId").isEqualTo(processModelId);
    return query.limit(1).execute().getFirst();
  }

  public long getTotalCount(Long applicationId) {
    try {
      Filter<CaseColumnsConfiguration> query =
          repo().search(getType()).numberField("applicationId").isEqualTo(applicationId);
      return query.execute().totalCount();
    } catch (Exception e) {
      Ivy.log().error(e);
      return 0;
    }
  }

  public List<CaseColumnsConfiguration> getAllConfiguration(Long applicationId, int limitFrom, int limitSize) {
    try {
      Filter<CaseColumnsConfiguration> query =
          repo().search(getType()).numberField("applicationId").isEqualTo(applicationId);
      Result<CaseColumnsConfiguration> queryResult = query.limit(limitFrom, limitSize).execute();
      return queryResult.getAll();
    } catch (Exception e) {
      Ivy.log().error(e);
      return new ArrayList<>();
    }
  }
}
