package ch.ivy.addon.portalkit.bo;

public class TaskNode extends MainMenuNode {

  private boolean isRootNodeAllTask;
  private String category;
  private boolean isFirstCategoryNode;
  
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

	public boolean isFirstCategoryNode() {
		return isFirstCategoryNode;
	}

	public void setFirstCategoryNode(boolean isFirstCategoryNode) {
		this.isFirstCategoryNode = isFirstCategoryNode;
	}
}
