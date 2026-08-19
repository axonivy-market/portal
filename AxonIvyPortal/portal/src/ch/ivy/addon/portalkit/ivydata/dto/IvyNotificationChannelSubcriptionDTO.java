package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.Objects;

import ch.ivyteam.ivy.notification.channel.NotificationSubscription;

public class IvyNotificationChannelSubcriptionDTO {

  private State state;
  private final boolean isSubscribedByDefault;
  private String icon;
  private String title;

  public IvyNotificationChannelSubcriptionDTO(NotificationSubscription.State state, boolean isSubscribedByDefault) {
    this.state = State.fromDbState(state);
    this.isSubscribedByDefault = isSubscribedByDefault;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public Object getStateAsObject() {
    return state.value;
  }

  public void setStateAsObject(Object value) {
    this.state = State.of(value);
  }

  public boolean isSubscribedByDefault() {
    return isSubscribedByDefault;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public static enum State {
    USE_DEFAULT(null), SUBSCRIBED(true), NOT_SUBSCRIBED(false);

    private final Boolean value;

    private State(Boolean value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static State of(Object bool) {
      for (State state : values()) {
        if (Objects.equals(state.value, bool)) {
          return state;
        }
      }
      throw new IllegalArgumentException("Unexpected value: " + bool);
    }

    public static IvyNotificationChannelSubcriptionDTO.State fromDbState(NotificationSubscription.State state) {
      return switch (state) {
        case USE_DEFAULT -> USE_DEFAULT;
        case SUBSCRIBED -> SUBSCRIBED;
        case NOT_SUBSCRIBED -> NOT_SUBSCRIBED;
        default -> throw new IllegalArgumentException("Unexpected value: " + state);
      };
    }

    public NotificationSubscription.State toDbState() {
      return switch (value) {
        case null -> NotificationSubscription.State.USE_DEFAULT;
        case Boolean b when b == true -> NotificationSubscription.State.SUBSCRIBED;
        case Boolean b when b == false -> NotificationSubscription.State.NOT_SUBSCRIBED;
        default -> throw new IllegalArgumentException("Unexpected value: " + value);
      };
    }
  }
}
