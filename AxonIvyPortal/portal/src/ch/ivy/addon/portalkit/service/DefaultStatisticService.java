package ch.ivy.addon.portalkit.service;

import com.axonivy.portal.bo.Statistic;

import ch.ivy.addon.portalkit.enums.PortalVariable;

public class DefaultStatisticService extends JsonConfigurationService<Statistic> {

  private static DefaultStatisticService instance;

  private DefaultStatisticService() {}

  public static DefaultStatisticService getInstance() {
    if (instance == null) {
      instance = new DefaultStatisticService();
    }
    return instance;
  }

  @Override
  public Class<Statistic> getType() {
    return Statistic.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.STATISTIC.key;
  }
}
