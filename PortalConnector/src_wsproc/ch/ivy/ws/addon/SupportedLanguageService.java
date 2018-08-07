package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class SupportedLanguageService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public SupportedLanguageService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @param applicationName
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetSupportedLanguagesResult getSupportedLanguages(@javax.jws.WebParam(name="applicationName") java.lang.String applicationName) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetSupportedLanguagesResult result = new GetSupportedLanguagesResult();

    params.put("applicationName", applicationName);

    processResult = executeProcess("getSupportedLanguages(String)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setSupportedLanguages((java.util.List<java.lang.String>)getTupleField(processResult, "supportedLanguages"));
    return result;
  }

  /**
   * Result type for the getSupportedLanguages method
   */
  public static class GetSupportedLanguagesResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<java.lang.String> fSupportedLanguages;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<java.lang.String> getSupportedLanguages()
    {
      return fSupportedLanguages;
    }

    public void setSupportedLanguages(java.util.List<java.lang.String> supportedLanguages)
    {
      fSupportedLanguages = supportedLanguages;
    }

  }
}