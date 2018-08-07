package ch.ivy.addon.portalkit.bo;

public class TaskNode extends MainMenuNode {

  private boolean isRootNodeAllTask;
  private String category;

  public boolean isRootNodeAllTask() {
    return isRootNodeAllTask;
  }

  public void setRootNodeAllTask(boolean isRootNodeAllTask) {
    this.isRootNodeAllTask = isRootNodeAllTask;
  }

  public TaskNode() {}

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
