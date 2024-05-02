package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.notification.channel.Event;
import ch.ivyteam.ivy.notification.channel.NotificationChannel;
import ch.ivyteam.ivy.notification.channel.NotificationSubscription;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;

public class IvyNotificationChannelDTO {

  private final NotificationChannel channel;
  private final Map<Event, IvyNotificationChannelSubcriptionDTO> subscriptions;

  private IvyNotificationChannelDTO(NotificationChannel channel,
      Map<Event, IvyNotificationChannelSubcriptionDTO> subscriptions) {
    this.channel = channel;
    this.subscriptions = subscriptions;
  }

  public static List<IvyNotificationChannelDTO> all(ISecurityMember subscriber, ISecurityContext securityContext) {
    var events = Event.all();
    var channels = NotificationChannel.all(securityContext).stream().filter(channel -> channel.config().enabled())
        .map(channel -> toChannel(subscriber, channel)).toList();
    channels.forEach(channel -> events.forEach(event -> channel.setSubscriptionIconAndTitle(event)));
    return channels;
  }

  private static IvyNotificationChannelDTO toChannel(ISecurityMember subscriber, NotificationChannel channel) {
    var subscriptions = channel.configFor(subscriber).subscriptions().stream()
        .collect(Collectors.toMap(NotificationSubscription::event,
            subscription -> new IvyNotificationChannelSubcriptionDTO(subscription.state(),
                subscription.isSubscribedByDefault())));
    return new IvyNotificationChannelDTO(channel, subscriptions);
  }

  public NotificationChannel getChannel() {
    return channel;
  }

  public Map<Event, IvyNotificationChannelSubcriptionDTO> getSubscriptions() {
    return subscriptions;
  }

  public IvyNotificationChannelSubcriptionDTO getSubscription(IvyNotificationEventDTO event) {
    return subscriptions.get(event.getEvent());
  }

  public void setSubscriptionIconAndTitle(IvyNotificationEventDTO event) {
    setSubscriptionIconAndTitle(event.getEvent());
  }

  private void setSubscriptionIconAndTitle(Event event) {
    var subscription = subscriptions.get(event);
    if (subscription != null) {
      var state = subscription.getState();
      boolean subscribedByUser = IvyNotificationChannelSubcriptionDTO.State.SUBSCRIBED.equals(state);
      boolean useDefault = IvyNotificationChannelSubcriptionDTO.State.USE_DEFAULT.equals(state);
      var icon = new StringBuilder();
      var iconTitle = new StringBuilder();

      if (subscribedByUser || (useDefault && subscription.isSubscribedByDefault())) {
        icon.append("check-circle-1 state-active");
        iconTitle.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/subscribed"));
      } else {
        icon.append("remove-circle state-inactive");
        iconTitle.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/notSubscribed"));
      }

      if (useDefault) {
        icon.append(" light");
        iconTitle.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/byDefault"));
      }

      subscription.setIcon(icon.toString());
      subscription.setTitle(iconTitle.toString());
    }
  }
}
