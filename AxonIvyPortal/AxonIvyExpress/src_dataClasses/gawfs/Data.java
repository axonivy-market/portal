package gawfs;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class Data", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class Data extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -5597036921740166333L;

  /**
   * Process Steps for ProcessChain
   */
  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> steps;

  /**
   * Gets the field steps.
   * @return the value of the field steps; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getSteps()
  {
    return steps;
  }

  /**
   * Sets the field steps.
   * @param _steps the new value of the field steps.
   */
  public void setSteps(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _steps)
  {
    steps = _steps;
  }

  /**
   * List of Tasks for execution
   */
  private ch.ivyteam.ivy.scripting.objects.List<gawfs.TaskDef> definedTasks;

  /**
   * Gets the field definedTasks.
   * @return the value of the field definedTasks; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<gawfs.TaskDef> getDefinedTasks()
  {
    return definedTasks;
  }

  /**
   * Sets the field definedTasks.
   * @param _definedTasks the new value of the field definedTasks.
   */
  public void setDefinedTasks(ch.ivyteam.ivy.scripting.objects.List<gawfs.TaskDef> _definedTasks)
  {
    definedTasks = _definedTasks;
  }

  /**
   * Name of the Process also later CaseName
   */
  private java.lang.String processName;

  /**
   * Gets the field processName.
   * @return the value of the field processName; may be null.
   */
  public java.lang.String getProcessName()
  {
    return processName;
  }

  /**
   * Sets the field processName.
   * @param _processName the new value of the field processName.
   */
  public void setProcessName(java.lang.String _processName)
  {
    processName = _processName;
  }

  /**
   * Description of the Process later CaseDescription
   */
  private java.lang.String processDescription;

  /**
   * Gets the field processDescription.
   * @return the value of the field processDescription; may be null.
   */
  public java.lang.String getProcessDescription()
  {
    return processDescription;
  }

  /**
   * Sets the field processDescription.
   * @param _processDescription the new value of the field processDescription.
   */
  public void setProcessDescription(java.lang.String _processDescription)
  {
    processDescription = _processDescription;
  }

  /**
   * Processtype
   */
  private java.lang.String processType;

  /**
   * Gets the field processType.
   * @return the value of the field processType; may be null.
   */
  public java.lang.String getProcessType()
  {
    return processType;
  }

  /**
   * Sets the field processType.
   * @param _processType the new value of the field processType.
   */
  public void setProcessType(java.lang.String _processType)
  {
    processType = _processType;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> wfusers;

  /**
   * Gets the field wfusers.
   * @return the value of the field wfusers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> getWfusers()
  {
    return wfusers;
  }

  /**
   * Sets the field wfusers.
   * @param _wfusers the new value of the field wfusers.
   */
  public void setWfusers(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.security.IUser> _wfusers)
  {
    wfusers = _wfusers;
  }

  private gawfs.Workflow processRepository;

  /**
   * Gets the field processRepository.
   * @return the value of the field processRepository; may be null.
   */
  public gawfs.Workflow getProcessRepository()
  {
    return processRepository;
  }

  /**
   * Sets the field processRepository.
   * @param _processRepository the new value of the field processRepository.
   */
  public void setProcessRepository(gawfs.Workflow _processRepository)
  {
    processRepository = _processRepository;
  }

  private gawfs.TaskDefp taskRepository;

  /**
   * Gets the field taskRepository.
   * @return the value of the field taskRepository; may be null.
   */
  public gawfs.TaskDefp getTaskRepository()
  {
    return taskRepository;
  }

  /**
   * Sets the field taskRepository.
   * @param _taskRepository the new value of the field taskRepository.
   */
  public void setTaskRepository(gawfs.TaskDefp _taskRepository)
  {
    taskRepository = _taskRepository;
  }

  private gawfs.Formelement formelementRepository;

  /**
   * Gets the field formelementRepository.
   * @return the value of the field formelementRepository; may be null.
   */
  public gawfs.Formelement getFormelementRepository()
  {
    return formelementRepository;
  }

  /**
   * Sets the field formelementRepository.
   * @param _formelementRepository the new value of the field formelementRepository.
   */
  public void setFormelementRepository(gawfs.Formelement _formelementRepository)
  {
    formelementRepository = _formelementRepository;
  }

  private de.eon.gawfs.DragAndDropController dragAndDropController;

  /**
   * Gets the field dragAndDropController.
   * @return the value of the field dragAndDropController; may be null.
   */
  public de.eon.gawfs.DragAndDropController getDragAndDropController()
  {
    return dragAndDropController;
  }

  /**
   * Sets the field dragAndDropController.
   * @param _dragAndDropController the new value of the field dragAndDropController.
   */
  public void setDragAndDropController(de.eon.gawfs.DragAndDropController _dragAndDropController)
  {
    dragAndDropController = _dragAndDropController;
  }

  private de.eon.gawfs.DynaFormController dynaFormController;

  /**
   * Gets the field dynaFormController.
   * @return the value of the field dynaFormController; may be null.
   */
  public de.eon.gawfs.DynaFormController getDynaFormController()
  {
    return dynaFormController;
  }

  /**
   * Sets the field dynaFormController.
   * @param _dynaFormController the new value of the field dynaFormController.
   */
  public void setDynaFormController(de.eon.gawfs.DynaFormController _dynaFormController)
  {
    dynaFormController = _dynaFormController;
  }

  private java.lang.Boolean backFlag;

  /**
   * Gets the field backFlag.
   * @return the value of the field backFlag; may be null.
   */
  public java.lang.Boolean getBackFlag()
  {
    return backFlag;
  }

  /**
   * Sets the field backFlag.
   * @param _backFlag the new value of the field backFlag.
   */
  public void setBackFlag(java.lang.Boolean _backFlag)
  {
    backFlag = _backFlag;
  }

  private java.lang.Integer processID;

  /**
   * Gets the field processID.
   * @return the value of the field processID; may be null.
   */
  public java.lang.Integer getProcessID()
  {
    return processID;
  }

  /**
   * Sets the field processID.
   * @param _processID the new value of the field processID.
   */
  public void setProcessID(java.lang.Integer _processID)
  {
    processID = _processID;
  }

  private java.lang.Boolean directExecutionFlag;

  /**
   * Gets the field directExecutionFlag.
   * @return the value of the field directExecutionFlag; may be null.
   */
  public java.lang.Boolean getDirectExecutionFlag()
  {
    return directExecutionFlag;
  }

  /**
   * Sets the field directExecutionFlag.
   * @param _directExecutionFlag the new value of the field directExecutionFlag.
   */
  public void setDirectExecutionFlag(java.lang.Boolean _directExecutionFlag)
  {
    directExecutionFlag = _directExecutionFlag;
  }

  /**
   * Taskid for Endpage
   */
  private java.lang.Number taskId;

  /**
   * Gets the field taskId.
   * @return the value of the field taskId; may be null.
   */
  public java.lang.Number getTaskId()
  {
    return taskId;
  }

  /**
   * Sets the field taskId.
   * @param _taskId the new value of the field taskId.
   */
  public void setTaskId(java.lang.Number _taskId)
  {
    taskId = _taskId;
  }

  private java.lang.Boolean discard;

  /**
   * Gets the field discard.
   * @return the value of the field discard; may be null.
   */
  public java.lang.Boolean getDiscard()
  {
    return discard;
  }

  /**
   * Sets the field discard.
   * @param _discard the new value of the field discard.
   */
  public void setDiscard(java.lang.Boolean _discard)
  {
    discard = _discard;
  }

}
