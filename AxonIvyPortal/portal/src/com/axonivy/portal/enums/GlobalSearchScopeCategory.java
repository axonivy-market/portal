package com.axonivy.portal.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum GlobalSearchScopeCategory {
  PROCESSES(0), TASKS(1), CASES(2);

  private int priority;

  GlobalSearchScopeCategory(int priority) {
    this.priority = priority;
  }

  public String getLabel() {
    return Ivy.cms().co("/Labels/Enums/GlobalSearchScopeCategory/" + name());
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }
}
