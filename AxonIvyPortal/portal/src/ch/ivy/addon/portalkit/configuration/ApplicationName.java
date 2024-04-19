package ch.ivy.addon.portalkit.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApplicationName {
  private String applicationName;

  public String getApplicationName() {
    return applicationName;
  }

}
