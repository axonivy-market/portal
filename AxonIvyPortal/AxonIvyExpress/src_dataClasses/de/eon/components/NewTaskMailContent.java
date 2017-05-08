package de.eon.components;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class NewTaskMailContent", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class NewTaskMailContent extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 9129012364612175415L;

  private ch.ivyteam.ivy.security.IUser user;

  /**
   * Gets the field user.
   * @return the value of the field user; may be null.
   */
  public ch.ivyteam.ivy.security.IUser getUser()
  {
    return user;
  }

  /**
   * Sets the field user.
   * @param _user the new value of the field user.
   */
  public void setUser(ch.ivyteam.ivy.security.IUser _user)
  {
    user = _user;
  }

  private ch.ivyteam.ivy.workflow.ITask task;

  /**
   * Gets the field task.
   * @return the value of the field task; may be null.
   */
  public ch.ivyteam.ivy.workflow.ITask getTask()
  {
    return task;
  }

  /**
   * Sets the field task.
   * @param _task the new value of the field task.
   */
  public void setTask(ch.ivyteam.ivy.workflow.ITask _task)
  {
    task = _task;
  }

  private java.lang.String taskStartLink;

  /**
   * Gets the field taskStartLink.
   * @return the value of the field taskStartLink; may be null.
   */
  public java.lang.String getTaskStartLink()
  {
    return taskStartLink;
  }

  /**
   * Sets the field taskStartLink.
   * @param _taskStartLink the new value of the field taskStartLink.
   */
  public void setTaskStartLink(java.lang.String _taskStartLink)
  {
    taskStartLink = _taskStartLink;
  }

  private java.lang.Number userIdForMail;

  /**
   * Gets the field userIdForMail.
   * @return the value of the field userIdForMail; may be null.
   */
  public java.lang.Number getUserIdForMail()
  {
    return userIdForMail;
  }

  /**
   * Sets the field userIdForMail.
   * @param _userIdForMail the new value of the field userIdForMail.
   */
  public void setUserIdForMail(java.lang.Number _userIdForMail)
  {
    userIdForMail = _userIdForMail;
  }

  private ch.ivyteam.ivy.scripting.objects.List<de.eon.components.bo.TaskForMail> tasksForUser;

  /**
   * Gets the field tasksForUser.
   * @return the value of the field tasksForUser; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<de.eon.components.bo.TaskForMail> getTasksForUser()
  {
    return tasksForUser;
  }

  /**
   * Sets the field tasksForUser.
   * @param _tasksForUser the new value of the field tasksForUser.
   */
  public void setTasksForUser(ch.ivyteam.ivy.scripting.objects.List<de.eon.components.bo.TaskForMail> _tasksForUser)
  {
    tasksForUser = _tasksForUser;
  }

}
