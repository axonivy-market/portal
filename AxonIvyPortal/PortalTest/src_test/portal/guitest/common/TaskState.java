package portal.guitest.common;

public enum TaskState {
  
  OPEN, IN_PROGRESS, DONE, DESTROYED, DELAYED, ERROR;
  
  public static TaskState fromClass(String stateClass) {
    switch (stateClass.trim()) {
      case "in_progress-task-state":
        return IN_PROGRESS;
        
      case "error-task-state":
        return ERROR;
        
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
