package ch.ivy.addon.portalkit.bo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.util.ExternalLinkUtils;

import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivyteam.ivy.workflow.category.Category;

/*
 * Used for merging external link and ivy process into a process list
 */
public class ExternalLinkProcessItem implements Process {

  public static final String DEFAULT_ICON = "si-hyperlink-3";
  private ExternalLink externalLink;
  
  public ExternalLinkProcessItem(ExternalLink externalLink) {
    this.externalLink = externalLink;
    convertBase64ImageToFile();
  }
  
  private void convertBase64ImageToFile() {
    if (StringUtils.isNotBlank(externalLink.getImageType()) && StringUtils.isNotBlank(externalLink.getImageContent())) {
      String imageLocation = ExternalLinkUtils.imageBase64ToApplicationCMSFile(externalLink.getImageContent(), externalLink.getImageType());
      ExternalLinkUtils.removeImage(externalLink.getImageLocation(), externalLink.getImageType());
      externalLink.setImageLocation(imageLocation);
      externalLink.setImageContent(null);
      ExternalLinkService.getInstance().save(externalLink);
    }
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
    String imageLocation = this.externalLink.getImageLocation();
    return ExternalLinkUtils.isValidImageUrl(imageLocation, this.externalLink.getImageType()) ? imageLocation : getContentImageUrl(DefaultImage.ARROWRIGHT.getPath());
  }

  public String getImageType() {
    return this.externalLink.getImageType();
  }

  @Override
  public List<String> getPermissions() {
    return externalLink.getPermissions();
  }

  @Override
  public String getApplication() {
    return null;
  }

  @Override
  public String getSortIndex() {
    return null;
  }

  @Override
  public String getPortalProcessInformation() {
    return null;
  }

  // Use the same image in Light mode
  public String getDefaultImageDarkUrl() {
    String imageLocation = this.externalLink.getImageLocation();
    return ExternalLinkUtils.isValidImageUrl(imageLocation, this.externalLink.getImageType()) ? imageLocation : getContentImageUrl(DefaultImage.ARROWRIGHT.getPath());
  }
}
