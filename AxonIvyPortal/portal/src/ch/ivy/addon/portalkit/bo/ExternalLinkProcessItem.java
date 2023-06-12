package ch.ivy.addon.portalkit.bo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.util.ExternalLinkUtils;

import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.util.Pair;

/*
 * Used for merging external link and ivy process into a process list
 */
public class ExternalLinkProcessItem implements Process {

  public static final String DEFAULT_ICON = "si si-hyperlink-3";
  public static final String BASE_64 = "base64";
  private ExternalLink externalLink;
  
  public ExternalLinkProcessItem(ExternalLink externalLink) {
    this.externalLink = externalLink;
    convertBase64ImageToFile();
  }
  
  private void convertBase64ImageToFile() {
    if (StringUtils.isNotBlank(externalLink.getImageContent()) && externalLink.getImageContent().contains(BASE_64)) {
      Pair<String, String> imageInfo = ExternalLinkUtils.imageBase64ToApplicationCMSFile(externalLink.getImageContent());
      ExternalLinkUtils.removeImage(externalLink.getImageLocation(), externalLink.getImageType());
      externalLink.setImageLocation(imageInfo.getLeft());
      externalLink.setImageType(imageInfo.getRight());
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
    String imageUrl = this.externalLink.getImageLocation();
    return ExternalLinkUtils.isValidImageUrl(imageUrl, this.externalLink.getImageType()) ? imageUrl : getContentImageUrl(DefaultImage.ARROWRIGHT.getPath());
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
}
