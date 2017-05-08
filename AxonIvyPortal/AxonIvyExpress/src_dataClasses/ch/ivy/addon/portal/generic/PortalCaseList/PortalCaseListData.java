package ch.ivy.addon.portal.generic.PortalCaseList;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class PortalCaseListData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class PortalCaseListData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -4963040727459951053L;

  /**
   * load all cases or just user session cases
   */
  private transient java.lang.Boolean loadAllCases;

  /**
   * Gets the field loadAllCases.
   * @return the value of the field loadAllCases; may be null.
   */
  public java.lang.Boolean getLoadAllCases()
  {
    return loadAllCases;
  }

  /**
   * Sets the field loadAllCases.
   * @param _loadAllCases the new value of the field loadAllCases.
   */
  public void setLoadAllCases(java.lang.Boolean _loadAllCases)
  {
    loadAllCases = _loadAllCases;
  }

  private org.primefaces.model.TreeNode userCaseTree;

  /**
   * Gets the field userCaseTree.
   * @return the value of the field userCaseTree; may be null.
   */
  public org.primefaces.model.TreeNode getUserCaseTree()
  {
    return userCaseTree;
  }

  /**
   * Sets the field userCaseTree.
   * @param _userCaseTree the new value of the field userCaseTree.
   */
  public void setUserCaseTree(org.primefaces.model.TreeNode _userCaseTree)
  {
    userCaseTree = _userCaseTree;
  }

  private java.lang.String homeLink;

  /**
   * Gets the field homeLink.
   * @return the value of the field homeLink; may be null.
   */
  public java.lang.String getHomeLink()
  {
    return homeLink;
  }

  /**
   * Sets the field homeLink.
   * @param _homeLink the new value of the field homeLink.
   */
  public void setHomeLink(java.lang.String _homeLink)
  {
    homeLink = _homeLink;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.CaseVO> selectedCases;

  /**
   * Gets the field selectedCases.
   * @return the value of the field selectedCases; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.CaseVO> getSelectedCases()
  {
    return selectedCases;
  }

  /**
   * Sets the field selectedCases.
   * @param _selectedCases the new value of the field selectedCases.
   */
  public void setSelectedCases(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.CaseVO> _selectedCases)
  {
    selectedCases = _selectedCases;
  }

  private org.primefaces.model.TreeNode adminCaseTree;

  /**
   * Gets the field adminCaseTree.
   * @return the value of the field adminCaseTree; may be null.
   */
  public org.primefaces.model.TreeNode getAdminCaseTree()
  {
    return adminCaseTree;
  }

  /**
   * Sets the field adminCaseTree.
   * @param _adminCaseTree the new value of the field adminCaseTree.
   */
  public void setAdminCaseTree(org.primefaces.model.TreeNode _adminCaseTree)
  {
    adminCaseTree = _adminCaseTree;
  }

  private java.lang.Boolean isAdmin;

  /**
   * Gets the field isAdmin.
   * @return the value of the field isAdmin; may be null.
   */
  public java.lang.Boolean getIsAdmin()
  {
    return isAdmin;
  }

  /**
   * Sets the field isAdmin.
   * @param _isAdmin the new value of the field isAdmin.
   */
  public void setIsAdmin(java.lang.Boolean _isAdmin)
  {
    isAdmin = _isAdmin;
  }

  private org.primefaces.model.LazyDataModel caseLazyDataModel;

  /**
   * Gets the field caseLazyDataModel.
   * @return the value of the field caseLazyDataModel; may be null.
   */
  public org.primefaces.model.LazyDataModel getCaseLazyDataModel()
  {
    return caseLazyDataModel;
  }

  /**
   * Sets the field caseLazyDataModel.
   * @param _caseLazyDataModel the new value of the field caseLazyDataModel.
   */
  public void setCaseLazyDataModel(org.primefaces.model.LazyDataModel _caseLazyDataModel)
  {
    caseLazyDataModel = _caseLazyDataModel;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> publicServiceIds;

  /**
   * Gets the field publicServiceIds.
   * @return the value of the field publicServiceIds; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getPublicServiceIds()
  {
    return publicServiceIds;
  }

  /**
   * Sets the field publicServiceIds.
   * @param _publicServiceIds the new value of the field publicServiceIds.
   */
  public void setPublicServiceIds(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _publicServiceIds)
  {
    publicServiceIds = _publicServiceIds;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.CaseVO> casesAfterFilter;

  /**
   * Gets the field casesAfterFilter.
   * @return the value of the field casesAfterFilter; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.CaseVO> getCasesAfterFilter()
  {
    return casesAfterFilter;
  }

  /**
   * Sets the field casesAfterFilter.
   * @param _casesAfterFilter the new value of the field casesAfterFilter.
   */
  public void setCasesAfterFilter(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.vo.CaseVO> _casesAfterFilter)
  {
    casesAfterFilter = _casesAfterFilter;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> publicServiceIdsAfterFilter;

  /**
   * Gets the field publicServiceIdsAfterFilter.
   * @return the value of the field publicServiceIdsAfterFilter; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getPublicServiceIdsAfterFilter()
  {
    return publicServiceIdsAfterFilter;
  }

  /**
   * Sets the field publicServiceIdsAfterFilter.
   * @param _publicServiceIdsAfterFilter the new value of the field publicServiceIdsAfterFilter.
   */
  public void setPublicServiceIdsAfterFilter(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _publicServiceIdsAfterFilter)
  {
    publicServiceIdsAfterFilter = _publicServiceIdsAfterFilter;
  }

  private java.lang.String endpoint;

  /**
   * Gets the field endpoint.
   * @return the value of the field endpoint; may be null.
   */
  public java.lang.String getEndpoint()
  {
    return endpoint;
  }

  /**
   * Sets the field endpoint.
   * @param _endpoint the new value of the field endpoint.
   */
  public void setEndpoint(java.lang.String _endpoint)
  {
    endpoint = _endpoint;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> publicServiceIdsUserCase;

  /**
   * Gets the field publicServiceIdsUserCase.
   * @return the value of the field publicServiceIdsUserCase; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getPublicServiceIdsUserCase()
  {
    return publicServiceIdsUserCase;
  }

  /**
   * Sets the field publicServiceIdsUserCase.
   * @param _publicServiceIdsUserCase the new value of the field publicServiceIdsUserCase.
   */
  public void setPublicServiceIdsUserCase(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _publicServiceIdsUserCase)
  {
    publicServiceIdsUserCase = _publicServiceIdsUserCase;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> publicServiceIdUserCaseAfterFilter;

  /**
   * Gets the field publicServiceIdUserCaseAfterFilter.
   * @return the value of the field publicServiceIdUserCaseAfterFilter; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getPublicServiceIdUserCaseAfterFilter()
  {
    return publicServiceIdUserCaseAfterFilter;
  }

  /**
   * Sets the field publicServiceIdUserCaseAfterFilter.
   * @param _publicServiceIdUserCaseAfterFilter the new value of the field publicServiceIdUserCaseAfterFilter.
   */
  public void setPublicServiceIdUserCaseAfterFilter(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _publicServiceIdUserCaseAfterFilter)
  {
    publicServiceIdUserCaseAfterFilter = _publicServiceIdUserCaseAfterFilter;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> userCasesAfterFilter;

  /**
   * Gets the field userCasesAfterFilter.
   * @return the value of the field userCasesAfterFilter; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> getUserCasesAfterFilter()
  {
    return userCasesAfterFilter;
  }

  /**
   * Sets the field userCasesAfterFilter.
   * @param _userCasesAfterFilter the new value of the field userCasesAfterFilter.
   */
  public void setUserCasesAfterFilter(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> _userCasesAfterFilter)
  {
    userCasesAfterFilter = _userCasesAfterFilter;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> userCasesTemp;

  /**
   * Gets the field userCasesTemp.
   * @return the value of the field userCasesTemp; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> getUserCasesTemp()
  {
    return userCasesTemp;
  }

  /**
   * Sets the field userCasesTemp.
   * @param _userCasesTemp the new value of the field userCasesTemp.
   */
  public void setUserCasesTemp(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> _userCasesTemp)
  {
    userCasesTemp = _userCasesTemp;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> publicServiceIdsAdminCase;

  /**
   * Gets the field publicServiceIdsAdminCase.
   * @return the value of the field publicServiceIdsAdminCase; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getPublicServiceIdsAdminCase()
  {
    return publicServiceIdsAdminCase;
  }

  /**
   * Sets the field publicServiceIdsAdminCase.
   * @param _publicServiceIdsAdminCase the new value of the field publicServiceIdsAdminCase.
   */
  public void setPublicServiceIdsAdminCase(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _publicServiceIdsAdminCase)
  {
    publicServiceIdsAdminCase = _publicServiceIdsAdminCase;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> publicServiceIdAdminCaseAfterFilter;

  /**
   * Gets the field publicServiceIdAdminCaseAfterFilter.
   * @return the value of the field publicServiceIdAdminCaseAfterFilter; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getPublicServiceIdAdminCaseAfterFilter()
  {
    return publicServiceIdAdminCaseAfterFilter;
  }

  /**
   * Sets the field publicServiceIdAdminCaseAfterFilter.
   * @param _publicServiceIdAdminCaseAfterFilter the new value of the field publicServiceIdAdminCaseAfterFilter.
   */
  public void setPublicServiceIdAdminCaseAfterFilter(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _publicServiceIdAdminCaseAfterFilter)
  {
    publicServiceIdAdminCaseAfterFilter = _publicServiceIdAdminCaseAfterFilter;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> adminCasesTemp;

  /**
   * Gets the field adminCasesTemp.
   * @return the value of the field adminCasesTemp; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> getAdminCasesTemp()
  {
    return adminCasesTemp;
  }

  /**
   * Sets the field adminCasesTemp.
   * @param _adminCasesTemp the new value of the field adminCasesTemp.
   */
  public void setAdminCasesTemp(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> _adminCasesTemp)
  {
    adminCasesTemp = _adminCasesTemp;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> adminCasesAfterFilter;

  /**
   * Gets the field adminCasesAfterFilter.
   * @return the value of the field adminCasesAfterFilter; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> getAdminCasesAfterFilter()
  {
    return adminCasesAfterFilter;
  }

  /**
   * Sets the field adminCasesAfterFilter.
   * @param _adminCasesAfterFilter the new value of the field adminCasesAfterFilter.
   */
  public void setAdminCasesAfterFilter(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.ICase> _adminCasesAfterFilter)
  {
    adminCasesAfterFilter = _adminCasesAfterFilter;
  }

  private java.lang.Boolean isFilterTaskByPublicService;

  /**
   * Gets the field isFilterTaskByPublicService.
   * @return the value of the field isFilterTaskByPublicService; may be null.
   */
  public java.lang.Boolean getIsFilterTaskByPublicService()
  {
    return isFilterTaskByPublicService;
  }

  /**
   * Sets the field isFilterTaskByPublicService.
   * @param _isFilterTaskByPublicService the new value of the field isFilterTaskByPublicService.
   */
  public void setIsFilterTaskByPublicService(java.lang.Boolean _isFilterTaskByPublicService)
  {
    isFilterTaskByPublicService = _isFilterTaskByPublicService;
  }

  private ch.ivy.addon.portal.generic.PortalListsConfig portalListsConfig;

  /**
   * Gets the field portalListsConfig.
   * @return the value of the field portalListsConfig; may be null.
   */
  public ch.ivy.addon.portal.generic.PortalListsConfig getPortalListsConfig()
  {
    return portalListsConfig;
  }

  /**
   * Sets the field portalListsConfig.
   * @param _portalListsConfig the new value of the field portalListsConfig.
   */
  public void setPortalListsConfig(ch.ivy.addon.portal.generic.PortalListsConfig _portalListsConfig)
  {
    portalListsConfig = _portalListsConfig;
  }

  private org.primefaces.model.TreeNode selectedCaseNode;

  /**
   * Gets the field selectedCaseNode.
   * @return the value of the field selectedCaseNode; may be null.
   */
  public org.primefaces.model.TreeNode getSelectedCaseNode()
  {
    return selectedCaseNode;
  }

  /**
   * Sets the field selectedCaseNode.
   * @param _selectedCaseNode the new value of the field selectedCaseNode.
   */
  public void setSelectedCaseNode(org.primefaces.model.TreeNode _selectedCaseNode)
  {
    selectedCaseNode = _selectedCaseNode;
  }

}
