package portalmigration.version112.bo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.workflow.category.Category;
import portalmigration.version112.configuration.ExternalLink;
import portalmigration.version112.enums.DefaultImage;
import portalmigration.version112.enums.ProcessType;

/*
 * Used for merging external link and ivy process into a process list
 */
public class ExternalLinkProcessItem implements Process {

  public static final String DEFAULT_ICON = "si si-hyperlink-3";
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
    return externalLink.getDescription();
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

  @Override
  public String getIcon() {
    String icon = this.externalLink.getIcon();
    return StringUtils.isBlank(icon) ? DEFAULT_ICON : icon;
  }

  @Override
  public Category getCategory() {
    return null;
  }
  
  @Override
  public String getImageUrl() {
    return getContentImageUrl(DefaultImage.ARROWRIGHT.getPath());
  }

  @Override
  public List<String> getPermissions() {
    return externalLink.getPermissions();
  }

  @Override
  public String getApplication() {
    return null;
  }
}
