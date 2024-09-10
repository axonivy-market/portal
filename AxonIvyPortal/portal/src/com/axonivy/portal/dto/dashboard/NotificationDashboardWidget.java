package com.axonivy.portal.dto.dashboard;

import com.axonivy.portal.datamodel.NotificationLazyModel;
import com.axonivy.portal.dto.NotificationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivyteam.ivy.notification.web.WebNotifications;

public class NotificationDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 2688408047581268705L;

  @JsonIgnore
  private final WebNotifications webNotifications;
  @JsonIgnore
  private NotificationLazyModel dataModel;
  @JsonIgnore
  private long countAll;
  @JsonIgnore
  private long countUnread;
  private boolean onlyUnread;
  private boolean showFullscreenMode;

  public NotificationDashboardWidget() {
    this.webNotifications = WebNotifications.current();
    this.countAll = webNotifications.countAll();
    this.countUnread = webNotifications.countUnread();
    setShowFullscreenMode(true);
  }

  @JsonIgnore
  public static NotificationDashboardWidget buildDefaultWidget(String widgetId, String widgetName) {
    var widget = new NotificationDashboardWidget();
    widget.setId(widgetId);
    widget.setName(widgetName);
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(4);
    widget.getLayout().setHeight(6);
    widget.getLayout().setAxisX(0);
    widget.getLayout().setAxisY(0);
    return widget;
  }

  public void loadFirstTime() {
    this.dataModel = new NotificationLazyModel(this.webNotifications);
    this.dataModel.setOnlyUnread(this.onlyUnread);
    this.dataModel.setWidgetId(this.getId());
  }

  @Override
  public void resetWidgetFilters() {
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.NOTIFICATION;
  }

  public boolean isOnlyUnread() {
    return onlyUnread;
  }

  public void setOnlyUnread(boolean onlyUnread) {
    this.onlyUnread = onlyUnread;
  }

  public NotificationLazyModel getDataModel() {
    return dataModel;
  }

  public void markAsRead(NotificationDto dto) {
    if (!dto.isRead()) {
      dataModel.markAsRead(dto.getNotification());
    }
  }

  public boolean hasNotifications() {
    return this.onlyUnread ? countUnread != 0 : countAll != 0;
  }

  @Override
  public void cancelUserFilter() { }
  
  public void startTaskFromNoti(NotificationDto dto) {
    markAsRead(dto);
    PortalNavigator.redirect(dto.getRunAction().getLink().getRelative());
  }
  
  public void goToTaskDetail(NotificationDto dto) {
    markAsRead(dto);
    PortalNavigator.redirect(dto.getInfoAction().getLink().getRelative());
  }

  public boolean isShowFullscreenMode() {
    return showFullscreenMode;
  }

  public void setShowFullscreenMode(boolean showFullscreenMode) {
    this.showFullscreenMode = showFullscreenMode;
  }

}
