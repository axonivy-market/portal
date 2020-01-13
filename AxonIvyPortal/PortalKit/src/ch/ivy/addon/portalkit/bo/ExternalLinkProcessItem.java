package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.enums.ProcessType;

/*
 * Used for merging external link and ivy process into a process list
 */
public class ExternalLinkProcessItem implements Process {

  private ExternalLink externalLink;
  
  public ExternalLinkProcessItem(ExternalLink externalLink) {
    this.externalLink = externalLink;
  }
  
  @Override
  public String getName() {
    return externalLink.getName();
  }

  @Override
  public String getStartLink() {
    return externalLink.getLink();
  }

  @Override
  public String getDescription() {
    return externalLink.getLink();
  }

  @Override
  public ExternalLink getProcess() {
    return externalLink;
  }

  @Override
  public ProcessType getType() {
    return ProcessType.EXTERNAL_LINK;
  }

  @Override
  public String getTypeName() { 
    return ProcessType.EXTERNAL_LINK.name();
  }

  @Override
  public String getId() {
    return externalLink.getId().toString();
  }

}
