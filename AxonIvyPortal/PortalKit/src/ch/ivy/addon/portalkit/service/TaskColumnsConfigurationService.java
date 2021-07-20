package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivy.addon.portalkit.enums.JsonVariable;

public class TaskColumnsConfigurationService extends JsonConfigurationService<TaskColumnsConfiguration> {

  private static TaskColumnsConfigurationService instance;

  private TaskColumnsConfigurationService() {}

  public static TaskColumnsConfigurationService getInstance() {
    if (instance == null) {
      instance = new TaskColumnsConfigurationService();
    }
    return instance;
  }

  /**
   * @deprecated use {@link TaskColumnsConfigurationService#getConfiguration(Long)} instead
   * @param applicationId
   * @param userId
   * @param processModelId
   * @return TaskColumnsConfigurations
   */
  @SuppressWarnings("unused")
  @Deprecated(forRemoval = true, since = "9.3")
  public TaskColumnsConfiguration getConfiguration(Long applicationId, Long userId, Long processModelId) {
    return getConfiguration(processModelId);
  }

  public TaskColumnsConfiguration getConfiguration(Long processModelId) {
    return getPrivateConfig().stream().filter(config -> config.getProcessModelId().equals(processModelId)).findFirst()
        .orElse(null);
  }

  @Override
  public Class<TaskColumnsConfiguration> getType() {
    return TaskColumnsConfiguration.class;
  }

  @Override
  public String getConfigKey() {
    return JsonVariable.TASK_COLUMN.key;
  }
}
