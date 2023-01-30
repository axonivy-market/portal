package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivyteam.ivy.workflow.start.IWebStartable;

/**
 * @deprecated instead use {@link com.axonivy.portal.components.dto.IvyProcessResultDTO}
 */
@Deprecated(forRemoval = true, since = "10.0.4")
public class IvyProcessResultDTO extends com.axonivy.portal.components.dto.IvyProcessResultDTO {

  public IvyProcessResultDTO() {
    super();
  }

  public IvyProcessResultDTO(List<IWebStartable> processes) {
    super(processes);
  }

}
