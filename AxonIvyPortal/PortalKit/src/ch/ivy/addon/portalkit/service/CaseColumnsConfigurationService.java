package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivy.addon.portalkit.enums.JsonVariable;

public class CaseColumnsConfigurationService extends JsonConfigurationService<CaseColumnsConfiguration> {

  private static CaseColumnsConfigurationService instance;

  private CaseColumnsConfigurationService() {}

  public static CaseColumnsConfigurationService getInstance() {
    if (instance == null) {
      instance = new CaseColumnsConfigurationService();
    }
    return instance;
  }


  /**
   * @deprecated use {@link CaseColumnsConfigurationService#getConfiguration(Long)} instead
   * @param applicationId
   * @param userId
   * @param processModelId
   * @return CaseColumnsConfiguration
   */
  @SuppressWarnings("unused")
  @Deprecated(forRemoval = true, since = "9.3")
  public CaseColumnsConfiguration getConfiguration(Long applicationId, Long userId, Long processModelId) {
    return getConfiguration(processModelId);
  }

  public CaseColumnsConfiguration getConfiguration(Long processModelId) {
    return getPrivateConfig().stream().filter(config -> config.getProcessModelId().equals(processModelId)).findFirst()
        .orElse(null);
  }

  @Override
  public Class<CaseColumnsConfiguration> getType() {
    return CaseColumnsConfiguration.class;
  }

  @Override
  public String getConfigKey() {
    return JsonVariable.CASE_COLUMN.key;
  }
}
