package portal.guitest.common;

public enum CaseState {
  CREATED, IN_PROGRESS, DONE, DESTROYED;

  public static CaseState fromClass(String stateClass) {
    switch (stateClass.trim()) {
      case "case-state-in-progress":
        return IN_PROGRESS;

      case "case-state-done":
        return DONE;

      case "case-state-zombie-destroyed":
        return DESTROYED;
      default:
        return CREATED;
    }
  }
}
