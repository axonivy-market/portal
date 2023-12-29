package com.axonivy.portal.selenium.common;

public enum CaseState {
  OPEN, DONE, DESTROYED;

  public static CaseState fromClass(String stateClass) {
    switch (stateClass.trim()) {
      case "done-case-state":
        return DONE;

      case "destroyed-case-state":
        return DESTROYED;
      default:
        return OPEN;
    }
  }
}
