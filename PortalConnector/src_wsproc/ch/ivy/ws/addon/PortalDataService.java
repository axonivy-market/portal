package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class PortalDataService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public PortalDataService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @param customPropertyPairs
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public AddOrUpdateResult addOrUpdate(@javax.jws.WebParam(name="customPropertyPairs") java.util.List<ch.ivy.ws.portaldata.model.CustomPropertyPair> customPropertyPairs) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    AddOrUpdateResult result = new AddOrUpdateResult();

    params.put("customPropertyPairs", customPropertyPairs);

    processResult = executeProcess("addOrUpdate(List<ch.ivy.ws.portaldata.model.CustomPropertyPair>)", params);

    result.setMessage((java.lang.String)getTupleField(processResult, "message"));
    result.setStatus((ch.ivy.ws.addon.ReturnedStatus)getTupleField(processResult, "status"));
    return result;
  }

  /**
   * @param propertyKeyToBeDeleted
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public DeleteResult delete(@javax.jws.WebParam(name="propertyKeyToBeDeleted") java.lang.String propertyKeyToBeDeleted) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    DeleteResult result = new DeleteResult();

    params.put("propertyKeyToBeDeleted", propertyKeyToBeDeleted);

    processResult = executeProcess("delete(String)", params);

    result.setMessage((java.lang.String)getTupleField(processResult, "message"));
    result.setReturnedStatus((ch.ivy.ws.addon.ReturnedStatus)getTupleField(processResult, "returnedStatus"));
    return result;
  }

  /**
   * @param keyPrefixToBeDeleted
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public DeleteByPrefixResult deleteByPrefix(@javax.jws.WebParam(name="keyPrefixToBeDeleted") java.lang.String keyPrefixToBeDeleted) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    DeleteByPrefixResult result = new DeleteByPrefixResult();

    params.put("keyPrefixToBeDeleted", keyPrefixToBeDeleted);

    processResult = executeProcess("deleteByPrefix(String)", params);

    result.setMessage((java.lang.String)getTupleField(processResult, "message"));
    result.setReturnedStatus((ch.ivy.ws.addon.ReturnedStatus)getTupleField(processResult, "returnedStatus"));
    return result;
  }

  /**
   * @param propertyKeysToBeDeleted
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public DeleteManyPropertiesResult deleteManyProperties(@javax.jws.WebParam(name="propertyKeysToBeDeleted") java.util.List<java.lang.String> propertyKeysToBeDeleted) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    DeleteManyPropertiesResult result = new DeleteManyPropertiesResult();

    params.put("propertyKeysToBeDeleted", propertyKeysToBeDeleted);

    processResult = executeProcess("deleteManyProperties(List<String>)", params);

    result.setMessage((java.lang.String)getTupleField(processResult, "message"));
    result.setReturnedStatus((ch.ivy.ws.addon.ReturnedStatus)getTupleField(processResult, "returnedStatus"));
    return result;
  }

  /**
   * Result type for the addOrUpdate method
   */
  public static class AddOrUpdateResult
  {
    private java.lang.String fMessage;

    private ch.ivy.ws.addon.ReturnedStatus fStatus;

    public java.lang.String getMessage()
    {
      return fMessage;
    }

    public void setMessage(java.lang.String message)
    {
      fMessage = message;
    }

    public ch.ivy.ws.addon.ReturnedStatus getStatus()
    {
      return fStatus;
    }

    public void setStatus(ch.ivy.ws.addon.ReturnedStatus status)
    {
      fStatus = status;
    }

  }
  /**
   * Result type for the delete method
   */
  public static class DeleteResult
  {
    private java.lang.String fMessage;

    private ch.ivy.ws.addon.ReturnedStatus fReturnedStatus;

    public java.lang.String getMessage()
    {
      return fMessage;
    }

    public void setMessage(java.lang.String message)
    {
      fMessage = message;
    }

    public ch.ivy.ws.addon.ReturnedStatus getReturnedStatus()
    {
      return fReturnedStatus;
    }

    public void setReturnedStatus(ch.ivy.ws.addon.ReturnedStatus returnedStatus)
    {
      fReturnedStatus = returnedStatus;
    }

  }
  /**
   * Result type for the deleteByPrefix method
   */
  public static class DeleteByPrefixResult
  {
    private java.lang.String fMessage;

    private ch.ivy.ws.addon.ReturnedStatus fReturnedStatus;

    public java.lang.String getMessage()
    {
      return fMessage;
    }

    public void setMessage(java.lang.String message)
    {
      fMessage = message;
    }

    public ch.ivy.ws.addon.ReturnedStatus getReturnedStatus()
    {
      return fReturnedStatus;
    }

    public void setReturnedStatus(ch.ivy.ws.addon.ReturnedStatus returnedStatus)
    {
      fReturnedStatus = returnedStatus;
    }

  }
  /**
   * Result type for the deleteManyProperties method
   */
  public static class DeleteManyPropertiesResult
  {
    private java.lang.String fMessage;

    private ch.ivy.ws.addon.ReturnedStatus fReturnedStatus;

    public java.lang.String getMessage()
    {
      return fMessage;
    }

    public void setMessage(java.lang.String message)
    {
      fMessage = message;
    }

    public ch.ivy.ws.addon.ReturnedStatus getReturnedStatus()
    {
      return fReturnedStatus;
    }

    public void setReturnedStatus(ch.ivy.ws.addon.ReturnedStatus returnedStatus)
    {
      fReturnedStatus = returnedStatus;
    }

  }
}