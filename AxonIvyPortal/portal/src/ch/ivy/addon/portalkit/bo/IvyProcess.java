package ch.ivy.addon.portalkit.bo;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class IvyProcess implements Process {

  private IWebStartable process;
  private String application;
  private String imageUrl;

  public IvyProcess(IWebStartable process) {
    this.application = process.pmv().getApplication().getName();
    this.process = process;
    this.imageUrl = collectProcessImage(process);
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
    if (StringUtils.isEmpty(imageUrl)) {
      imageUrl = getContentImageUrl(DefaultImage.PROCESSMODELING.getPath());
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
}
