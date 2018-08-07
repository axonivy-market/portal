package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class ApplicationService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public ApplicationService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetAllApplicationsResult getAllApplications() throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetAllApplicationsResult result = new GetAllApplicationsResult();


    processResult = executeProcess("getAllApplications()", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setApplications((java.util.List<ch.ivy.ws.addon.types.IvyApplication>)getTupleField(processResult, "applications"));
    return result;
  }

  /**
   * @param applicationNames
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetApplicationsByAppNamesResult getApplicationsByAppNames(@javax.jws.WebParam(name="applicationNames") java.util.List<java.lang.String> applicationNames) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetApplicationsByAppNamesResult result = new GetApplicationsByAppNamesResult();

    params.put("applicationNames", applicationNames);

    processResult = executeProcess("getApplicationsByAppNames(List<String>)", params);

    result.setApplications((java.util.List<ch.ivy.ws.addon.types.IvyApplication>)getTupleField(processResult, "applications"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    return result;
  }

  /**
   * Result type for the getAllApplications method
   */
  public static class GetAllApplicationsResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyApplication> fApplications;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyApplication> getApplications()
    {
      return fApplications;
    }

    public void setApplications(java.util.List<ch.ivy.ws.addon.types.IvyApplication> applications)
    {
      fApplications = applications;
    }

  }
  /**
   * Result type for the getApplicationsByAppNames method
   */
  public static class GetApplicationsByAppNamesResult
  {
    private java.util.List<ch.ivy.ws.addon.types.IvyApplication> fApplications;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    public java.util.List<ch.ivy.ws.addon.types.IvyApplication> getApplications()
    {
      return fApplications;
    }

    public void setApplications(java.util.List<ch.ivy.ws.addon.types.IvyApplication> applications)
    {
      fApplications = applications;
    }

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

  }
}