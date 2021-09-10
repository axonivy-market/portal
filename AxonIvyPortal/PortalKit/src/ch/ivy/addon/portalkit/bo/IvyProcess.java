package ch.ivy.addon.portalkit.bo;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcess implements Process {

  private IWebStartable process;
  private String defaultImageSrc;
  private String defaultImageCms;

  public IvyProcess(IWebStartable process) {
    this.process = process;
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
  public String getCategory() {
    return process.getCategory().getPath();
  }

  @Override
  public String getImageUrl() {
    return StringUtils.defaultIfBlank(defaultImageCms, DefaultImage.PROCESSMODELING.getPath());
  }

  public void setDefaultImageSrc(String defaultImageSrc) {
    this.defaultImageSrc = defaultImageSrc;
  }

  @Override
  public String getDefaultImageSrc() {
    return this.defaultImageSrc;
  }

  public void setDefaultImageCms(String defaultImageCms) {
    this.defaultImageCms = defaultImageCms;
  }

  public String getCustomFieldImageProcess() {
    return this.process.customFields().value("processImage");
  }
}
