package com.axonivy.portal.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.datamodel.NotificationLazyModel;
import com.axonivy.portal.dto.NotificationDto;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelDTO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.notification.channel.NotificationChannel;
import ch.ivyteam.ivy.notification.channel.NotificationChannelSystemConfig;
import ch.ivyteam.ivy.notification.channel.NotificationSubscription;
import ch.ivyteam.ivy.notification.web.WebNotifications;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;

@ViewScoped
@ManagedBean
public class NotificationBean implements Serializable {

  private static final long serialVersionUID = 4467991301954952570L;
  private final WebNotifications webNotifications;
  private final NotificationLazyModel dataModel;
  private final String WEB = "web";

  private long countAll;
  private long countUnread;
  private boolean isRender;
  private List<IvyNotificationChannelDTO> channels;
  private ISecurityContext securityContext;
  private ISecurityMember subscriber;
  private Boolean isBellIconNeeded;

  public NotificationBean() {
    this.webNotifications = WebNotifications.current();
    this.countAll = webNotifications.countAll();
    this.countUnread = webNotifications.countUnread();
    this.dataModel = new NotificationLazyModel(webNotifications);
    this.subscriber = Ivy.session().getSessionUser();
    this.securityContext = ISecurityContext.current();
    this.channels = IvyNotificationChannelDTO.all(subscriber, securityContext);
    this.isBellIconNeeded = isNotificationBellRendered();
  }

  public NotificationLazyModel getDataModel() {
    return dataModel;
  }

  public void hideAll() {
    webNotifications.hideAll();
    countAll = 0;
    countUnread = 0;
  }

  public void readAll() {
    webNotifications.markAllAsRead();
    countUnread = 0;
  }

  public void markAsRead(NotificationDto dto) {
    if (!dto.isRead()) {
      dataModel.markAsRead(dto.getNotification());
    }
  }

  public boolean hasNotifications() {
    return countAll != 0;
  }

  public boolean hasUnreadNotifications() {
    return countUnread != 0;
  }

  public String getUnreadNotifications() {
    countUnread = webNotifications.countUnread();
    return countUnread > 99 ? "99+" : String.valueOf(countUnread);
  }

  public void setRender(boolean isRender) {
    this.isRender = isRender;
  }

  public boolean isRender() {
    return isRender;
  }

  public long getCountUnread() {
    return this.countUnread;
  }

  public Boolean getIsBellIconNeeded() {
    return isBellIconNeeded;
  }

  public void setIsBellIconNeeded(Boolean isBellIconNeeded) {
    this.isBellIconNeeded = isBellIconNeeded;
  }

  public boolean isNotificationBellRendered() {
    if (!isWebChannelEnableByAdmin()) {
      return false;
    } else if (isWebChannelEnableByUser()) {
      return true;
    } else
      return false;
  }

  private boolean isWebChannelEnableByAdmin() {
    return channels.stream().map(IvyNotificationChannelDTO::getChannel)
        .filter(channel -> channel.displayName().equalsIgnoreCase(WEB)).map(NotificationChannel::config)
        .map(NotificationChannelSystemConfig::enabled).map(Boolean::valueOf).findFirst().orElse(false);
  }

  private boolean isWebChannelEnableByUser() {
    List<NotificationSubscription> subscriptions = channels.stream().map(IvyNotificationChannelDTO::getChannel)
        .filter(channel -> channel.displayName().equalsIgnoreCase(WEB))
        .map(channel -> channel.configFor(subscriber).subscriptions()).findFirst().orElse(null);
    if (subscriptions != null) {
      return subscriptions.stream().map(NotificationSubscription::state)
          .anyMatch(arg0 -> arg0.equals(NotificationSubscription.State.SUBSCRIBED)
              || arg0.equals(NotificationSubscription.State.USE_DEFAULT));
    } else
      return false;
  }
  
  public void startTaskFromNoti(NotificationDto dto) {
    markAsRead(dto);
    PortalNavigator.redirect(dto.getRunAction().getLink().getRelative());
  }
  
  public void goToTaskDetail(NotificationDto dto) {
    markAsRead(dto);
    PortalNavigator.redirect(dto.getInfoAction().getLink().getRelative());
  }
  
}
