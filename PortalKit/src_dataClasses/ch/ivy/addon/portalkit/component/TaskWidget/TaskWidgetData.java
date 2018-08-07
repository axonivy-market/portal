package ch.ivy.addon.portalkit.component.TaskWidget;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskWidgetData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskWidgetData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 1348788786586065755L;

  private java.lang.Boolean compactMode;

  /**
   * Gets the field compactMode.
   * @return the value of the field compactMode; may be null.
   */
  public java.lang.Boolean getCompactMode()
  {
    return compactMode;
  }

  /**
   * Sets the field compactMode.
   * @param _compactMode the new value of the field compactMode.
   */
  public void setCompactMode(java.lang.Boolean _compactMode)
  {
    compactMode = _compactMode;
  }

  private java.lang.Integer count;

  /**
   * Gets the field count.
   * @return the value of the field count; may be null.
   */
  public java.lang.Integer getCount()
  {
    return count;
  }

  /**
   * Sets the field count.
   * @param _count the new value of the field count.
   */
  public void setCount(java.lang.Integer _count)
  {
    count = _count;
  }

  private ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel;

  /**
   * Gets the field dataModel.
   * @return the value of the field dataModel; may be null.
   */
  public ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel getDataModel()
  {
    return dataModel;
  }

  /**
   * Sets the field dataModel.
   * @param _dataModel the new value of the field dataModel.
   */
  public void setDataModel(ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel _dataModel)
  {
    dataModel = _dataModel;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WsException> _errors)
  {
    errors = _errors;
  }

  private java.lang.String keyword;

  /**
   * Gets the field keyword.
   * @return the value of the field keyword; may be null.
   */
  public java.lang.String getKeyword()
  {
    return keyword;
  }

  /**
   * Sets the field keyword.
   * @param _keyword the new value of the field keyword.
   */
  public void setKeyword(java.lang.String _keyword)
  {
    keyword = _keyword;
  }

  private java.lang.Boolean modeInitialized;

  /**
   * Gets the field modeInitialized.
   * @return the value of the field modeInitialized; may be null.
   */
  public java.lang.Boolean getModeInitialized()
  {
    return modeInitialized;
  }

  /**
   * Sets the field modeInitialized.
   * @param _modeInitialized the new value of the field modeInitialized.
   */
  public void setModeInitialized(java.lang.Boolean _modeInitialized)
  {
    modeInitialized = _modeInitialized;
  }

  private java.lang.Integer startIndex;

  /**
   * Gets the field startIndex.
   * @return the value of the field startIndex; may be null.
   */
  public java.lang.Integer getStartIndex()
  {
    return startIndex;
  }

  /**
   * Sets the field startIndex.
   * @param _startIndex the new value of the field startIndex.
   */
  public void setStartIndex(java.lang.Integer _startIndex)
  {
    startIndex = _startIndex;
  }

  private java.lang.Long taskCount;

  /**
   * Gets the field taskCount.
   * @return the value of the field taskCount; may be null.
   */
  public java.lang.Long getTaskCount()
  {
    return taskCount;
  }

  /**
   * Sets the field taskCount.
   * @param _taskCount the new value of the field taskCount.
   */
  public void setTaskCount(java.lang.Long _taskCount)
  {
    taskCount = _taskCount;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteTask> _tasks)
  {
    tasks = _tasks;
  }

  private ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria;

  /**
   * Gets the field taskSearchCriteria.
   * @return the value of the field taskSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.TaskSearchCriteria getTaskSearchCriteria()
  {
    return taskSearchCriteria;
  }

  /**
   * Sets the field taskSearchCriteria.
   * @param _taskSearchCriteria the new value of the field taskSearchCriteria.
   */
  public void setTaskSearchCriteria(ch.ivy.ws.addon.TaskSearchCriteria _taskSearchCriteria)
  {
    taskSearchCriteria = _taskSearchCriteria;
  }

}
