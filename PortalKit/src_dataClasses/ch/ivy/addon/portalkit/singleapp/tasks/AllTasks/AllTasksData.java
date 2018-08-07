package ch.ivy.addon.portalkit.singleapp.tasks.AllTasks;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AllTasksData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AllTasksData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3772062421344612234L;

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

  private ch.ivyteam.ivy.workflow.ITask selectedTask;

  /**
   * Gets the field selectedTask.
   * @return the value of the field selectedTask; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getSelectedTask()
  {
    return selectedTask;
  }

  /**
   * Sets the field selectedTask.
   * @param _selectedTask the new value of the field selectedTask.
   */
  public void setSelectedTask(ch.ivyteam.ivy.workflow.ITask _selectedTask)
  {
    selectedTask = _selectedTask;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.add.portalkit.admin.Task> filteredTasks;

  /**
   * Gets the field filteredTasks.
   * @return the value of the field filteredTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.add.portalkit.admin.Task> getFilteredTasks()
  {
    return filteredTasks;
  }

  /**
   * Sets the field filteredTasks.
   * @param _filteredTasks the new value of the field filteredTasks.
   */
  public void setFilteredTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.add.portalkit.admin.Task> _filteredTasks)
  {
    filteredTasks = _filteredTasks;
  }

  private ch.ivyteam.ivy.workflow.IPropertyFilter propertierFilter;

  /**
   * Gets the field propertierFilter.
   * @return the value of the field propertierFilter; may be null.
   */
  public ch.ivyteam.ivy.workflow.IPropertyFilter getPropertierFilter()
  {
    return propertierFilter;
  }

  /**
   * Sets the field propertierFilter.
   * @param _propertierFilter the new value of the field propertierFilter.
   */
  public void setPropertierFilter(ch.ivyteam.ivy.workflow.IPropertyFilter _propertierFilter)
  {
    propertierFilter = _propertierFilter;
  }

  private java.lang.Boolean isFilterMode;

  /**
   * Gets the field isFilterMode.
   * @return the value of the field isFilterMode; may be null.
   */
  public java.lang.Boolean getIsFilterMode()
  {
    return isFilterMode;
  }

  /**
   * Sets the field isFilterMode.
   * @param _isFilterMode the new value of the field isFilterMode.
   */
  public void setIsFilterMode(java.lang.Boolean _isFilterMode)
  {
    isFilterMode = _isFilterMode;
  }

}
