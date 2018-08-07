package portalKit_test;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseHistoryCustomTaskFilterOverrideData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseHistoryCustomTaskFilterOverrideData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -5920167687468152184L;

  private java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public java.util.List<ch.ivyteam.ivy.workflow.ITask> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(java.util.List<ch.ivyteam.ivy.workflow.ITask> _tasks)
  {
    tasks = _tasks;
  }

  private java.util.List<ch.ivyteam.ivy.workflow.ITask> filterdTasks;

  /**
   * Gets the field filterdTasks.
   * @return the value of the field filterdTasks; may be null.
   */
  public java.util.List<ch.ivyteam.ivy.workflow.ITask> getFilterdTasks()
  {
    return filterdTasks;
  }

  /**
   * Sets the field filterdTasks.
   * @param _filterdTasks the new value of the field filterdTasks.
   */
  public void setFilterdTasks(java.util.List<ch.ivyteam.ivy.workflow.ITask> _filterdTasks)
  {
    filterdTasks = _filterdTasks;
  }

}
