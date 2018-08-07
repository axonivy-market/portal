package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class IsAliveService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public IsAliveService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @param apps
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public IsAliveResult isAlive(@javax.jws.WebParam(name="apps") java.util.List<java.lang.String> apps) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    IsAliveResult result = new IsAliveResult();

    params.put("apps", apps);

    processResult = executeProcess("isAlive(List<String>)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setApplicationList((java.util.List<ch.ivy.ws.addon.types.IvyApplication>)getTupleField(processResult, "applicationList"));
    result.setIsAlive((java.lang.Boolean)getTupleField(processResult, "isAlive"));
    return result;
  }

  /**
   * Result type for the isAlive method
   */
  public static class IsAliveResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyApplication> fApplicationList;

    private java.lang.Boolean fIsAlive;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyApplication> getApplicationList()
    {
      return fApplicationList;
    }

    public void setApplicationList(java.util.List<ch.ivy.ws.addon.types.IvyApplication> applicationList)
    {
      fApplicationList = applicationList;
    }

    public java.lang.Boolean getIsAlive()
    {
      return fIsAlive;
    }

    public void setIsAlive(java.lang.Boolean isAlive)
    {
      fIsAlive = isAlive;
    }

  }
}