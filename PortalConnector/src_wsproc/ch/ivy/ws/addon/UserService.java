package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class UserService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public UserService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @param user
   * @param appName
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindUserSettingResult findUserSetting(@javax.jws.WebParam(name="user") java.lang.String user, @javax.jws.WebParam(name="appName") java.lang.String appName) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindUserSettingResult result = new FindUserSettingResult();

    params.put("user", user);
    params.put("appName", appName);

    processResult = executeProcess("findUserSetting(String,String)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setUserSetting((ch.ivy.ws.addon.types.IvyUserSetting)getTupleField(processResult, "userSetting"));
    return result;
  }

  /**
   * @param userSetting
   * @param user
   * @param appName
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> saveUserSetting(@javax.jws.WebParam(name="userSetting") ch.ivy.ws.addon.types.IvyUserSetting userSetting, @javax.jws.WebParam(name="user") java.lang.String user, @javax.jws.WebParam(name="appName") java.lang.String appName) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("userSetting", userSetting);
    params.put("user", user);
    params.put("appName", appName);

    processResult = executeProcess("saveUserSetting(ch.ivy.ws.addon.types.IvyUserSetting,String,String)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * @param applications
   * @param user
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetEmailSetttingsResult getEmailSetttings(@javax.jws.WebParam(name="applications") java.util.List<java.lang.String> applications, @javax.jws.WebParam(name="user") java.lang.String user) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetEmailSetttingsResult result = new GetEmailSetttingsResult();

    params.put("applications", applications);
    params.put("user", user);

    processResult = executeProcess("getEmailSetttings(List<String>,String)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setSettings((java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting>)getTupleField(processResult, "settings"));
    return result;
  }

  /**
   * @param user
   * @param settings
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> setEMailSettings(@javax.jws.WebParam(name="user") java.lang.String user, @javax.jws.WebParam(name="settings") java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting> settings) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("user", user);
    params.put("settings", settings);

    processResult = executeProcess("setEMailSettings(String,List<ch.ivy.ws.addon.types.IvyEmailSetting>)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * @param applications
   * @param user
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetSubstitutesResult getSubstitutes(@javax.jws.WebParam(name="applications") java.util.List<java.lang.String> applications, @javax.jws.WebParam(name="user") java.lang.String user) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetSubstitutesResult result = new GetSubstitutesResult();

    params.put("applications", applications);
    params.put("user", user);

    processResult = executeProcess("getSubstitutes(List<String>,String)", params);

    result.setSubstitutes((java.util.List<ch.ivy.ws.addon.types.IvySubstitute>)getTupleField(processResult, "substitutes"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setApplicationUsers((java.util.List<ch.ivy.ws.addon.types.IvyUser>)getTupleField(processResult, "applicationUsers"));
    return result;
  }

  /**
   * @param username
   * @param substitutes
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> setSubstitutes(@javax.jws.WebParam(name="username") java.lang.String username, @javax.jws.WebParam(name="substitutes") java.util.List<ch.ivy.ws.addon.types.IvySubstitute> substitutes) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("username", username);
    params.put("substitutes", substitutes);

    processResult = executeProcess("setSubstitutes(String,List<ch.ivy.ws.addon.types.IvySubstitute>)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * @param serverId
   * @param applications
   * @param user
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public GetLanguagesSettingsResult getLanguagesSettings(@javax.jws.WebParam(name="serverId") java.lang.Long serverId, @javax.jws.WebParam(name="applications") java.util.List<java.lang.String> applications, @javax.jws.WebParam(name="user") java.lang.String user) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    GetLanguagesSettingsResult result = new GetLanguagesSettingsResult();

    params.put("serverId", serverId);
    params.put("applications", applications);
    params.put("user", user);

    processResult = executeProcess("getLanguagesSettings(Long,List<String>,String)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setSettings((java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting>)getTupleField(processResult, "settings"));
    return result;
  }

  /**
   * @param settings
   * @param user
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> setLanguagesSettings(@javax.jws.WebParam(name="settings") java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting> settings, @javax.jws.WebParam(name="user") java.lang.String user) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("settings", settings);
    params.put("user", user);

    processResult = executeProcess("setLanguagesSettings(List<ch.ivy.ws.addon.types.IvyLanguageSetting>,String)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * Result type for the findUserSetting method
   */
  public static class FindUserSettingResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private ch.ivy.ws.addon.types.IvyUserSetting fUserSetting;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public ch.ivy.ws.addon.types.IvyUserSetting getUserSetting()
    {
      return fUserSetting;
    }

    public void setUserSetting(ch.ivy.ws.addon.types.IvyUserSetting userSetting)
    {
      fUserSetting = userSetting;
    }

  }
  /**
   * Result type for the getEmailSetttings method
   */
  public static class GetEmailSetttingsResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting> fSettings;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting> getSettings()
    {
      return fSettings;
    }

    public void setSettings(java.util.List<ch.ivy.ws.addon.types.IvyEmailSetting> settings)
    {
      fSettings = settings;
    }

  }
  /**
   * Result type for the getSubstitutes method
   */
  public static class GetSubstitutesResult
  {
    private java.util.List<ch.ivy.ws.addon.types.IvySubstitute> fSubstitutes;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyUser> fApplicationUsers;

    public java.util.List<ch.ivy.ws.addon.types.IvySubstitute> getSubstitutes()
    {
      return fSubstitutes;
    }

    public void setSubstitutes(java.util.List<ch.ivy.ws.addon.types.IvySubstitute> substitutes)
    {
      fSubstitutes = substitutes;
    }

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyUser> getApplicationUsers()
    {
      return fApplicationUsers;
    }

    public void setApplicationUsers(java.util.List<ch.ivy.ws.addon.types.IvyUser> applicationUsers)
    {
      fApplicationUsers = applicationUsers;
    }

  }
  /**
   * Result type for the getLanguagesSettings method
   */
  public static class GetLanguagesSettingsResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting> fSettings;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting> getSettings()
    {
      return fSettings;
    }

    public void setSettings(java.util.List<ch.ivy.ws.addon.types.IvyLanguageSetting> settings)
    {
      fSettings = settings;
    }

  }
}