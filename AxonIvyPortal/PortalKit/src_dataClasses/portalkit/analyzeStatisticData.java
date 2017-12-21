package portalkit;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class analyzeStatisticData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class analyzeStatisticData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -1275370707579324780L;

  private transient ch.ivy.ws.addon.PriorityStatistic priorityStatisticResult;

  /**
   * Gets the field priorityStatisticResult.
   * @return the value of the field priorityStatisticResult; may be null.
   */
  public ch.ivy.ws.addon.PriorityStatistic getPriorityStatisticResult()
  {
    return priorityStatisticResult;
  }

  /**
   * Sets the field priorityStatisticResult.
   * @param _priorityStatisticResult the new value of the field priorityStatisticResult.
   */
  public void setPriorityStatisticResult(ch.ivy.ws.addon.PriorityStatistic _priorityStatisticResult)
  {
    priorityStatisticResult = _priorityStatisticResult;
  }

  private transient ch.ivy.ws.addon.CaseStateStatistic caseStateStatisticResult;

  /**
   * Gets the field caseStateStatisticResult.
   * @return the value of the field caseStateStatisticResult; may be null.
   */
  public ch.ivy.ws.addon.CaseStateStatistic getCaseStateStatisticResult()
  {
    return caseStateStatisticResult;
  }

  /**
   * Sets the field caseStateStatisticResult.
   * @param _caseStateStatisticResult the new value of the field caseStateStatisticResult.
   */
  public void setCaseStateStatisticResult(ch.ivy.ws.addon.CaseStateStatistic _caseStateStatisticResult)
  {
    caseStateStatisticResult = _caseStateStatisticResult;
  }

  private transient java.util.List<ch.ivy.ws.addon.ExpiryStatistic> expiryStatisticResult;

  /**
   * Gets the field expiryStatisticResult.
   * @return the value of the field expiryStatisticResult; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.ExpiryStatistic> getExpiryStatisticResult()
  {
    return expiryStatisticResult;
  }

  /**
   * Sets the field expiryStatisticResult.
   * @param _expiryStatisticResult the new value of the field expiryStatisticResult.
   */
  public void setExpiryStatisticResult(java.util.List<ch.ivy.ws.addon.ExpiryStatistic> _expiryStatisticResult)
  {
    expiryStatisticResult = _expiryStatisticResult;
  }

  private transient java.lang.Long serverId;

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

  private transient java.lang.String jsonQuery;

  /**
   * Gets the field jsonQuery.
   * @return the value of the field jsonQuery; may be null.
   */
  public java.lang.String getJsonQuery()
  {
    return jsonQuery;
  }

  /**
   * Sets the field jsonQuery.
   * @param _jsonQuery the new value of the field jsonQuery.
   */
  public void setJsonQuery(java.lang.String _jsonQuery)
  {
    jsonQuery = _jsonQuery;
  }

  private transient java.util.List<java.lang.String> involvedApplications;

  /**
   * Gets the field involvedApplications.
   * @return the value of the field involvedApplications; may be null.
   */
  public java.util.List<java.lang.String> getInvolvedApplications()
  {
    return involvedApplications;
  }

  /**
   * Sets the field involvedApplications.
   * @param _involvedApplications the new value of the field involvedApplications.
   */
  public void setInvolvedApplications(java.util.List<java.lang.String> _involvedApplications)
  {
    involvedApplications = _involvedApplications;
  }

  private transient java.util.List<ch.ivy.ws.addon.ElapsedTimeStatistic> elapsedTimeStatisticResult;

  /**
   * Gets the field elapsedTimeStatisticResult.
   * @return the value of the field elapsedTimeStatisticResult; may be null.
   */
  public java.util.List<ch.ivy.ws.addon.ElapsedTimeStatistic> getElapsedTimeStatisticResult()
  {
    return elapsedTimeStatisticResult;
  }

  /**
   * Sets the field elapsedTimeStatisticResult.
   * @param _elapsedTimeStatisticResult the new value of the field elapsedTimeStatisticResult.
   */
  public void setElapsedTimeStatisticResult(java.util.List<ch.ivy.ws.addon.ElapsedTimeStatistic> _elapsedTimeStatisticResult)
  {
    elapsedTimeStatisticResult = _elapsedTimeStatisticResult;
  }

  private transient java.util.List<java.lang.String> caseCategories;

  /**
   * Gets the field caseCategories.
   * @return the value of the field caseCategories; may be null.
   */
  public java.util.List<java.lang.String> getCaseCategories()
  {
    return caseCategories;
  }

  /**
   * Sets the field caseCategories.
   * @param _caseCategories the new value of the field caseCategories.
   */
  public void setCaseCategories(java.util.List<java.lang.String> _caseCategories)
  {
    caseCategories = _caseCategories;
  }

  private transient ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria;

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

}
