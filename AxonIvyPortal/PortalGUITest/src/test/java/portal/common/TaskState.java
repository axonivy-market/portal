package portal.common;

public enum TaskState {
  
  OPEN, IN_PROGRESS, RESERVED, DONE;
  
  public static TaskState fromClass(String stateClass) {
    switch (stateClass) {
      case "task-state-open":
        return OPEN;

      case "task-state-in-progress":
        return IN_PROGRESS;
        
      case "task-state-reserved":
        return RESERVED;
        
      case "task-state-done":
        return DONE;
        
      default:
        return OPEN;
    }
  }
}
