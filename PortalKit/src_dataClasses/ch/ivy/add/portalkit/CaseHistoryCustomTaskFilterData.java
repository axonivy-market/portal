package ch.ivy.add.portalkit;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseHistoryCustomTaskFilterData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseHistoryCustomTaskFilterData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -6263465238460335218L;

  private transient java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks;

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

}
