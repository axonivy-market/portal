package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardProcess implements Process {
  private String id;
  @Deprecated(forRemoval = true, since = "11.2.0")
  private Long processStartId;
  private ProcessType type;
  private String name;
  private String description;
  private List<DisplayName> names;
  private String startLink;
  private String icon;
  private String imageUrl;
  private String application;
  private Category category;
  private String sortIndex;
  private String processElementId;
  private String portalProcessInformation;
  private static final String DEFAULT_IMAGE_VALUE_LIGHT_MODE = "PROCESSMODELING.svg";
  private static final String DEFAULT_IMAGE_VALUE_DARK_MODE = "PROCESSMODELINGDARK.svg";
  
  public DashboardProcess() {}

  public DashboardProcess(Process process) {
    this.id = process.getId();
    this.type = process.getType();
    this.name = process.getName();
    this.description = process.getDescription();
    this.startLink = process.getStartLink();
    this.icon = process.getIcon();
    this.category = process.getCategory();
    this.imageUrl = process.getImageUrl();
    this.application = process.getApplication();
    this.sortIndex = process.getSortIndex();
    this.portalProcessInformation = process.getPortalProcessInformation();
  }

  public DashboardProcess(IWebStartable process) {
    this.id = process.getId();
    this.type = ProcessType.IVY_PROCESS;
    this.name = process.getDisplayName();
    this.description = process.getDescription();
    this.startLink = process.getLink().getRelative();
    this.icon = process.customFields().value("cssIcon");
    this.category = process.getCategory();
    this.application = process.pmv().getApplication().getName();
    this.imageUrl = collectProcessImage(process);
    this.sortIndex = getSortIndexInCustomField(process);
    this.portalProcessInformation = getPortalProcessInformation(process);
  }

  @Override
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public ProcessType getType() {
    return type;
  }

  public void setType(ProcessType type) {
    this.type = type;
  }

  @Override
  public String getName() {
    if (CollectionUtils.isNotEmpty(this.names)) {
      return getActiveDisplayName();
    }
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<DisplayName> getNames() {
    return names;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
  }

  @Override
  public String getStartLink() {
    return startLink;
  }

  public void setStartLink(String startLink) {
    this.startLink = startLink;
  }

  @Override
  public String getIcon() {
    if (StringUtils.isBlank(icon)) {
      if (this.type == ProcessType.IVY_PROCESS) {
        return Process.DEFAULT_PROCESS_ICON;
      } else if (this.type == ProcessType.EXTERNAL_LINK) {
        return ExternalLinkProcessItem.DEFAULT_ICON;
      }
    }
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  private String getActiveDisplayName() {
    Locale currentLocale = Ivy.session().getContentLocale();
    return names.stream().filter(displayName -> displayName.getLocale().equals(currentLocale))
        .map(DisplayName::getValue).findFirst().orElse(this.name);
  }

  /**
   * @deprecated use {@link #setProcessElementId(String)}
   * @return process start id
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
  public Long getProcessStartId() {
    return processStartId;
  }

  /**
   * @deprecated use {@link #setProcessElementId(String)} 
   * @param processStartId
   */
  @Deprecated(forRemoval = true, since = "11.2.0")
  public void setProcessStartId(Long processStartId) {
    this.processStartId = processStartId;
  }

  @Override
  public Object getProcess() {
    return this;
  }

  @Override
  public String getTypeName() {
    return this.type.name();
  }

  @Override
  public String getImageUrl() {
    // Change default image value
    if (imageUrl.contains(DEFAULT_IMAGE_VALUE_DARK_MODE)) {
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

  @Override
  public Category getCategory() {
    return category;
  }

  @Override
  public String getSortIndex() {
    return sortIndex;
  }

  public String getProcessElementId() {
    return processElementId;
  }

  public void setProcessElementId(String processElementId) {
    this.processElementId = processElementId;
  }
  
  @Override
  public String getPortalProcessInformation() {
    return portalProcessInformation;
  }
  
  public String getDefaultImageDarkUrl() {
    // Change default image value
    if (imageUrl.contains(DEFAULT_IMAGE_VALUE_LIGHT_MODE)) {
      imageUrl = getContentImageUrl(DefaultImage.PROCESSMODELINGDARK.getPath());
    }
    return imageUrl;
  }
}
