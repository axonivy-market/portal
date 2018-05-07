package ch.ivy.gawfs.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum ProcessType {
  AD_HOC("AMWF", "/Dialogs/workflowCreation/WorkflowDefinition/ProcesstypeAdHoc"),
  REPEAT("AHWF", "/Dialogs/workflowCreation/WorkflowDefinition/ProcesstypeRepeating");

  private final String value;
  private final String label;

  ProcessType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  public String getValue() {
    return value;
  }

  public String getLabel() {
    return Ivy.cms().co(label);
  }
}