package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum DeputyRoleType {
  PERSONAL_TASK_PERMANENT_DEPUTIES("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/personalTaskPermanentDeputies"),
  PERSONAL_TASK_DURING_ABSENCE_DEPUTIES("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/personalTaskDuringAbsenceDeputies"),
  TASK_FOR_ROLE_DEPUTIES("/ch.ivy.addon.portalkit.ui.jsf/AbsenceAndDeputy/taskForRole");

  private final String cmsURI;

  DeputyRoleType(String cmsURI) {
    this.cmsURI = cmsURI;
  }

  /**
   * Get the label value.
   *
   * @return string value of label
   */
  public String getLabel() {
    return Ivy.cms().co(cmsURI);
  }
}
