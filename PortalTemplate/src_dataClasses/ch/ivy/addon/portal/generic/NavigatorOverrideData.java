package ch.ivy.addon.portal.generic;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class NavigatorOverrideData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class NavigatorOverrideData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 5731132404072017075L;

  private transient ch.ivy.addon.portalkit.dto.GlobalCaseId caseId;

  /**
   * Gets the field caseId.
   * @return the value of the field caseId; may be null.
   */
  public ch.ivy.addon.portalkit.dto.GlobalCaseId getCaseId()
  {
    return caseId;
  }

  /**
   * Sets the field caseId.
   * @param _caseId the new value of the field caseId.
   */
  public void setCaseId(ch.ivy.addon.portalkit.dto.GlobalCaseId _caseId)
  {
    caseId = _caseId;
  }

  private transient ch.ivy.addon.portal.generic.view.CaseView caseView;

  /**
   * Gets the field caseView.
   * @return the value of the field caseView; may be null.
   */
  public ch.ivy.addon.portal.generic.view.CaseView getCaseView()
  {
    return caseView;
  }

  /**
   * Sets the field caseView.
   * @param _caseView the new value of the field caseView.
   */
  public void setCaseView(ch.ivy.addon.portal.generic.view.CaseView _caseView)
  {
    caseView = _caseView;
  }

  private transient java.lang.String caseName;

  /**
   * Gets the field caseName.
   * @return the value of the field caseName; may be null.
   */
  public java.lang.String getCaseName()
  {
    return caseName;
  }

  /**
   * Sets the field caseName.
   * @param _caseName the new value of the field caseName.
   */
  public void setCaseName(java.lang.String _caseName)
  {
    caseName = _caseName;
  }

  private transient ch.ivy.addon.portal.generic.view.TaskView taskView;

  /**
   * Gets the field taskView.
   * @return the value of the field taskView; may be null.
   */
  public ch.ivy.addon.portal.generic.view.TaskView getTaskView()
  {
    return taskView;
  }

  /**
   * Sets the field taskView.
   * @param _taskView the new value of the field taskView.
   */
  public void setTaskView(ch.ivy.addon.portal.generic.view.TaskView _taskView)
  {
    taskView = _taskView;
  }

  private transient java.lang.Long taskId;

  /**
   * Gets the field taskId.
   * @return the value of the field taskId; may be null.
   */
  public java.lang.Long getTaskId()
  {
    return taskId;
  }

  /**
   * Sets the field taskId.
   * @param _taskId the new value of the field taskId.
   */
  public void setTaskId(java.lang.Long _taskId)
  {
    taskId = _taskId;
  }

  private transient java.lang.String thirdPartyAppName;

  /**
   * Gets the field thirdPartyAppName.
   * @return the value of the field thirdPartyAppName; may be null.
   */
  public java.lang.String getThirdPartyAppName()
  {
    return thirdPartyAppName;
  }

  /**
   * Sets the field thirdPartyAppName.
   * @param _thirdPartyAppName the new value of the field thirdPartyAppName.
   */
  public void setThirdPartyAppName(java.lang.String _thirdPartyAppName)
  {
    thirdPartyAppName = _thirdPartyAppName;
  }

  private transient java.lang.String thirdPartyAppUrl;

  /**
   * Gets the field thirdPartyAppUrl.
   * @return the value of the field thirdPartyAppUrl; may be null.
   */
  public java.lang.String getThirdPartyAppUrl()
  {
    return thirdPartyAppUrl;
  }

  /**
   * Sets the field thirdPartyAppUrl.
   * @param _thirdPartyAppUrl the new value of the field thirdPartyAppUrl.
   */
  public void setThirdPartyAppUrl(java.lang.String _thirdPartyAppUrl)
  {
    thirdPartyAppUrl = _thirdPartyAppUrl;
  }

}
