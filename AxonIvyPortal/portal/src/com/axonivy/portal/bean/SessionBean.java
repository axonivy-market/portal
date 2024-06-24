package com.axonivy.portal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.PrimeFaces;

import com.axonivy.portal.dto.PortalSessionInfo;
import com.fasterxml.jackson.core.JsonProcessingException;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;

@SessionScoped
@ManagedBean
public class SessionBean implements Serializable {

  private static final long serialVersionUID = 7126184987391824473L;
  private List<PortalSessionInfo> sessionInfos;

  public static final long TIME_BEFORE_LOST_SESSION = 3
      * DateUtils.MILLIS_PER_MINUTE; // 3 minutes

  public long getClientSideTimeout() {
    String clientSideTimeoutInMinute = GlobalSettingService.getInstance()
        .findGlobalSettingValue(GlobalVariable.CLIENT_SIDE_TIMEOUT);
    if (StringUtils.isNotBlank(clientSideTimeoutInMinute)) {
      Long timeoutInMinute = Long.valueOf(clientSideTimeoutInMinute);
      if (timeoutInMinute > 0) {
        return timeoutInMinute * DateUtils.MILLIS_PER_MINUTE;
      }
    }
    return getDefaultClientSideTimeout();
  }

  private long getDefaultClientSideTimeout() {
    ExternalContext externalContext = getExternalContext();
    long serverSideTimeOutInMillisecond = externalContext
        .getSessionMaxInactiveInterval() * DateUtils.MILLIS_PER_SECOND;
    return serverSideTimeOutInMillisecond - TIME_BEFORE_LOST_SESSION;
  }

  private ExternalContext getExternalContext() {
    return FacesContext.getCurrentInstance().getExternalContext();
  }

  private boolean isActive;

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  public void onActive() {
    this.isActive = true;
  }

  public void onIdle() {
    this.isActive = false;
  }

  private PortalSessionInfo initNewTabInteactionFromContext() {
    Map<String, String> params = FacesContext.getCurrentInstance()
        .getExternalContext().getRequestParameterMap();

    PortalSessionInfo info = new PortalSessionInfo(
        Optional.ofNullable(params.get("tabId")).orElse(""));
    info.setMillisecondsToTimeout(getClientSideTimeout());
    return info;
  }

  private String getTabIdFromContext() {
    Map<String, String> params = FacesContext.getCurrentInstance()
        .getExternalContext().getRequestParameterMap();
    return Optional.ofNullable(params.get("tabId")).orElse("");
  }

  public void keepSession() throws JsonProcessingException {
    keepSession(true);
  }

  public synchronized void keepSession(boolean shouldCheckOtherTabs)
      throws JsonProcessingException {
    if (Ivy.session().isSessionUserUnknown()) {
      sessionInfos = null;
      return;
    }

    if (sessionInfos == null) {
      sessionInfos = new ArrayList<>();
    }

    PortalSessionInfo newTabInteraction = initNewTabInteactionFromContext();
    List<PortalSessionInfo> newInfos = new ArrayList<>();
    for (PortalSessionInfo info : sessionInfos) {
      if (!info.getTabId().contentEquals(newTabInteraction.getTabId())) {
        long updatedTimeout = updateMillisecondsToTimeout(info);
        if (updatedTimeout <= 0) {
          continue;
        }
        info.setMillisecondsToTimeout(updatedTimeout);
        newInfos.add(info);
      }
    }

    newInfos.add(newTabInteraction);
    setSessionInfos(newInfos);

    PrimeFaces.current()
        .executeScript(
            "PortalSessionWarning.getSessionInfoCmd('"
                + BusinessEntityConverter.entityToJsonValue(newTabInteraction)
                + "')");
  }

  public void unloadSession() throws JsonProcessingException {
    if (sessionInfos == null) {
      return;
    }
    for (PortalSessionInfo info : sessionInfos) {
      if (info.getTabId().contentEquals(getTabIdFromContext())) {
        sessionInfos.remove(info);
        break;
      }
    }
  }

  private long updateMillisecondsToTimeout(PortalSessionInfo info) {
    return getClientSideTimeout()
        - ((new Date()).getTime() - info.getActiveTime().getTime());
  }

  public List<PortalSessionInfo> getSessionInfos() {
    return sessionInfos;
  }

  public void setSessionInfos(List<PortalSessionInfo> sessionInfos) {
    this.sessionInfos = sessionInfos;
  }
}
