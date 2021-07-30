package portal.migration.version93.configuration;

import java.util.List;

public class ConfigurationWrapper {

  private List<AbstractConfiguration> configurations;

  public ConfigurationWrapper() {}


  public ConfigurationWrapper(List<AbstractConfiguration> configurations) {
    this.configurations = configurations;
  }

  public List<AbstractConfiguration> getConfigurations() {
    return configurations;
  }

  public void setConfigurations(List<AbstractConfiguration> configurations) {
    this.configurations = configurations;
  }

}
