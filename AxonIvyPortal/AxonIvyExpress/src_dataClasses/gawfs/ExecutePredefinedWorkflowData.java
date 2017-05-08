package gawfs;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class ExecutePredefinedWorkflowData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class ExecutePredefinedWorkflowData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4470612805946409196L;

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

  private java.lang.Integer actualStepIndex;

  /**
   * Gets the field actualStepIndex.
   * @return the value of the field actualStepIndex; may be null.
   */
  public java.lang.Integer getActualStepIndex()
  {
    return actualStepIndex;
  }

  /**
   * Sets the field actualStepIndex.
   * @param _actualStepIndex the new value of the field actualStepIndex.
   */
  public void setActualStepIndex(java.lang.Integer _actualStepIndex)
  {
    actualStepIndex = _actualStepIndex;
  }

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

  private java.lang.Integer workflowID;

  /**
   * Gets the field workflowID.
   * @return the value of the field workflowID; may be null.
   */
  public java.lang.Integer getWorkflowID()
  {
    return workflowID;
  }

  /**
   * Sets the field workflowID.
   * @param _workflowID the new value of the field workflowID.
   */
  public void setWorkflowID(java.lang.Integer _workflowID)
  {
    workflowID = _workflowID;
  }

  private java.lang.String workflowName;

  /**
   * Gets the field workflowName.
   * @return the value of the field workflowName; may be null.
   */
  public java.lang.String getWorkflowName()
  {
    return workflowName;
  }

  /**
   * Sets the field workflowName.
   * @param _workflowName the new value of the field workflowName.
   */
  public void setWorkflowName(java.lang.String _workflowName)
  {
    workflowName = _workflowName;
  }

  private java.lang.String workflowDescription;

  /**
   * Gets the field workflowDescription.
   * @return the value of the field workflowDescription; may be null.
   */
  public java.lang.String getWorkflowDescription()
  {
    return workflowDescription;
  }

  /**
   * Sets the field workflowDescription.
   * @param _workflowDescription the new value of the field workflowDescription.
   */
  public void setWorkflowDescription(java.lang.String _workflowDescription)
  {
    workflowDescription = _workflowDescription;
  }

  private java.lang.String workflowType;

  /**
   * Gets the field workflowType.
   * @return the value of the field workflowType; may be null.
   */
  public java.lang.String getWorkflowType()
  {
    return workflowType;
  }

  /**
   * Sets the field workflowType.
   * @param _workflowType the new value of the field workflowType.
   */
  public void setWorkflowType(java.lang.String _workflowType)
  {
    workflowType = _workflowType;
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

  private gawfs.TaskDef nextTask;

  /**
   * Gets the field nextTask.
   * @return the value of the field nextTask; may be null.
   */
  public gawfs.TaskDef getNextTask()
  {
    return nextTask;
  }

  /**
   * Sets the field nextTask.
   * @param _nextTask the new value of the field nextTask.
   */
  public void setNextTask(gawfs.TaskDef _nextTask)
  {
    nextTask = _nextTask;
  }

  private gawfs.Applicant applicant;

  /**
   * Gets the field applicant.
   * @return the value of the field applicant; may be null.
   */
  public gawfs.Applicant getApplicant()
  {
    return applicant;
  }

  /**
   * Sets the field applicant.
   * @param _applicant the new value of the field applicant.
   */
  public void setApplicant(gawfs.Applicant _applicant)
  {
    applicant = _applicant;
  }

  private java.lang.Boolean denied;

  /**
   * Gets the field denied.
   * @return the value of the field denied; may be null.
   */
  public java.lang.Boolean getDenied()
  {
    return denied;
  }

  /**
   * Sets the field denied.
   * @param _denied the new value of the field denied.
   */
  public void setDenied(java.lang.Boolean _denied)
  {
    denied = _denied;
  }

  private ch.ivyteam.ivy.security.IUser creatorUser;

  /**
   * Gets the field creatorUser.
   * @return the value of the field creatorUser; may be null.
   */
  public ch.ivyteam.ivy.security.IUser getCreatorUser()
  {
    return creatorUser;
  }

  /**
   * Sets the field creatorUser.
   * @param _creatorUser the new value of the field creatorUser.
   */
  public void setCreatorUser(ch.ivyteam.ivy.security.IUser _creatorUser)
  {
    creatorUser = _creatorUser;
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
