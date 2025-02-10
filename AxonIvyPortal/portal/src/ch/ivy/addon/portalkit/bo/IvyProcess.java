package ch.ivy.addon.portalkit.bo;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcess implements Process {

  private static final String DEFAULT_IMAGE_VALUE_LIGHT_MODE = "PROCESSMODELING.svg";
  private static final String DEFAULT_IMAGE_VALUE_DARK_MODE = "PROCESSMODELINGDARK.svg";
  private IWebStartable process;
  private String application;
  private String imageUrl;
  private String index;
  private String portalProcessInformation;

  public IvyProcess(IWebStartable process) {
    this.application = process.pmv().getApplication().getName();
    this.process = process;
    this.imageUrl = collectProcessImage(process);
    this.index = process.customFields().value(CustomFields.SORT_INDEX);
    this.portalProcessInformation = process.customFields().value(CustomFields.PROCESS_INFORMATION);
  }

  @Override
  public String getStartLink() {
    return process.getLink().getRelativeEncoded();
  }

  @Override
  public String getDescription() {
    return process.getDescription();
  }

  @Override
  public String getName() {
    return process.getDisplayName();
  }

  @Override
  public IWebStartable getProcess() {
    return process;
  }

  @Override
  public ProcessType getType() {
    return ProcessType.IVY_PROCESS;
  }

  @Override
  public String getTypeName() {
    return ProcessType.IVY_PROCESS.name();
  }

  @Override
  public String getId() {
    return StringUtils.EMPTY;
  }

  @Override
  public String getIcon() {
    String cssIcon = this.process.customFields().value("cssIcon");
    return StringUtils.defaultIfBlank(cssIcon, Process.super.getIcon());
  }

  @Override
  public Category getCategory() {
    return process.getCategory();
  }

  @Override
  public String getImageUrl() {
    // Change default value image
    if (imageUrl.contains(DEFAULT_IMAGE_VALUE_DARK_MODE)) {
      imageUrl = getContentImageUrl(DefaultImage.PROCESSMODELING.getPath());
    }
    return imageUrl;
  }
  
  public String getDefaultImageDarkUrl() {
    // Change default value image
    if (imageUrl.contains(DEFAULT_IMAGE_VALUE_LIGHT_MODE)) {
      imageUrl = getContentImageUrl(DefaultImage.PROCESSMODELINGDARK.getPath());
    }
    return imageUrl;
  }

  @Override
  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  @Override
  public String getSortIndex() {
    return index;
  }

  @Override
  public String getPortalProcessInformation() {
    return portalProcessInformation;
  }
}
