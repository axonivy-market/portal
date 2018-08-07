package ch.ivy.addon.portalkit.bo;

public class TaskNode extends MainMenuNode {

  private boolean isRootAllTask;
  private String category;

  public boolean isRootAllTask() {
    return isRootAllTask;
  }

  public void setRootAllTask(boolean isRootAllTask) {
    this.isRootAllTask = isRootAllTask;
  }

  public TaskNode() {}

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
