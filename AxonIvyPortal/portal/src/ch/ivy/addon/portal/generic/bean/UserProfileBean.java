package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelSubcriptionDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationEventDTO;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.notification.channel.NotificationSubscription;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class UserProfileBean implements Serializable {
  private static final long serialVersionUID = 4952280551311826903L;

  public UserProfileBean() {}

  private ISecurityMember subscriber;
  private ISecurityContext securityContext;

  private List<IvyNotificationEventDTO> events;
  private List<IvyNotificationChannelDTO> channels;

  public void init() {
    subscriber = Ivy.session().getSessionUser();
    securityContext = ISecurityContext.current();
    onloadChannel();
  }

  public void saveHomepage(String homepageName) {
    IUser user = Ivy.session().getSessionUser();
    if (StringUtils.isBlank(homepageName)) {
      user.removeProperty(UserProperty.HOMEPAGE);
    }
    user.setProperty(UserProperty.HOMEPAGE, homepageName);
  }

  public void onloadChannel() {
    events = IvyNotificationEventDTO.all();
    channels = IvyNotificationChannelDTO.all(subscriber, securityContext);
  }

  public void resetAllChannel() {
    channels.forEach(this::resetChannel);
    onloadChannel();
    addMessage(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/notificationChannelsReset"));
  }

  private void resetChannel(IvyNotificationChannelDTO channel) {
    channel.getSubscriptions().forEach(
        (event, subscription) -> subscription.setState(IvyNotificationChannelSubcriptionDTO.State.USE_DEFAULT));
    saveChannel(channel);
  }

  public void saveAllChannel() {
    channels.forEach(this::saveChannel);
  }

  private void saveChannel(IvyNotificationChannelDTO channel) {
    channel.getSubscriptions().entrySet().forEach(eventSubscription -> {
      var subscription = NotificationSubscription.of(subscriber, channel.getChannel(), eventSubscription.getKey());
      subscription.state(eventSubscription.getValue().getState().toDbState());
    });
  }

  private void addMessage(String msg) {
    FacesContext.getCurrentInstance().addMessage("notification-Message", FacesMessageUtils.sanitizedMessage(msg));
  }

  public List<IvyNotificationChannelDTO> getChannels() {
    return channels;
  }

  public List<IvyNotificationEventDTO> getEvents() {
    return events;
  }

  public Boolean canAccessProcessList() {
    return PermissionUtils.checkAccessFullProcessListPermission();
  }

  public void deleteAllFavoriteTasks() {
    IUser currentUser = Ivy.session().getSessionUser();
    if (currentUser != null) {
      TaskUtils.removeAllFavoriteTasks();
      addMessage(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/deleteAllFavoriteTask",
          Arrays.asList(currentUser.getDisplayName())));
    }
  }
}
