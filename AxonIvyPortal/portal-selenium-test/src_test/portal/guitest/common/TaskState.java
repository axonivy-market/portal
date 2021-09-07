package portal.guitest.common;

public enum TaskState {
  
  OPEN, IN_PROGRESS, RESERVED, DONE, DESTROYED, DELAYED, SUSPENDED;
  
  public static TaskState fromClass(String stateClass) {
    switch (stateClass.trim()) {
      case "suspended-task-state":
        return SUSPENDED;

      case "resumed-task-state":
        return IN_PROGRESS;
        
      case "parked-task-state":
        return RESERVED;
        
      case "done-task-state":
        return DONE;

      case "destroyed-task-state":
        return DESTROYED;
        
      case "delayed-task-state":
        return DELAYED;
      default:
        return OPEN;
    }
  }
}
