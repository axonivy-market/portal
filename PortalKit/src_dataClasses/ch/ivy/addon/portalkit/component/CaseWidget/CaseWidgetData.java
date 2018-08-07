package ch.ivy.addon.portalkit.component.CaseWidget;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseWidgetData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseWidgetData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -1486816000538444537L;

  private java.lang.Long caseId;

  /**
   * Gets the field caseId.
   * @return the value of the field caseId; may be null.
   */
  public java.lang.Long getCaseId()
  {
    return caseId;
  }

  /**
   * Sets the field caseId.
   * @param _caseId the new value of the field caseId.
   */
  public void setCaseId(java.lang.Long _caseId)
  {
    caseId = _caseId;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> cases;

  /**
   * Gets the field cases.
   * @return the value of the field cases; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> getCases()
  {
    return cases;
  }

  /**
   * Sets the field cases.
   * @param _cases the new value of the field cases.
   */
  public void setCases(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> _cases)
  {
    cases = _cases;
  }

  private java.lang.String filteringKeyword;

  /**
   * Gets the field filteringKeyword.
   * @return the value of the field filteringKeyword; may be null.
   */
  public java.lang.String getFilteringKeyword()
  {
    return filteringKeyword;
  }

  /**
   * Sets the field filteringKeyword.
   * @param _filteringKeyword the new value of the field filteringKeyword.
   */
  public void setFilteringKeyword(java.lang.String _filteringKeyword)
  {
    filteringKeyword = _filteringKeyword;
  }

  private ch.ivy.addon.portalkit.bo.RemoteCase selectedCase;

  /**
   * Gets the field selectedCase.
   * @return the value of the field selectedCase; may be null.
   */
  public ch.ivy.addon.portalkit.bo.RemoteCase getSelectedCase()
  {
    return selectedCase;
  }

  /**
   * Sets the field selectedCase.
   * @param _selectedCase the new value of the field selectedCase.
   */
  public void setSelectedCase(ch.ivy.addon.portalkit.bo.RemoteCase _selectedCase)
  {
    selectedCase = _selectedCase;
  }

  private java.lang.Long serverId;

  /**
   * Gets the field serverId.
   * @return the value of the field serverId; may be null.
   */
  public java.lang.Long getServerId()
  {
    return serverId;
  }

  /**
   * Sets the field serverId.
   * @param _serverId the new value of the field serverId.
   */
  public void setServerId(java.lang.Long _serverId)
  {
    serverId = _serverId;
  }

  private ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel dataModel;

  /**
   * Gets the field dataModel.
   * @return the value of the field dataModel; may be null.
   */
  public ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel getDataModel()
  {
    return dataModel;
  }

  /**
   * Sets the field dataModel.
   * @param _dataModel the new value of the field dataModel.
   */
  public void setDataModel(ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel _dataModel)
  {
    dataModel = _dataModel;
  }

  private java.lang.Integer caseCount;

  /**
   * Gets the field caseCount.
   * @return the value of the field caseCount; may be null.
   */
  public java.lang.Integer getCaseCount()
  {
    return caseCount;
  }

  /**
   * Sets the field caseCount.
   * @param _caseCount the new value of the field caseCount.
   */
  public void setCaseCount(java.lang.Integer _caseCount)
  {
    caseCount = _caseCount;
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

  private ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria;

  /**
   * Gets the field caseSearchCriteria.
   * @return the value of the field caseSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.CaseSearchCriteria getCaseSearchCriteria()
  {
    return caseSearchCriteria;
  }

  /**
   * Sets the field caseSearchCriteria.
   * @param _caseSearchCriteria the new value of the field caseSearchCriteria.
   */
  public void setCaseSearchCriteria(ch.ivy.ws.addon.CaseSearchCriteria _caseSearchCriteria)
  {
    caseSearchCriteria = _caseSearchCriteria;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> filteredCases;

  /**
   * Gets the field filteredCases.
   * @return the value of the field filteredCases; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> getFilteredCases()
  {
    return filteredCases;
  }

  /**
   * Sets the field filteredCases.
   * @param _filteredCases the new value of the field filteredCases.
   */
  public void setFilteredCases(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.bo.RemoteCase> _filteredCases)
  {
    filteredCases = _filteredCases;
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

}
