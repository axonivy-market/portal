package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivyteam.ivy.business.data.store.search.Filter;

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
    return query.execute().getFirst();
  }

  public List<CaseColumnsConfiguration> getAllConfiguration(Long applicationId) {
    Filter<CaseColumnsConfiguration> query =
        repo().search(getType()).numberField("applicationId").isEqualTo(applicationId);
    return query.execute().getAll();
  }
}
