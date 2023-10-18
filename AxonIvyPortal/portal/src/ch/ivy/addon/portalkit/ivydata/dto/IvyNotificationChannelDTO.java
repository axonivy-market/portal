package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.notification.channel.NotificationChannel;
import ch.ivyteam.ivy.notification.channel.NotificationSubscription;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;

public class IvyNotificationChannelDTO extends AbstractResultDTO {

  private final NotificationChannel channel;
  private final Map<String, IvyNotificationChannelSubcriptionDTO> subscriptions;

  private IvyNotificationChannelDTO(NotificationChannel channel,
      Map<String, IvyNotificationChannelSubcriptionDTO> subscriptions) {
    this.channel = channel;
    this.subscriptions = subscriptions;
  }

  public static List<IvyNotificationChannelDTO> all(ISecurityMember subscriber, ISecurityContext securityContext,
      List<String> events) {
    var channels = NotificationChannel.all().stream().filter(channel -> channel.configFor(securityContext).enabled())
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

  public Map<String, IvyNotificationChannelSubcriptionDTO> getSubscriptions() {
    return subscriptions;
  }

  public IvyNotificationChannelSubcriptionDTO getSubscription(String event) {
    return subscriptions.get(event);
  }

  public void setSubscriptionIconAndTitle(String event) {
    var subscription = subscriptions.get(event);
    var state = subscription.getState();
    boolean subscribedByUser = IvyNotificationChannelSubcriptionDTO.State.SUBSCRIBED.equals(state);
    boolean useDefault = IvyNotificationChannelSubcriptionDTO.State.USE_DEFAULT.equals(state);
    var icon = new StringBuilder();
    var iconTitle = new StringBuilder();

    if (subscribedByUser || (useDefault && subscription.isSubscribedByDefault())) {
      icon.append("check-circle-1 state-active");
      iconTitle.append("Subscribed");
    } else {
      icon.append("remove-circle state-inactive");
      iconTitle.append("Not subscribed");
    }

    if (useDefault) {
      icon.append(" light");
      iconTitle.append(" by default");
    }

    subscription.setIcon(icon.toString());
    subscription.setTitle(iconTitle.toString());
  }
}
