package com.axonivy.portal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.datamodel.NotificationLazyModel;
import com.axonivy.portal.dto.NotificationDto;

import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelDTO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.notification.channel.NotificationChannel;
import ch.ivyteam.ivy.notification.channel.NotificationChannelSystemConfig;
import ch.ivyteam.ivy.notification.channel.NotificationSubscription;
import ch.ivyteam.ivy.notification.event.NotificationEvent;
import ch.ivyteam.ivy.notification.web.WebNotifications;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;

@ViewScoped
@ManagedBean
public class NotificationBean implements Serializable {

  private static final long serialVersionUID = 4467991301954952570L;
  private final WebNotifications webNotifications;
  private final NotificationLazyModel dataModel;

  private long countAll;
  private long countUnread;
  private boolean isRender;
  private List<IvyNotificationChannelDTO> channels;
  private ISecurityContext securityContext;
  private ISecurityMember subscriber;
  private List<String> events;
  private Boolean isBellIconNeeded;

  public NotificationBean() {
    this.events = new ArrayList<>(NotificationEvent.allAsString());
    this.webNotifications = WebNotifications.current();
    this.countAll = webNotifications.countAll();
    this.countUnread = webNotifications.countUnread();
    this.dataModel = new NotificationLazyModel(webNotifications);
    this.subscriber = Ivy.session().getSessionUser();
    this.securityContext = ISecurityContext.current();
    this.channels = IvyNotificationChannelDTO.all(subscriber, securityContext, events);
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
      countUnread--;
    }
  }

  public boolean hasNotifications() {
    return countAll != 0;
  }

  public boolean hasUnreadNotifications() {
    return countUnread != 0;
  }

  public String getUnreadNotifications() {
    return countUnread > 99 ? "99+" : String.valueOf(countUnread);
  }

  public void setRender(boolean isRender) {
    this.isRender = isRender;
  }

  public boolean isRender() {
    return isRender;
  }

  private boolean isOnlyUnread = false;

  public boolean isOnlyUnread() {
    return isOnlyUnread;
  }

  public void setOnlyUnread(boolean isOnlyUnread) {
    this.isOnlyUnread = isOnlyUnread;
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
        .filter(channel -> channel.displayName().equalsIgnoreCase("web")).map(NotificationChannel::config)
        .map(NotificationChannelSystemConfig::enabled).map(Boolean::valueOf).findFirst().orElse(false);
  }

  private boolean isWebChannelEnableByUser() {
    List<NotificationSubscription> subscriptions = channels.stream().map(IvyNotificationChannelDTO::getChannel)
        .filter(channel -> channel.displayName().equalsIgnoreCase("web"))
        .map(channel -> channel.configFor(subscriber).subscriptions()).findFirst().orElse(null);
    if (subscriptions != null) {
      return subscriptions.stream().map(NotificationSubscription::state)
          .anyMatch(arg0 -> arg0.equals(NotificationSubscription.State.SUBSCRIBED)
              || arg0.equals(NotificationSubscription.State.USE_DEFAULT));
    } else
      return false;
  }
}
