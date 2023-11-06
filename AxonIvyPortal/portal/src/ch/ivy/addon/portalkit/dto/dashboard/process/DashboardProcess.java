package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.bo.PortalExpressProcess;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.Category;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardProcess implements Process {
  private static final String EXPRESS_WORKFLOW_ID_PARAM = "?workflowID=";
  private String id;
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
  }

  public DashboardProcess(ExpressProcess process) {
    this.id = process.getId();
    this.type = ProcessType.EXPRESS_PROCESS;
    this.name = process.getProcessName();
    this.description = process.getProcessDescription();
    this.icon = process.getIcon();
    this.category = CategoryUtils.buildExpressCategory(process.getProcessName());
    this.application = IApplication.current().getName();
  }

  public DashboardProcess(ExternalLink externalLink) {
    this.id = String.valueOf(externalLink.getId());
    this.type = ProcessType.EXTERNAL_LINK;
    this.name = externalLink.getName();
    this.description = externalLink.getDescription();
    this.startLink = externalLink.getLink();
    this.icon = externalLink.getIcon();
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
    if (this.type == ProcessType.EXPRESS_PROCESS) {
      return generateWorkflowStartLink();
    }
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
      } else if (this.type == ProcessType.EXPRESS_PROCESS) {
        return PortalExpressProcess.DEFAULT_ICON;
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

  private String generateWorkflowStartLink() {
    return ExpressProcessService.getInstance().findExpressWorkflowStartLink() + EXPRESS_WORKFLOW_ID_PARAM + this.id;
  }

  public Long getProcessStartId() {
    return processStartId;
  }

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

  @Override
  public Category getCategory() {
    return category;
  }

  @Override
  public String getSortIndex() {
    return sortIndex;
  }
}
