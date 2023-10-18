package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvyNotificationChannelSubcriptionDTO;
import ch.ivyteam.ivy.notification.channel.NotificationSubscription;
import ch.ivyteam.ivy.notification.event.NotificationEvent;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;

@ManagedBean
@ViewScoped
public class NotificationChannelBean implements Serializable {

  private static final long serialVersionUID = 52874962558656141L;
  private final ISecurityMember subscriber;
  private final ISecurityContext securityContext;

  private List<String> events;
  private List<IvyNotificationChannelDTO> channels;

  private NotificationChannelBean(ISecurityMember subscriber, ISecurityContext securityContext) {
    this.subscriber = subscriber;
    this.securityContext = securityContext;
  }

  public void onload() {
    events = new ArrayList<>(NotificationEvent.allAsString());
    channels = IvyNotificationChannelDTO.all(subscriber, securityContext, events);
  }

  public void reset() {
    channels.forEach(this::resetChannel);
    onload();
    addMessage("Notification Channels reset");
  }

  private void resetChannel(IvyNotificationChannelDTO channel) {
    channel.getSubscriptions()
        .forEach(
            (event, subscription) -> subscription.setState(IvyNotificationChannelSubcriptionDTO.State.USE_DEFAULT));
  }

  private void saveChannel(IvyNotificationChannelDTO channel) {
    channel.getSubscriptions().entrySet().forEach(eventSubscription -> {
      var subscription = NotificationSubscription.of(subscriber, channel.getChannel(), eventSubscription.getKey());
      subscription.state(eventSubscription.getValue().getState().toDbState());
    });
  }

  private void addMessage(String msg) {
    FacesContext.getCurrentInstance().addMessage("notificationMessage", new FacesMessage(msg));
  }

  public List<IvyNotificationChannelDTO> getChannels() {
    return channels;
  }

  public List<String> getEvents() {
    return events;
  }

  public static NotificationChannelBean instance(ISecurityMember member, ISecurityContext securityContext) {
    var model = new NotificationChannelBean(member, securityContext);
    model.onload();
    return model;
  }
}
