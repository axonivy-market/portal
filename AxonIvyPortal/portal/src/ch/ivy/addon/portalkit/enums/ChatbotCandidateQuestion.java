package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum ChatbotCandidateQuestion {

  EXPLORE("si-bulb"),
  TUTORIAL("si-touch-finger_1"),
  INTERACT("si-cog");

  private String icon;

  ChatbotCandidateQuestion(String icon) {
    this.setIcon(icon);
  }

  public String getHeaderText() {
    return Ivy.cms().co("/Labels/Enums/ChatbotCandidateQuestion/" + name() + "/header");
  }

  public String getQuestion() {
    return Ivy.cms().co("/Labels/Enums/ChatbotCandidateQuestion/" + name() + "/question");
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}
