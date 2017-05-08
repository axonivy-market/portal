package ch.ivy.addon.portal.generic.PortalTaskList;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class PortalTaskListData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class PortalTaskListData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 6335878020391603430L;

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.CustomTask> customTasks;

  /**
   * Gets the field customTasks.
   * @return the value of the field customTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.CustomTask> getCustomTasks()
  {
    return customTasks;
  }

  /**
   * Sets the field customTasks.
   * @param _customTasks the new value of the field customTasks.
   */
  public void setCustomTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.CustomTask> _customTasks)
  {
    customTasks = _customTasks;
  }

  private ch.ivy.addon.portalkit.bo.CustomTask selectedTask;

  /**
   * Gets the field selectedTask.
   * @return the value of the field selectedTask; may be null.
   */
  public ch.ivy.addon.portalkit.bo.CustomTask getSelectedTask()
  {
    return selectedTask;
  }

  /**
   * Sets the field selectedTask.
   * @param _selectedTask the new value of the field selectedTask.
   */
  public void setSelectedTask(ch.ivy.addon.portalkit.bo.CustomTask _selectedTask)
  {
    selectedTask = _selectedTask;
  }

  private boolean taskInformationVisible;

  /**
   * Gets the field taskInformationVisible.
   * @return the value of the field taskInformationVisible; may be null.
   */
  public boolean getTaskInformationVisible()
  {
    return taskInformationVisible;
  }

  /**
   * Sets the field taskInformationVisible.
   * @param _taskInformationVisible the new value of the field taskInformationVisible.
   */
  public void setTaskInformationVisible(boolean _taskInformationVisible)
  {
    taskInformationVisible = _taskInformationVisible;
  }

  private ch.ivyteam.ivy.workflow.ITask startTask;

  /**
   * Gets the field startTask.
   * @return the value of the field startTask; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getStartTask()
  {
    return startTask;
  }

  /**
   * Sets the field startTask.
   * @param _startTask the new value of the field startTask.
   */
  public void setStartTask(ch.ivyteam.ivy.workflow.ITask _startTask)
  {
    startTask = _startTask;
  }

  private java.lang.Number startTaskId;

  /**
   * Gets the field startTaskId.
   * @return the value of the field startTaskId; may be null.
   */
  public java.lang.Number getStartTaskId()
  {
    return startTaskId;
  }

  /**
   * Sets the field startTaskId.
   * @param _startTaskId the new value of the field startTaskId.
   */
  public void setStartTaskId(java.lang.Number _startTaskId)
  {
    startTaskId = _startTaskId;
  }

  private org.primefaces.model.TreeNode taskTree;

  /**
   * Gets the field taskTree.
   * @return the value of the field taskTree; may be null.
   */
  public org.primefaces.model.TreeNode getTaskTree()
  {
    return taskTree;
  }

  /**
   * Sets the field taskTree.
   * @param _taskTree the new value of the field taskTree.
   */
  public void setTaskTree(org.primefaces.model.TreeNode _taskTree)
  {
    taskTree = _taskTree;
  }

  private org.primefaces.model.TreeNode selectedTaskTreeNode;

  /**
   * Gets the field selectedTaskTreeNode.
   * @return the value of the field selectedTaskTreeNode; may be null.
   */
  public org.primefaces.model.TreeNode getSelectedTaskTreeNode()
  {
    return selectedTaskTreeNode;
  }

  /**
   * Sets the field selectedTaskTreeNode.
   * @param _selectedTaskTreeNode the new value of the field selectedTaskTreeNode.
   */
  public void setSelectedTaskTreeNode(org.primefaces.model.TreeNode _selectedTaskTreeNode)
  {
    selectedTaskTreeNode = _selectedTaskTreeNode;
  }

  private java.lang.String homeLink;

  /**
   * Gets the field homeLink.
   * @return the value of the field homeLink; may be null.
   */
  public java.lang.String getHomeLink()
  {
    return homeLink;
  }

  /**
   * Sets the field homeLink.
   * @param _homeLink the new value of the field homeLink.
   */
  public void setHomeLink(java.lang.String _homeLink)
  {
    homeLink = _homeLink;
  }

  private transient boolean taskNotFound;

  /**
   * Gets the field taskNotFound.
   * @return the value of the field taskNotFound; may be null.
   */
  public boolean getTaskNotFound()
  {
    return taskNotFound;
  }

  /**
   * Sets the field taskNotFound.
   * @param _taskNotFound the new value of the field taskNotFound.
   */
  public void setTaskNotFound(boolean _taskNotFound)
  {
    taskNotFound = _taskNotFound;
  }

  private transient boolean isTaskSearchMode;

  /**
   * Gets the field isTaskSearchMode.
   * @return the value of the field isTaskSearchMode; may be null.
   */
  public boolean getIsTaskSearchMode()
  {
    return isTaskSearchMode;
  }

  /**
   * Sets the field isTaskSearchMode.
   * @param _isTaskSearchMode the new value of the field isTaskSearchMode.
   */
  public void setIsTaskSearchMode(boolean _isTaskSearchMode)
  {
    isTaskSearchMode = _isTaskSearchMode;
  }

  private org.primefaces.model.TreeNode adminTaskTree;

  /**
   * Gets the field adminTaskTree.
   * @return the value of the field adminTaskTree; may be null.
   */
  public org.primefaces.model.TreeNode getAdminTaskTree()
  {
    return adminTaskTree;
  }

  /**
   * Sets the field adminTaskTree.
   * @param _adminTaskTree the new value of the field adminTaskTree.
   */
  public void setAdminTaskTree(org.primefaces.model.TreeNode _adminTaskTree)
  {
    adminTaskTree = _adminTaskTree;
  }

  private java.lang.Boolean hasAdministratorWorkflowPermisson;

  /**
   * Gets the field hasAdministratorWorkflowPermisson.
   * @return the value of the field hasAdministratorWorkflowPermisson; may be null.
   */
  public java.lang.Boolean getHasAdministratorWorkflowPermisson()
  {
    return hasAdministratorWorkflowPermisson;
  }

  /**
   * Sets the field hasAdministratorWorkflowPermisson.
   * @param _hasAdministratorWorkflowPermisson the new value of the field hasAdministratorWorkflowPermisson.
   */
  public void setHasAdministratorWorkflowPermisson(java.lang.Boolean _hasAdministratorWorkflowPermisson)
  {
    hasAdministratorWorkflowPermisson = _hasAdministratorWorkflowPermisson;
  }

  private org.primefaces.model.LazyDataModel taskLazyDataModel;

  /**
   * Gets the field taskLazyDataModel.
   * @return the value of the field taskLazyDataModel; may be null.
   */
  public org.primefaces.model.LazyDataModel getTaskLazyDataModel()
  {
    return taskLazyDataModel;
  }

  /**
   * Sets the field taskLazyDataModel.
   * @param _taskLazyDataModel the new value of the field taskLazyDataModel.
   */
  public void setTaskLazyDataModel(org.primefaces.model.LazyDataModel _taskLazyDataModel)
  {
    taskLazyDataModel = _taskLazyDataModel;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.add.portalkit.admin.Task> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.add.portalkit.admin.Task> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.add.portalkit.admin.Task> _tasks)
  {
    tasks = _tasks;
  }

  private ch.ivy.addon.portal.generic.PortalListsConfig portalListsConfig;

  /**
   * Gets the field portalListsConfig.
   * @return the value of the field portalListsConfig; may be null.
   */
  public ch.ivy.addon.portal.generic.PortalListsConfig getPortalListsConfig()
  {
    return portalListsConfig;
  }

  /**
   * Sets the field portalListsConfig.
   * @param _portalListsConfig the new value of the field portalListsConfig.
   */
  public void setPortalListsConfig(ch.ivy.addon.portal.generic.PortalListsConfig _portalListsConfig)
  {
    portalListsConfig = _portalListsConfig;
  }

}
