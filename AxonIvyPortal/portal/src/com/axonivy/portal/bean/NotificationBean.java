package com.axonivy.portal.bean;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.axonivy.portal.datamodel.NotificationLazyModel;
import com.axonivy.portal.dto.NotificationDto;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelDTO;
import ch.ivy.addon.portalkit.util.GrowlMessageUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.notification.channel.NotificationChannel;
import ch.ivyteam.ivy.notification.channel.NotificationChannelSystemConfig;
import ch.ivyteam.ivy.notification.channel.NotificationSubscription;
import ch.ivyteam.ivy.notification.web.WebNotifications;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

@ViewScoped
@ManagedBean
public class NotificationBean implements Serializable {

  private static final long serialVersionUID = 4467991301954952570L;
  private static final String TASK_START = "TASK_START";
  private static final String TASK_DETAIL = "TASK_DETAIL";
  
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
      dataModel.markAsRead(dto.getNotification());
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
  
  public void startTask(NotificationDto dto) {
    markAsRead(dto);
    PortalNavigator.redirect(dto.getRunAction().getLink().getRelative());
  }
  
  public void startTaskFromNotification(NotificationDto dto, boolean isWorkingTask, ITask task) {
    if (isWorkingTask && task.getState() != TaskState.DONE) {
      PrimeFaces.current().executeScript(String.format("checkWarningLogForTaskStart('%s', '%s')", dto.getId(), task.getId()));
      return;
    }
    startTask(dto);
  }
  
  public void startTaskFromNotiId() {
    String notificationId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("notificationId");
    NotificationDto dto = dataModel.findById(notificationId);
    startTask(dto);
  }
  
  public void goToNotificationDetail(NotificationDto dto, boolean isWorkingTask, ITask task) {
    if (isWorkingTask && task.getState() != TaskState.DONE) {
      PrimeFaces.current().executeScript(String.format("checkWarningLogForTaskDetail('%s', '%s')", dto.getId(), task.getId()));
      return;
    }
    goToTaskDetail(dto);
  }
  
  public void goToTaskDetail(NotificationDto dto) {
    markAsRead(dto);
    PortalNavigator.redirect(dto.getInfoAction().getLink().getRelative());
  }
  
  public void goToTaskDetail() {
    String notificationId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("notificationId");
    NotificationDto dto = dataModel.findById(notificationId);
    goToTaskDetail(dto);
  }
  
  public void leaveTask() {
    handleTask(TaskUtils::resetTask);
  }

  public void reserveTask() {
    handleTask(TaskUtils::parkTask);
  }

  private void handleTask(Consumer<ITask> taskHandler) {
    FacesContext context = FacesContext.getCurrentInstance();
    String redirectType = context.getExternalContext().getRequestParameterMap().get("redirectType");
    String taskId = context.getExternalContext().getRequestParameterMap().get("taskId");
    ITask task = TaskUtils.findTaskById(Long.valueOf(taskId));

    taskHandler.accept(task);

    switch (redirectType) {
    case TASK_START:
      startTaskFromNotiId();
      break;
    case TASK_DETAIL:
      goToTaskDetail();
      break;
    }

    if (task.getState() != TaskState.DONE) {
      GrowlMessageUtils.addFeedbackMessage(task.getState() == TaskState.DONE, task.getCase());
    }
  }

}
