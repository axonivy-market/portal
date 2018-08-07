package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class SecurityService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public SecurityService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
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
  public FindAllUsersResult findAllUsers(@javax.jws.WebParam(name="apps") java.util.List<java.lang.String> apps) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindAllUsersResult result = new FindAllUsersResult();

    params.put("apps", apps);

    processResult = executeProcess("findAllUsers(List<String>)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setUsers((java.util.List<ch.ivy.ws.addon.types.IvyUser>)getTupleField(processResult, "users"));
    return result;
  }

  /**
   * @param apps
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindAllRolesResult findAllRoles(@javax.jws.WebParam(name="apps") java.util.List<java.lang.String> apps) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindAllRolesResult result = new FindAllRolesResult();

    params.put("apps", apps);

    processResult = executeProcess("findAllRoles(List<String>)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setRoles((java.util.List<ch.ivy.ws.addon.types.IvyRole>)getTupleField(processResult, "roles"));
    return result;
  }

  /**
   * @param app
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindUsersByRoleIdResult findUsersByRoleId(@javax.jws.WebParam(name="app") java.lang.String app, @javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindUsersByRoleIdResult result = new FindUsersByRoleIdResult();

    params.put("app", app);
    params.put("id", id);

    processResult = executeProcess("findUsersByRoleId(String,Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setUsers((java.util.List<ch.ivy.ws.addon.types.IvyUser>)getTupleField(processResult, "users"));
    return result;
  }

  /**
   * @param taskID
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindSecurityMembersToDelegateResult findSecurityMembersToDelegate(@javax.jws.WebParam(name="taskID") java.lang.Long taskID) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindSecurityMembersToDelegateResult result = new FindSecurityMembersToDelegateResult();

    params.put("taskID", taskID);

    processResult = executeProcess("findSecurityMembersToDelegate(Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setUsers((java.util.List<ch.ivy.ws.addon.types.IvyUser>)getTupleField(processResult, "users"));
    result.setRoles((java.util.List<ch.ivy.ws.addon.types.IvyRole>)getTupleField(processResult, "roles"));
    return result;
  }

  /**
   * Result type for the findAllUsers method
   */
  public static class FindAllUsersResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyUser> fUsers;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyUser> getUsers()
    {
      return fUsers;
    }

    public void setUsers(java.util.List<ch.ivy.ws.addon.types.IvyUser> users)
    {
      fUsers = users;
    }

  }
  /**
   * Result type for the findAllRoles method
   */
  public static class FindAllRolesResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyRole> fRoles;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyRole> getRoles()
    {
      return fRoles;
    }

    public void setRoles(java.util.List<ch.ivy.ws.addon.types.IvyRole> roles)
    {
      fRoles = roles;
    }

  }
  /**
   * Result type for the findUsersByRoleId method
   */
  public static class FindUsersByRoleIdResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyUser> fUsers;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyUser> getUsers()
    {
      return fUsers;
    }

    public void setUsers(java.util.List<ch.ivy.ws.addon.types.IvyUser> users)
    {
      fUsers = users;
    }

  }
  /**
   * Result type for the findSecurityMembersToDelegate method
   */
  public static class FindSecurityMembersToDelegateResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyUser> fUsers;

    private java.util.List<ch.ivy.ws.addon.types.IvyRole> fRoles;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyUser> getUsers()
    {
      return fUsers;
    }

    public void setUsers(java.util.List<ch.ivy.ws.addon.types.IvyUser> users)
    {
      fUsers = users;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyRole> getRoles()
    {
      return fRoles;
    }

    public void setRoles(java.util.List<ch.ivy.ws.addon.types.IvyRole> roles)
    {
      fRoles = roles;
    }

  }
}