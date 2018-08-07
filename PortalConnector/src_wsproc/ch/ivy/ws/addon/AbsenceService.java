package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class AbsenceService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public AbsenceService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @param applicationNames
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetAbsencesOfAllUsersResult getAbsencesOfAllUsers(@javax.jws.WebParam(name="applicationNames") java.util.List<java.lang.String> applicationNames) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetAbsencesOfAllUsersResult result = new GetAbsencesOfAllUsersResult();

    params.put("applicationNames", applicationNames);

    processResult = executeProcess("getAbsencesOfAllUsers(List<String>)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setAbsences((java.util.List<ch.ivy.ws.addon.types.Absence>)getTupleField(processResult, "absences"));
    return result;
  }

  /**
   * @param userName
   * @param applicationNames
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetAbsencesResult getAbsences(@javax.jws.WebParam(name="userName") java.lang.String userName, @javax.jws.WebParam(name="applicationNames") java.util.List<java.lang.String> applicationNames) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetAbsencesResult result = new GetAbsencesResult();

    params.put("userName", userName);
    params.put("applicationNames", applicationNames);

    processResult = executeProcess("getAbsences(String,List<String>)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setAbsences((java.util.List<ch.ivy.ws.addon.types.Absence>)getTupleField(processResult, "absences"));
    return result;
  }

  /**
   * @param userName
   * @param applicationNames
   * @param absencesToUpdate
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> setAbsences(@javax.jws.WebParam(name="userName") java.lang.String userName, @javax.jws.WebParam(name="applicationNames") java.util.List<java.lang.String> applicationNames, @javax.jws.WebParam(name="absencesToUpdate") java.util.List<ch.ivy.ws.addon.types.IvyAbsence> absencesToUpdate) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("userName", userName);
    params.put("applicationNames", applicationNames);
    params.put("absencesToUpdate", absencesToUpdate);

    processResult = executeProcess("setAbsences(String,List<String>,List<ch.ivy.ws.addon.types.IvyAbsence>)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * Result type for the getAbsencesOfAllUsers method
   */
  public static class GetAbsencesOfAllUsersResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.Absence> fAbsences;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.Absence> getAbsences()
    {
      return fAbsences;
    }

    public void setAbsences(java.util.List<ch.ivy.ws.addon.types.Absence> absences)
    {
      fAbsences = absences;
    }

  }
  /**
   * Result type for the getAbsences method
   */
  public static class GetAbsencesResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.Absence> fAbsences;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.Absence> getAbsences()
    {
      return fAbsences;
    }

    public void setAbsences(java.util.List<ch.ivy.ws.addon.types.Absence> absences)
    {
      fAbsences = absences;
    }

  }
}