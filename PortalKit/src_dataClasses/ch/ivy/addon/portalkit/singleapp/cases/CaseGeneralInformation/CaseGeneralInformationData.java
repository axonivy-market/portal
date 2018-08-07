package ch.ivy.addon.portalkit.singleapp.cases.CaseGeneralInformation;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseGeneralInformationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseGeneralInformationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 5528316285585808248L;

  private java.lang.Number caseId;

  /**
   * Gets the field caseId.
   * @return the value of the field caseId; may be null.
   */
  public java.lang.Number getCaseId()
  {
    return caseId;
  }

  /**
   * Sets the field caseId.
   * @param _caseId the new value of the field caseId.
   */
  public void setCaseId(java.lang.Number _caseId)
  {
    caseId = _caseId;
  }

  private ch.ivyteam.ivy.workflow.ICase caseInfo;

  /**
   * Gets the field caseInfo.
   * @return the value of the field caseInfo; may be null.
   */
  public ch.ivyteam.ivy.workflow.ICase getCaseInfo()
  {
    return caseInfo;
  }

  /**
   * Sets the field caseInfo.
   * @param _caseInfo the new value of the field caseInfo.
   */
  public void setCaseInfo(ch.ivyteam.ivy.workflow.ICase _caseInfo)
  {
    caseInfo = _caseInfo;
  }

  private java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> tasks;

  /**
   * Gets the field tasks.
   * @return the value of the field tasks; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> getTasks()
  {
    return tasks;
  }

  /**
   * Sets the field tasks.
   * @param _tasks the new value of the field tasks.
   */
  public void setTasks(java.util.List<ch.ivy.addon.portalkit.vo.TaskVO> _tasks)
  {
    tasks = _tasks;
  }

  private ch.ivy.addon.portalkit.bo.Contact contact;

  /**
   * Gets the field contact.
   * @return the value of the field contact; may be null.
   */
  public ch.ivy.addon.portalkit.bo.Contact getContact()
  {
    return contact;
  }

  /**
   * Sets the field contact.
   * @param _contact the new value of the field contact.
   */
  public void setContact(ch.ivy.addon.portalkit.bo.Contact _contact)
  {
    contact = _contact;
  }

  private java.lang.String creatorName;

  /**
   * Gets the field creatorName.
   * @return the value of the field creatorName; may be null.
   */
  public java.lang.String getCreatorName()
  {
    return creatorName;
  }

  /**
   * Sets the field creatorName.
   * @param _creatorName the new value of the field creatorName.
   */
  public void setCreatorName(java.lang.String _creatorName)
  {
    creatorName = _creatorName;
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

}
