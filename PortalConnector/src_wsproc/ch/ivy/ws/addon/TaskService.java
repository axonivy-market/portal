package ch.ivy.ws.addon;

/**
 * 
 */
@SuppressWarnings("all")
@javax.jws.WebService
public class TaskService extends ch.ivyteam.ivy.webservice.process.restricted.AbstractWebServiceProcess
{

  /**
   * Constructor
   * @param webServiceProcess the web service process this class represents
   * @param processEngineManager the process engine manager to start the ws process
   */
  public TaskService(ch.ivyteam.ivy.webservice.process.restricted.IWebServiceProcessBeanRuntime webServiceProcessBeanRuntime)
  {
    super(webServiceProcessBeanRuntime);
  }

  /**
   * @param serverUrl
   * @param user
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public ParkTaskResult parkTask(@javax.jws.WebParam(name="serverUrl") java.lang.String serverUrl, @javax.jws.WebParam(name="user") java.lang.String user, @javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    ParkTaskResult result = new ParkTaskResult();

    params.put("serverUrl", serverUrl);
    params.put("user", user);
    params.put("id", id);

    processResult = executeProcess("parkTask(String,String,Long)", params);

    result.setError((ch.ivy.ws.addon.WSException)getTupleField(processResult, "error"));
    result.setIvyTask((ch.ivy.ws.addon.types.IvyTask)getTupleField(processResult, "ivyTask"));
    return result;
  }

  /**
   * @param serverUrl
   * @param ivySecurityMember
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public DelegateTaskResult delegateTask(@javax.jws.WebParam(name="serverUrl") java.lang.String serverUrl, @javax.jws.WebParam(name="ivySecurityMember") ch.ivy.ws.addon.types.IvySecurityMember ivySecurityMember, @javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    DelegateTaskResult result = new DelegateTaskResult();

    params.put("serverUrl", serverUrl);
    params.put("ivySecurityMember", ivySecurityMember);
    params.put("id", id);

    processResult = executeProcess("delegateTask(String,ch.ivy.ws.addon.types.IvySecurityMember,Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setIvyTask((ch.ivy.ws.addon.types.IvyTask)getTupleField(processResult, "ivyTask"));
    return result;
  }

  /**
   * @param user
   * @param message
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public CreateTaskNoteResult createTaskNote(@javax.jws.WebParam(name="user") java.lang.String user, @javax.jws.WebParam(name="message") java.lang.String message, @javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    CreateTaskNoteResult result = new CreateTaskNoteResult();

    params.put("user", user);
    params.put("message", message);
    params.put("id", id);

    processResult = executeProcess("createTaskNote(String,String,Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setNote((ch.ivy.ws.addon.types.IvyNote)getTupleField(processResult, "note"));
    return result;
  }

  /**
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindTaskNotesResult findTaskNotes(@javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindTaskNotesResult result = new FindTaskNotesResult();

    params.put("id", id);

    processResult = executeProcess("findTaskNotes(Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setIvyNotes((java.util.List<ch.ivy.ws.addon.types.IvyNote>)getTupleField(processResult, "ivyNotes"));
    return result;
  }

  /**
   * @param currentUserName
   * @param serverUrl
   * @param id
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public ResetTaskResult resetTask(@javax.jws.WebParam(name="currentUserName") java.lang.String currentUserName, @javax.jws.WebParam(name="serverUrl") java.lang.String serverUrl, @javax.jws.WebParam(name="id") java.lang.Long id) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    ResetTaskResult result = new ResetTaskResult();

    params.put("currentUserName", currentUserName);
    params.put("serverUrl", serverUrl);
    params.put("id", id);

    processResult = executeProcess("resetTask(String,String,Long)", params);

    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setIvyTask((ch.ivy.ws.addon.types.IvyTask)getTupleField(processResult, "ivyTask"));
    return result;
  }

  /**
   * @param isNoConfigurationMode
   * @param serverId
   * @param startIndex
   * @param count
   * @param serverUrl
   * @param taskSearchCriteria
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindTasksByCriteriaResult findTasksByCriteria(@javax.jws.WebParam(name="isNoConfigurationMode") java.lang.Boolean isNoConfigurationMode, @javax.jws.WebParam(name="serverId") java.lang.Long serverId, @javax.jws.WebParam(name="startIndex") java.lang.Integer startIndex, @javax.jws.WebParam(name="count") java.lang.Integer count, @javax.jws.WebParam(name="serverUrl") java.lang.String serverUrl, @javax.jws.WebParam(name="taskSearchCriteria") ch.ivy.ws.addon.service.TaskSearchCriteria taskSearchCriteria) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindTasksByCriteriaResult result = new FindTasksByCriteriaResult();

    params.put("isNoConfigurationMode", isNoConfigurationMode);
    params.put("serverId", serverId);
    params.put("startIndex", startIndex);
    params.put("count", count);
    params.put("serverUrl", serverUrl);
    params.put("taskSearchCriteria", taskSearchCriteria);

    processResult = executeProcess("findTasksByCriteria(Boolean,Long,Integer,Integer,String,ch.ivy.ws.addon.service.TaskSearchCriteria)", params);

    result.setAllIvyTasks((java.util.List<ch.ivy.ws.addon.types.IvyTask>)getTupleField(processResult, "allIvyTasks"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    result.setIvyTasks((java.util.List<ch.ivy.ws.addon.types.IvyTask>)getTupleField(processResult, "ivyTasks"));
    return result;
  }

  /**
   * @param id
   * @param userName
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public CanUserResumeTaskResult canUserResumeTask(@javax.jws.WebParam(name="id") java.lang.Long id, @javax.jws.WebParam(name="userName") java.lang.String userName) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    CanUserResumeTaskResult result = new CanUserResumeTaskResult();

    params.put("id", id);
    params.put("userName", userName);

    processResult = executeProcess("canUserResumeTask(Long,String)", params);

    result.setWorkerUserName((java.lang.String)getTupleField(processResult, "workerUserName"));
    result.setCanUserResumeTask((java.lang.Boolean)getTupleField(processResult, "canUserResumeTask"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    return result;
  }

  /**
   * @param taskSearchCriteria
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public CountTasksByCriteriaResult countTasksByCriteria(@javax.jws.WebParam(name="taskSearchCriteria") ch.ivy.ws.addon.service.TaskSearchCriteria taskSearchCriteria) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    CountTasksByCriteriaResult result = new CountTasksByCriteriaResult();

    params.put("taskSearchCriteria", taskSearchCriteria);

    processResult = executeProcess("countTasksByCriteria(ch.ivy.ws.addon.service.TaskSearchCriteria)", params);

    result.setTaskCount((java.lang.Long)getTupleField(processResult, "taskCount"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    return result;
  }

  /**
   * @param userName
   * @param apps
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public FindCategoriesResult findCategories(@javax.jws.WebParam(name="userName") java.lang.String userName, @javax.jws.WebParam(name="apps") java.util.List<java.lang.String> apps) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    FindCategoriesResult result = new FindCategoriesResult();

    params.put("userName", userName);
    params.put("apps", apps);

    processResult = executeProcess("findCategories(String,List<String>)", params);

    result.setCategories((java.util.List<java.lang.String>)getTupleField(processResult, "categories"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    return result;
  }

  /**
   * @param userName
   * @param apps
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public AnalyzePriorityStatisticResult analyzePriorityStatistic(@javax.jws.WebParam(name="userName") java.lang.String userName, @javax.jws.WebParam(name="apps") java.util.List<java.lang.String> apps) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    AnalyzePriorityStatisticResult result = new AnalyzePriorityStatisticResult();

    params.put("userName", userName);
    params.put("apps", apps);

    processResult = executeProcess("analyzePriorityStatistic(String,List<String>)", params);

    result.setPriorityStatistic((ch.ivy.ws.addon.types.PriorityStatistic)getTupleField(processResult, "priorityStatistic"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    return result;
  }

  /**
   * @param userName
   * @param apps
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="result")
  public AnalyzeExpiryStatisticResult analyzeExpiryStatistic(@javax.jws.WebParam(name="userName") java.lang.String userName, @javax.jws.WebParam(name="apps") java.util.List<java.lang.String> apps) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;
    AnalyzeExpiryStatisticResult result = new AnalyzeExpiryStatisticResult();

    params.put("userName", userName);
    params.put("apps", apps);

    processResult = executeProcess("analyzeExpiryStatistic(String,List<String>)", params);

    result.setExpiryStatistic((ch.ivy.ws.addon.types.ExpiryStatistic)getTupleField(processResult, "expiryStatistic"));
    result.setErrors((java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors"));
    return result;
  }

  /**
   * @param task
   * @return
   * @throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
   */
  @javax.jws.WebMethod
  @javax.jws.WebResult(name="errors")
  public java.util.List<ch.ivy.ws.addon.WSException> save(@javax.jws.WebParam(name="task") ch.ivy.ws.addon.types.IvyTask task) throws ch.ivyteam.ivy.webservice.process.restricted.WebServiceProcessTechnicalException
  {
    java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
    ch.ivyteam.ivy.scripting.objects.Tuple processResult;

    params.put("task", task);

    processResult = executeProcess("save(ch.ivy.ws.addon.types.IvyTask)", params);

    return (java.util.List<ch.ivy.ws.addon.WSException>)getTupleField(processResult, "errors");
  }

  /**
   * Result type for the parkTask method
   */
  public static class ParkTaskResult
  {
    private ch.ivy.ws.addon.WSException fError;

    private ch.ivy.ws.addon.types.IvyTask fIvyTask;

    public ch.ivy.ws.addon.WSException getError()
    {
      return fError;
    }

    public void setError(ch.ivy.ws.addon.WSException error)
    {
      fError = error;
    }

    public ch.ivy.ws.addon.types.IvyTask getIvyTask()
    {
      return fIvyTask;
    }

    public void setIvyTask(ch.ivy.ws.addon.types.IvyTask ivyTask)
    {
      fIvyTask = ivyTask;
    }

  }
  /**
   * Result type for the delegateTask method
   */
  public static class DelegateTaskResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private ch.ivy.ws.addon.types.IvyTask fIvyTask;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public ch.ivy.ws.addon.types.IvyTask getIvyTask()
    {
      return fIvyTask;
    }

    public void setIvyTask(ch.ivy.ws.addon.types.IvyTask ivyTask)
    {
      fIvyTask = ivyTask;
    }

  }
  /**
   * Result type for the createTaskNote method
   */
  public static class CreateTaskNoteResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private ch.ivy.ws.addon.types.IvyNote fNote;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public ch.ivy.ws.addon.types.IvyNote getNote()
    {
      return fNote;
    }

    public void setNote(ch.ivy.ws.addon.types.IvyNote note)
    {
      fNote = note;
    }

  }
  /**
   * Result type for the findTaskNotes method
   */
  public static class FindTaskNotesResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyNote> fIvyNotes;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyNote> getIvyNotes()
    {
      return fIvyNotes;
    }

    public void setIvyNotes(java.util.List<ch.ivy.ws.addon.types.IvyNote> ivyNotes)
    {
      fIvyNotes = ivyNotes;
    }

  }
  /**
   * Result type for the resetTask method
   */
  public static class ResetTaskResult
  {
    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private ch.ivy.ws.addon.types.IvyTask fIvyTask;

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public ch.ivy.ws.addon.types.IvyTask getIvyTask()
    {
      return fIvyTask;
    }

    public void setIvyTask(ch.ivy.ws.addon.types.IvyTask ivyTask)
    {
      fIvyTask = ivyTask;
    }

  }
  /**
   * Result type for the findTasksByCriteria method
   */
  public static class FindTasksByCriteriaResult
  {
    private java.util.List<ch.ivy.ws.addon.types.IvyTask> fAllIvyTasks;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    private java.util.List<ch.ivy.ws.addon.types.IvyTask> fIvyTasks;

    public java.util.List<ch.ivy.ws.addon.types.IvyTask> getAllIvyTasks()
    {
      return fAllIvyTasks;
    }

    public void setAllIvyTasks(java.util.List<ch.ivy.ws.addon.types.IvyTask> allIvyTasks)
    {
      fAllIvyTasks = allIvyTasks;
    }

    public java.util.List<ch.ivy.ws.addon.WSException> getErrors()
    {
      return fErrors;
    }

    public void setErrors(java.util.List<ch.ivy.ws.addon.WSException> errors)
    {
      fErrors = errors;
    }

    public java.util.List<ch.ivy.ws.addon.types.IvyTask> getIvyTasks()
    {
      return fIvyTasks;
    }

    public void setIvyTasks(java.util.List<ch.ivy.ws.addon.types.IvyTask> ivyTasks)
    {
      fIvyTasks = ivyTasks;
    }

  }
  /**
   * Result type for the canUserResumeTask method
   */
  public static class CanUserResumeTaskResult
  {
    private java.lang.String fWorkerUserName;

    private java.lang.Boolean fCanUserResumeTask;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    public java.lang.String getWorkerUserName()
    {
      return fWorkerUserName;
    }

    public void setWorkerUserName(java.lang.String workerUserName)
    {
      fWorkerUserName = workerUserName;
    }

    public java.lang.Boolean getCanUserResumeTask()
    {
      return fCanUserResumeTask;
    }

    public void setCanUserResumeTask(java.lang.Boolean canUserResumeTask)
    {
      fCanUserResumeTask = canUserResumeTask;
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
  /**
   * Result type for the countTasksByCriteria method
   */
  public static class CountTasksByCriteriaResult
  {
    private java.lang.Long fTaskCount;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    public java.lang.Long getTaskCount()
    {
      return fTaskCount;
    }

    public void setTaskCount(java.lang.Long taskCount)
    {
      fTaskCount = taskCount;
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
  /**
   * Result type for the findCategories method
   */
  public static class FindCategoriesResult
  {
    private java.util.List<java.lang.String> fCategories;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    public java.util.List<java.lang.String> getCategories()
    {
      return fCategories;
    }

    public void setCategories(java.util.List<java.lang.String> categories)
    {
      fCategories = categories;
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
  /**
   * Result type for the analyzePriorityStatistic method
   */
  public static class AnalyzePriorityStatisticResult
  {
    private ch.ivy.ws.addon.types.PriorityStatistic fPriorityStatistic;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    public ch.ivy.ws.addon.types.PriorityStatistic getPriorityStatistic()
    {
      return fPriorityStatistic;
    }

    public void setPriorityStatistic(ch.ivy.ws.addon.types.PriorityStatistic priorityStatistic)
    {
      fPriorityStatistic = priorityStatistic;
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
  /**
   * Result type for the analyzeExpiryStatistic method
   */
  public static class AnalyzeExpiryStatisticResult
  {
    private ch.ivy.ws.addon.types.ExpiryStatistic fExpiryStatistic;

    private java.util.List<ch.ivy.ws.addon.WSException> fErrors;

    public ch.ivy.ws.addon.types.ExpiryStatistic getExpiryStatistic()
    {
      return fExpiryStatistic;
    }

    public void setExpiryStatistic(ch.ivy.ws.addon.types.ExpiryStatistic expiryStatistic)
    {
      fExpiryStatistic = expiryStatistic;
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