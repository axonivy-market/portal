package ch.ivy.addon.portalkit.ivydata.dto;

import ch.ivyteam.ivy.workflow.IStartElement;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcessStartDTO extends AbstractResultDTO {

  /**
   * Use {@link #startElement} instead
   */
  @Deprecated(forRemoval = true, since = "10.0.4")
  private IWebStartable startableProcessStart;

  private IStartElement startElement;

  public IvyProcessStartDTO() {}

  /**
   * Use {@link #IvyProcessStartDTO(IStartElement)} instead
   * @param startableProcessStart
   */
  @Deprecated(forRemoval = true, since = "10.0.4")
  public IvyProcessStartDTO(IWebStartable startableProcessStart) {
    super();
    this.startableProcessStart = startableProcessStart;
  }

  public IvyProcessStartDTO(IStartElement startElement) {
    super();
    this.startElement = startElement;
  }

  /**
   * Use {@link #getStartElement()} instead
   * @return IWebStartable
   */
  @Deprecated(forRemoval = true, since = "10.0.4")
  public IWebStartable getStartableProcessStart() {
    return startableProcessStart;
  }

  /**
   * Use {@link #setStartElement(IStartElement)} instead
   * @param startableProcessStart
   */
  @Deprecated(forRemoval = true, since = "10.0.4")
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
