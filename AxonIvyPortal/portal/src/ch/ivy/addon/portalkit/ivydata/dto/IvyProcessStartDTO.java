package ch.ivy.addon.portalkit.ivydata.dto;

import ch.ivyteam.ivy.workflow.IStartElement;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcessStartDTO extends AbstractResultDTO {

  private IWebStartable startableProcessStart;

  private IStartElement startElement;

  public IvyProcessStartDTO() {}

  public IvyProcessStartDTO(IWebStartable startableProcessStart) {
    super();
    this.startableProcessStart = startableProcessStart;
  }

  public IvyProcessStartDTO(IStartElement startElement) {
    super();
    this.startElement = startElement;
  }

  public IWebStartable getStartableProcessStart() {
    return startableProcessStart;
  }

  public void setStartableProcessStart(IWebStartable startableProcessStart) {
    this.startableProcessStart = startableProcessStart;
  }

  public IStartElement getStartElement() {
    return startElement;
  }

  public void setStartElement(IStartElement startElement) {
    this.startElement = startElement;
  }
}
