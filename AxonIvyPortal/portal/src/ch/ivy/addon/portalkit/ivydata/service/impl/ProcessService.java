package ch.ivy.addon.portalkit.ivydata.service.impl;

/**
 * @deprecated instead use {@link com.axonivy.portal.components.service.impl.ProcessService}
 */
@Deprecated(forRemoval = true, since = "10.0.4")
public class ProcessService extends com.axonivy.portal.components.service.impl.ProcessService {

  public static ProcessService newInstance() {
    return new ProcessService();
  }

  public ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO findProcesses() {
    return new ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO(super.findProcesses().getProcesses());
  }

}
