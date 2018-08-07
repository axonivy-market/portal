package ch.ivy.addon.portal.generic;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class PortalStartData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class PortalStartData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -6674081167102483990L;

  private transient ch.ivy.addon.portal.generic.navigation.PortalPage portalPage;

  /**
   * Gets the field portalPage.
   * @return the value of the field portalPage; may be null.
   */
  public ch.ivy.addon.portal.generic.navigation.PortalPage getPortalPage()
  {
    return portalPage;
  }

  /**
   * Sets the field portalPage.
   * @param _portalPage the new value of the field portalPage.
   */
  public void setPortalPage(ch.ivy.addon.portal.generic.navigation.PortalPage _portalPage)
  {
    portalPage = _portalPage;
  }

  private transient java.lang.String parameters;

  /**
   * Gets the field parameters.
   * @return the value of the field parameters; may be null.
   */
  public java.lang.String getParameters()
  {
    return parameters;
  }

  /**
   * Sets the field parameters.
   * @param _parameters the new value of the field parameters.
   */
  public void setParameters(java.lang.String _parameters)
  {
    parameters = _parameters;
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

}
