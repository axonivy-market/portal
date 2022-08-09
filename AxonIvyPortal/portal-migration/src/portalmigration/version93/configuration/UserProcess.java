package portalmigration.version93.configuration;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import portalmigration.dto.DisplayName;
import portalmigration.enums.ProcessType;
import portalmigration.util.Locales;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserProcess extends AbstractConfiguration {
  private ProcessType processType;
  private String processName;
  private List<DisplayName> names;
  private String link;
  private String icon;
  private String processId;
  private String workflowId;
  private Integer index;
  private boolean isExternalLink;
  @JsonIgnore
  private boolean isBrokenLink = false;

  @JsonIgnore
  private String description;

  public UserProcess() {
  }

  public UserProcess(UserProcess userProcess) {
    processType = userProcess.processType;
    processName = userProcess.processName;
    names = userProcess.names;
    link = userProcess.link;
    icon = userProcess.icon;
    processId = userProcess.processId;
    index = userProcess.index;
    isBrokenLink = userProcess.isBrokenLink;
    description = userProcess.description;
  }

  public UserProcess(String processName, String link) {
    this.processName = processName;
    this.link = link;
  }

  public ProcessType getProcessType() {
    return processType;
  }

  public void setProcessType(ProcessType processType) {
    this.processType = processType;
  }

  /**
   * Gets the display name of process by current active locale
   * @return process name
   */
  public String getProcessName() {
    if (CollectionUtils.isNotEmpty(this.names)) {
      return getActiveDisplayName();
    }
    return processName;
  }

  private String getActiveDisplayName() {
    Locale currentLocale = new Locales().getCurrentLocale();
    return names.stream().filter(displayName -> displayName.getLocale().equals(currentLocale))
        .map(DisplayName::getValue)
        .findFirst().orElse(this.processName);
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public List<DisplayName> getNames() {
    return names;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getProcessId() {
    return processId;
  }

  public void setProcessId(String processId) {
    this.processId = processId;
  }

  public boolean isBrokenLink() {
    return isBrokenLink;
  }

  @JsonIgnore
  public void setBrokenLink(boolean isBrokenLink) {
    this.isBrokenLink = isBrokenLink;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  @Override
  public String toString() {
    return String.format("UserProcess {processName=%s, icon=%s, link=%s, id=%s}", processName, icon, link, getId());
  }

  public String getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  public boolean isExternalLink() {
    return isExternalLink;
  }

  public void setExternalLink(boolean isExternalLink) {
    this.isExternalLink = isExternalLink;
  }

}
