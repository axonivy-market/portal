package ch.ivy.addon.portalkit.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.Locales;


public class UserProcess extends BusinessEntity implements Cloneable {
  private String userName;
  private String processName;
  private List<DisplayName> names;
  private String icon;
  private String link;
  private String workflowId;
  private Integer index;
  private boolean defaultProcess;
  private boolean isExternalLink;
  
  @JsonIgnore
  private String description;

  public UserProcess() {}

  public UserProcess(String processName, String userName, String link) {
    this.processName = processName;
    this.userName = userName;
    this.link = link;
    generateDisplayNames(processName);
  }

  private void generateDisplayNames(String processName) {
    if (this.names == null) {
      this.names = new ArrayList<>();
    }
    DisplayName currentLocaleDisplayName = new DisplayName();
    currentLocaleDisplayName.setLocale(new Locales().getCurrentLocale());
    currentLocaleDisplayName.setValue(processName);
    this.names.add(currentLocaleDisplayName);
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public boolean isDefaultProcess() {
    return defaultProcess;
  }

  public void setDefaultProcess(boolean defaultProcess) {
    this.defaultProcess = defaultProcess;
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
    return "UserProcess {userName=" + userName + ", processName=" + processName + ", icon=" + icon + ", link=" + link
        + ", isDefaultProcess=" + defaultProcess + ", id=" + getId() + "}";
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultProcess, icon, link, processName, userName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UserProcess other = (UserProcess) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (workflowId == null) {
      if (other.workflowId != null) {
        return false;
      }
    } else if (!workflowId.equals(other.workflowId)) {
      return false;
    }
    return true;
  }

  @Override
  public UserProcess clone() throws CloneNotSupportedException {
    return (UserProcess) super.clone();
  }
}
