package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;

public class CaseColumnsConfigurationService extends BusinessDataService<CaseColumnsConfiguration> {
  
  private static final String APP_ID = "applicationId";
  private static CaseColumnsConfigurationService instance;

  public CaseColumnsConfigurationService() {}

  public static CaseColumnsConfigurationService getInstance() {
    if (instance == null) {
      instance = new CaseColumnsConfigurationService();
    }
    return instance;
  }

  @Override
  public Class<CaseColumnsConfiguration> getType() {
    return CaseColumnsConfiguration.class;
  }

  public CaseColumnsConfiguration getConfiguration(Long applicationId, Long userId, Long processModelId) {
    Filter<CaseColumnsConfiguration> query =
        repo().search(getType())
              .numberField(APP_ID).isEqualTo(applicationId)
              .and().numberField("userId").isEqualTo(userId)
              .and().numberField("processModelId").isEqualTo(processModelId);
    return query.limit(1).execute().getFirst();
  }

  /**
   * Get total count of Case configuration by application id
   * @param applicationId
   * @return totalCount
   */
  public long getTotalCaseConfigCountByAppId(Long applicationId) {
    try {
      Filter<CaseColumnsConfiguration> query =
          repo().search(getType()).numberField(APP_ID).isEqualTo(applicationId);
      return query.execute().totalCount();
    } catch (Exception e) {
      Ivy.log().error(e);
      return 0;
    }
  }

  /**
   * Get list of Case configuration by application id
   * @param applicationId
   * @param firstIndex is first entity
   * @param offset is size of return list
   * @return list of Case configuration
   */
  public List<CaseColumnsConfiguration> getCaseConfigurationWithOffset(Long applicationId, int firstIndex, int offset) {
    try {
      Filter<CaseColumnsConfiguration> query =
          repo().search(getType()).numberField(APP_ID).isEqualTo(applicationId);
      Result<CaseColumnsConfiguration> queryResult = query.limit(firstIndex, offset).execute();
      return queryResult.getAll();
    } catch (Exception e) {
      Ivy.log().error(e);
      return new ArrayList<>();
    }
  }
}
