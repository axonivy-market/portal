package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum CaseEmptyMessage {
  COFFEE_OF_HOT("si-coffee-cup-hot"),
  YOGA_MEDITATE("si-yoga-meditate"),
  RANKING_WINNER_MEDAL("si-ranking-winner-medal"),
  SOCCER_PLAYER("si-soccer-player"),
  CHARGING_BATTERY_FULL_1("si-charging-battery-full-1"),
  BEACH_PALM_SUNBED("si-beach-palm-sunbed");

  private String icon;

  private CaseEmptyMessage() {}

  private CaseEmptyMessage(String icon) {
    this.icon = icon;
  }

  public String getMessage() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/iconMessage/caseWidget/" + name());
  }

  public String getIcon() {
    return icon;
  }
}
