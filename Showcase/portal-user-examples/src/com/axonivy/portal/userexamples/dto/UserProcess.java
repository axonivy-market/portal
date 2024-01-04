package com.axonivy.portal.userexamples.dto;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.DisplayName;
import com.axonivy.portal.components.enums.ProcessType;

import ch.ivyteam.ivy.environment.Ivy;

public class UserProcess {
  private ProcessType processType;
  @Deprecated(since = "10.0", forRemoval = true)
  private String processName;
  private List<DisplayName> names;
  private String link;
  private String icon;
  private String processId;
  private String description;

  public UserProcess() {}

  public UserProcess(UserProcess userProcess) {
    processType = userProcess.processType;
    processName = userProcess.processName;
    names = userProcess.names;
    link = userProcess.link;
    icon = userProcess.icon;
    processId = userProcess.processId;
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
   * 
   * @return process name
   */
  public String getProcessName() {
    if (CollectionUtils.isNotEmpty(this.names)) {
      return getActiveDisplayName();
    }

    return getDisplayNameWithCms();
  }

  private String getActiveDisplayName() {
    Locale currentLocale = Ivy.session().getContentLocale();
    return names.stream().filter(displayName -> displayName.getLocale().equals(currentLocale))
        .map(DisplayName::getValue).findFirst().orElse(getDisplayNameWithCms());
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return String.format("UserProcess {processName=%s, icon=%s, link=%s, id=%s}", processName, icon, link);
  }

  private String getDisplayNameWithCms() {
    return StringUtils.startsWithIgnoreCase(processName, "cms")
        ? Ivy.cms().co(StringUtils.removeStart(processName, "cms"))
        : processName;
  }
}
