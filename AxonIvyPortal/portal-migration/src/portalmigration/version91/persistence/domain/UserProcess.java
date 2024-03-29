package portalmigration.version91.persistence.domain;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import portalmigration.dto.DisplayName;
import portalmigration.enums.ProcessType;
import portalmigration.util.Locales;

public class UserProcess extends BusinessEntity implements Cloneable {
  private long applicationId = Long.MIN_VALUE;
  private Long userId;
  private ProcessType processType;
  private String processName;
  private List<DisplayName> names;
  private String link;
  private String icon;
  private String processId;
  private String workflowId;
  private Integer index;
  private boolean defaultProcess;
  private boolean isExternalLink;
  @JsonIgnore
  private boolean isBrokenLink = false;

  /**
   *  Since 9.1, we use userId to store user process instead of userName.
   */
  @Deprecated
  @JsonIgnore
  private String userName;

  @JsonIgnore
  private String description;

  public UserProcess() {
  }

  public UserProcess(String processName, long applicationId, long userId, String link) {
    this.processName = processName;
    this.applicationId = applicationId;
    this.userId = userId;
    this.link = link;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Deprecated
  public String getUserName() {
    return userName;
  }

  @Deprecated
  public void setUserName(String userName) {
    this.userName = userName;
  }

  public long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(long applicationId) {
    this.applicationId = applicationId;
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

  public boolean isDefaultProcess() {
    return defaultProcess;
  }

  public void setDefaultProcess(boolean defaultProcess) {
    this.defaultProcess = defaultProcess;
  }

  public String getProcessId() {
    return processId;
  }

  public void setProcessId(String processId) {
    this.processId = processId;
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
    return String.format("UserProcess {userId=%s, processName=%s, icon=%s, link=%s, isDefaultProcess=%s, id=%s}", userId, processName, icon, link, defaultProcess, id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultProcess, icon, link, processName, userId);
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
