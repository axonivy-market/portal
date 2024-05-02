package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.notification.channel.Event;

public class IvyNotificationEventDTO {

  private final Event event;

  private IvyNotificationEventDTO(Event event) {
    this.event = event;
  }

  public static List<IvyNotificationEventDTO> all() {
    return Event.all().stream().map(IvyNotificationEventDTO::new).collect(Collectors.toList());
  }

  public String getDisplayName() {
    return event.displayName();
  }

  public String getDescription() {
    return event.description();
  }

  public Event getEvent() {
    return event;
  }
}
