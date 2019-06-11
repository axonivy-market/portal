package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum WeekDay {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
  
  @Override
  public String toString() {
    String translatedEnum =
        Ivy.cms().co("ch.ivy.addon.portalkit.ui.jsf/Enums/" + getClass().getSimpleName() + "/" + name());
    return translatedEnum.isEmpty() ? name() : translatedEnum;
  }
}
