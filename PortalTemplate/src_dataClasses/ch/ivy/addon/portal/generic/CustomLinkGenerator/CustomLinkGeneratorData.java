package ch.ivy.addon.portal.generic.CustomLinkGenerator;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CustomLinkGeneratorData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CustomLinkGeneratorData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 4036478403102480009L;

  private java.lang.String currentUser;

  /**
   * Gets the field currentUser.
   * @return the value of the field currentUser; may be null.
   */
  public java.lang.String getCurrentUser()
  {
    return currentUser;
  }

  /**
   * Sets the field currentUser.
   * @param _currentUser the new value of the field currentUser.
   */
  public void setCurrentUser(java.lang.String _currentUser)
  {
    currentUser = _currentUser;
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

  private transient java.lang.Boolean hasReadAllTasksPermisson;

  /**
   * Gets the field hasReadAllTasksPermisson.
   * @return the value of the field hasReadAllTasksPermisson; may be null.
   */
  public java.lang.Boolean getHasReadAllTasksPermisson()
  {
    return hasReadAllTasksPermisson;
  }

  /**
   * Sets the field hasReadAllTasksPermisson.
   * @param _hasReadAllTasksPermisson the new value of the field hasReadAllTasksPermisson.
   */
  public void setHasReadAllTasksPermisson(java.lang.Boolean _hasReadAllTasksPermisson)
  {
    hasReadAllTasksPermisson = _hasReadAllTasksPermisson;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portal.generic.CustomPortalLink> linkList;

  /**
   * Gets the field linkList.
   * @return the value of the field linkList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portal.generic.CustomPortalLink> getLinkList()
  {
    return linkList;
  }

  /**
   * Sets the field linkList.
   * @param _linkList the new value of the field linkList.
   */
  public void setLinkList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portal.generic.CustomPortalLink> _linkList)
  {
    linkList = _linkList;
  }

  private ch.ivy.addon.portal.generic.PortalConfig portalConfig;

  /**
   * Gets the field portalConfig.
   * @return the value of the field portalConfig; may be null.
   */
  public ch.ivy.addon.portal.generic.PortalConfig getPortalConfig()
  {
    return portalConfig;
  }

  /**
   * Sets the field portalConfig.
   * @param _portalConfig the new value of the field portalConfig.
   */
  public void setPortalConfig(ch.ivy.addon.portal.generic.PortalConfig _portalConfig)
  {
    portalConfig = _portalConfig;
  }

  private ch.ivy.addon.portalkit.bo.MainMenuNode selectedMenuItem;

  /**
   * Gets the field selectedMenuItem.
   * @return the value of the field selectedMenuItem; may be null.
   */
  public ch.ivy.addon.portalkit.bo.MainMenuNode getSelectedMenuItem()
  {
    return selectedMenuItem;
  }

  /**
   * Sets the field selectedMenuItem.
   * @param _selectedMenuItem the new value of the field selectedMenuItem.
   */
  public void setSelectedMenuItem(ch.ivy.addon.portalkit.bo.MainMenuNode _selectedMenuItem)
  {
    selectedMenuItem = _selectedMenuItem;
  }

  private org.primefaces.model.DefaultTreeNode selectedNode;

  /**
   * Gets the field selectedNode.
   * @return the value of the field selectedNode; may be null.
   */
  public org.primefaces.model.DefaultTreeNode getSelectedNode()
  {
    return selectedNode;
  }

  /**
   * Sets the field selectedNode.
   * @param _selectedNode the new value of the field selectedNode.
   */
  public void setSelectedNode(org.primefaces.model.DefaultTreeNode _selectedNode)
  {
    selectedNode = _selectedNode;
  }

  private java.lang.Boolean hasReadAllCasesPermission;

  /**
   * Gets the field hasReadAllCasesPermission.
   * @return the value of the field hasReadAllCasesPermission; may be null.
   */
  public java.lang.Boolean getHasReadAllCasesPermission()
  {
    return hasReadAllCasesPermission;
  }

  /**
   * Sets the field hasReadAllCasesPermission.
   * @param _hasReadAllCasesPermission the new value of the field hasReadAllCasesPermission.
   */
  public void setHasReadAllCasesPermission(java.lang.Boolean _hasReadAllCasesPermission)
  {
    hasReadAllCasesPermission = _hasReadAllCasesPermission;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> myTaskCategories;

  /**
   * Gets the field myTaskCategories.
   * @return the value of the field myTaskCategories; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getMyTaskCategories()
  {
    return myTaskCategories;
  }

  /**
   * Sets the field myTaskCategories.
   * @param _myTaskCategories the new value of the field myTaskCategories.
   */
  public void setMyTaskCategories(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _myTaskCategories)
  {
    myTaskCategories = _myTaskCategories;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> allTaskCategories;

  /**
   * Gets the field allTaskCategories.
   * @return the value of the field allTaskCategories; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getAllTaskCategories()
  {
    return allTaskCategories;
  }

  /**
   * Sets the field allTaskCategories.
   * @param _allTaskCategories the new value of the field allTaskCategories.
   */
  public void setAllTaskCategories(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _allTaskCategories)
  {
    allTaskCategories = _allTaskCategories;
  }

  private ch.ivy.addon.portal.generic.view.CaseView caseView;

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

  private ch.ivy.addon.portal.generic.view.TaskView taskView;

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
