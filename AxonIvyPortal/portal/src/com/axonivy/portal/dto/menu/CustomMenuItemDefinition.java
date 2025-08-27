package com.axonivy.portal.dto.menu;

import java.util.Optional;

import com.axonivy.portal.components.enums.MenuKind;
import com.axonivy.portal.components.util.ProcessStartUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;
import ch.ivyteam.ivy.model.value.WebLink;
import ch.ivyteam.ivy.workflow.IProcessStart;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = 2389106209816728259L;

  private String processStartPath;
  private String application;

  @JsonIgnore
  private IProcessStart processStart;

  @Override
  public MenuKind getType() {
    return MenuKind.CUSTOM;
  }

  public String getProcessStartPath() {
    return processStartPath;
  }

  public void setProcessStartPath(String processStartPath) {
    this.processStartPath = processStartPath;
  }

  @JsonIgnore
  public IProcessStart getProcessStart() {
    return processStart;
  }

  @JsonIgnore
  public void setProcessStart(IProcessStart processStart) {
    this.processStart = processStart;
  }

  @Override
  public PortalMenuItem convertToPortalMenuItem() {
    if (!hasPermission()) {
      return null;
    }

    if (processStart == null) {
      processStart = ProcessStartUtils.findProcessStartByUserFriendlyRequestPath(processStartPath);
    }

    PortalMenuBuilder builder = new PortalMenuBuilder(
        getTitles().stream().filter(name -> name.getLocale().toLanguageTag().contentEquals(getCurrentLanguage()))
            .findFirst().get().getValue(),
        getType(), false).icon(getIconClass())
        .url(Optional.of(processStart).map(IProcessStart::getLink).map(WebLink::getRelativeEncoded)
            .orElseGet(() -> DEFAULT_LINK));

    if (getWorkingtaskId() == null) {
      return builder.build();
    }

    return builder.isWorkingOnATask(true).workingTaskId(getWorkingtaskId()).build();
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }
}