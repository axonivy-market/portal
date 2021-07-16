package portal.guitest.common;

public enum CaseState {
  CREATED, IN_PROGRESS, DONE, DESTROYED;

  public static CaseState fromClass(String stateClass) {
    switch (stateClass.trim()) {
      case "running-case-state":
        return IN_PROGRESS;

      case "done-case-state":
        return DONE;

      case "destroyed-case-state":
        return DESTROYED;
      default:
        return CREATED;
    }
  }
}
