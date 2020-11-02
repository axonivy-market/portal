package portal.guitest.common;

public enum TaskState {
  
  OPEN, IN_PROGRESS, RESERVED, DONE, DESTROYED;
  
  public static TaskState fromClass(String stateClass) {
    switch (stateClass.trim()) {
      case "task-state-open":
        return OPEN;

      case "task-state-in-progress":
        return IN_PROGRESS;
        
      case "task-state-reserved":
        return RESERVED;
        
      case "task-state-done":
        return DONE;

      case "task-state-zombie-destroyed":
        return DESTROYED;
      default:
        return OPEN;
    }
  }
}
