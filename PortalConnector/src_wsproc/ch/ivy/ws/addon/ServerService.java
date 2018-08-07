package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class ServerService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public ServerService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetExternalHostResult getExternalHost() throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetExternalHostResult result = new GetExternalHostResult();


    processResult = executeProcess("getExternalHost()", params);

    result.setExternalHost((java.lang.String)getTupleField(processResult, "externalHost"));
    result.setErros((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "erros"));
    return result;
  }

  /**
   * Result type for the getExternalHost method
   */
  public static class GetExternalHostResult
  {
    private java.lang.String fExternalHost;

    private java.util.List<ch.ivy.ws.addon.WSException> fErros;

    public java.lang.String getExternalHost()
    {
      return fExternalHost;
    }

    public void setExternalHost(java.lang.String externalHost)
    {
      fExternalHost = externalHost;
    }

    public java.util.List<ch.ivy.ws.addon.WSException> getErros()
    {
      return fErros;
    }

    public void setErros(java.util.List<ch.ivy.ws.addon.WSException> erros)
    {
      fErros = erros;
    }

  }
}