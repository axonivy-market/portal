package ch.ivy.addon.portalkit.bo;

import java.util.Date;
import java.util.List;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.scripting.objects.BusinessDuration;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

/**
 * Bean for remote task.
 */
public class RemoteTask {

  private String name;

  private Number customDecimalField1;
  private Number customDecimalField2;
  private Number customDecimalField3;
  private Number customDecimalField4;
  private Number customDecimalField5;

  private String customVarCharField1;
  private String customVarCharField2;
  private String customVarCharField3;
  private String customVarCharField4;
  private String customVarCharField5;

  private Date customTimestampField1;
  private Date customTimestampField2;
  private Date customTimestampField3;
  private Date customTimestampField4;
  private Date customTimestampField5;

  private String description;

  private WorkflowPriority expiryPriority;
  private WorkflowPriority originalPriority;

  private Date startTimestamp;
  private String businessCreatorUser;
  private String displayDescriptionTemplate;
  private String displayNameTemplate;
  private String expiryActivatorName;
  private String expiryActivatorFullName;
  private Long id;
  private boolean expired;
  private WorkflowPriority priority;
  private String fullRequestPath;
  private String kindCode;
  private String kindName;
  private String originalActivatorName;
  private String originalActivatorFullName;
  private TaskState state;
  private String workerUserName;
  private String workerFullName;

  private String subTypeCode;
  private String subTypeName;

  private RemoteTask expiredCreatorTask;
  private BusinessDuration expiresInBusinessDuration;
  private RemoteSecurityMember expiryActivator;
  private String expiryTaskStartElementPid;
  private Date expiryTimestamp;
  
  private Date endTimestamp;

  private RemoteApplication remoteApplication;
  private RemoteSecurityMember activator;
  private String activatorUserName;
  private String activatorFullName;
  private RemoteCase remoteCase;
  private RemoteCase remoteTechnicalCase;
  private List<RemoteNote> remoteNotes;
  private boolean canReset;
  private boolean canDelegate;
  private boolean canResume;
  private boolean canPark;
  private boolean canChangePriority;
  private boolean canChangeExpiry;
  private boolean canChangeDescription;
  private boolean canChangeName;
  private boolean hasMoreActions;
  
  /**
   * Gets the workerFullName
   *
   * @return Returns the workerFullName
   */
  public String getWorkerFullName() {
    return workerFullName;
  }

  /**
   * Sets the workerFullName
   *
   * @param workerFullName The workerFullName to set
   */
  public void setWorkerFullName(String workerFullName) {
    this.workerFullName = workerFullName;
  }

  /**
   * @{link {@link Application} object that this task belongs to
   */
  private Application applicationRegister;

  /**
   * Constructor
   *
   */
  public RemoteTask() {
    super();
  }

  public RemoteSecurityMember getActivator() {
    return activator;
  }

  public String getActivatorName() {
    return null;
  }

  public RemoteApplication getApplication() {
    return remoteApplication;
  }


  public String getBusinessCreatorUser() {
    return businessCreatorUser;
  }

  public RemoteCase getCase() {
    return remoteCase;
  }

  public Number getCustomDecimalField1() {
    return customDecimalField1;
  }

  public Number getCustomDecimalField2() {
    return customDecimalField2;
  }

  public Number getCustomDecimalField3() {
    return customDecimalField3;
  }

  public Number getCustomDecimalField4() {
    return customDecimalField4;
  }

  public Number getCustomDecimalField5() {
    return customDecimalField5;
  }

  public Date getCustomTimestampField1() {
    return customTimestampField1;
  }

  public Date getCustomTimestampField2() {
    return customTimestampField2;
  }

  public Date getCustomTimestampField3() {
    return customTimestampField3;
  }

  public Date getCustomTimestampField4() {
    return customTimestampField4;
  }

  public Date getCustomTimestampField5() {
    return customTimestampField5;
  }

  public String getCustomVarCharField1() {
    return customVarCharField1;
  }

  public String getCustomVarCharField2() {
    return customVarCharField2;
  }

  public String getCustomVarCharField3() {
    return customVarCharField3;
  }

  public String getCustomVarCharField4() {
    return customVarCharField4;
  }

  public String getCustomVarCharField5() {
    return customVarCharField5;
  }

  public Date getDelayTimestamp() {
    return null;
  }

  public String getDescription() {
    return description;
  }

  public String getDisplayDescriptionTemplate() {
    return displayDescriptionTemplate;
  }

  public String getDisplayNameTemplate() {
    return displayNameTemplate;
  }

  public Date getEndTimestamp() {
    return endTimestamp;
  }

  public RemoteTask getExpiredCreatorTask() {
    return expiredCreatorTask;
  }

  public BusinessDuration getExpiresInBusinessDuration() {
    return expiresInBusinessDuration;
  }

  public RemoteSecurityMember getExpiryActivator() {
    return expiryActivator;
  }

  public String getExpiryActivatorName() {
    return expiryActivatorName;
  }

  public WorkflowPriority getExpiryPriority() {
    return expiryPriority;
  }

  public String getExpiryTaskStartElementPid() {
    return expiryTaskStartElementPid;
  }

  public Date getExpiryTimestamp() {
    return expiryTimestamp;
  }

  public String getFullRequestPath() {
    return fullRequestPath;
  }

  public long getId() {
    return id;
  }

  public String getKindCode() {
    return kindCode;
  }

  public String getKindName() {
    return kindName;
  }

  public String getName() {
    return name;
  }

  public int getNumberOfFailures() {
    return 0;
  }

  public String getOriginalActivatorName() {
    return originalActivatorName;
  }

  public WorkflowPriority getOriginalPriority() {
    return originalPriority;
  }

  public WorkflowPriority getPriority() {
    return priority;
  }

  public Date getStartTimestamp() {
    return startTimestamp;
  }

  public TaskState getState() {
    return state;
  }

  public String getSubTypeCode() {
    return subTypeCode;
  }

  public String getSubTypeName() {
    return subTypeName;
  }

  public IWorkflowSession getWorkerSession() {
    return null;
  }

  public String getWorkerUserName() {
    return workerUserName;
  }

  public boolean isExpired() {
    return expired;
  }

  public boolean isExpiryTask() {
    return false;
  }

  public boolean isPersistent() {
    return false;
  }

  public boolean isUpdatedOnStart() {
    return false;
  }

  public void reset() {

  }

  public void setActivator(RemoteSecurityMember activator) {
    this.activator = activator;

  }

  public void setCustomDecimalField1(Number customDecimalField1) {
    this.customDecimalField1 = customDecimalField1;

  }

  public void setCustomDecimalField2(Number customDecimalField2) {
    this.customDecimalField2 = customDecimalField2;

  }

  public void setCustomDecimalField3(Number customDecimalField3) {
    this.customDecimalField3 = customDecimalField3;

  }

  public void setCustomDecimalField4(Number customDecimalField4) {
    this.customDecimalField4 = customDecimalField4;

  }

  public void setCustomDecimalField5(Number customDecimalField5) {
    this.customDecimalField5 = customDecimalField5;

  }

  public void setCustomTimestampField1(Date customTimestampField1) {
    this.customTimestampField1 = customTimestampField1;

  }

  public void setCustomTimestampField2(Date customTimestampField2) {
    this.customTimestampField2 = customTimestampField2;

  }

  public void setCustomTimestampField3(Date customTimestampField3) {
    this.customTimestampField3 = customTimestampField3;

  }

  public void setCustomTimestampField4(Date customTimestampField4) {
    this.customTimestampField4 = customTimestampField4;

  }

  public void setCustomTimestampField5(Date customTimestampField5) {
    this.customTimestampField5 = customTimestampField5;

  }

  public void setCustomVarCharField1(String customVarCharField1) {
    this.customVarCharField1 = customVarCharField1;

  }

  public void setCustomVarCharField2(String customVarCharField2) {
    this.customVarCharField2 = customVarCharField2;

  }

  public void setCustomVarCharField3(String customVarCharField3) {
    this.customVarCharField3 = customVarCharField3;

  }

  public void setCustomVarCharField4(String customVarCharField4) {
    this.customVarCharField4 = customVarCharField4;

  }

  public void setCustomVarCharField5(String customVarCharField5) {
    this.customVarCharField5 = customVarCharField5;

  }

  @SuppressWarnings("unused")
  public void setDelayTimestamp(Date newDelayTimestamp) {

  }

  public void setDescription(String description) {
    this.description = description;

  }

  public void setExpiryActivator(RemoteSecurityMember expiryActivator) {
    this.expiryActivator = expiryActivator;

  }

  public void setExpiryPriority(WorkflowPriority expiryPriority) {
    this.expiryPriority = expiryPriority;

  }

  public void setExpiryTimestamp(Date newExpiryTimestamp) {
    this.expiryTimestamp = newExpiryTimestamp;
  }

  @SuppressWarnings("unused")
  public void setKind(String kindCode, String kindName) {

  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOriginalPriority(WorkflowPriority originalPriority) {
    this.originalPriority = originalPriority;

  }

  /**
   * Set start time stamp.
   *
   * @param startTimestamp start time stamp
   * @throws PersistencyException PersistencyException
   */
  public void setStartTimestamp(Date startTimestamp) {
    this.startTimestamp = startTimestamp;
  }
  
  /**
   * Set end time stamp.
   *
   * @param endTimestamp end time stamp
   * @throws PersistencyException PersistencyException
   */
  public void setEndTimestamp(Date endTimestamp) {
    this.endTimestamp = endTimestamp;
  }

  /**
   * Set business creator user.
   *
   * @param businessCreatorUser business creator user
   * @throws PersistencyException PersistencyException
   */
  public void setBusinessCreatorUser(String businessCreatorUser) {
    this.businessCreatorUser = businessCreatorUser;
  }

  /**
   * Set display description template.
   *
   * @param displayDescriptionTemplate display description template
   * @throws PersistencyException PersistencyException
   */
  public void setDisplayDescriptionTemplate(String displayDescriptionTemplate) {
    this.displayDescriptionTemplate = displayDescriptionTemplate;
  }

  /**
   * Set display name template.
   *
   * @param displayNameTemplate display name template
   * @throws PersistencyException PersistencyException
   */
  public void setDisplayNameTemplate(String displayNameTemplate) {
    this.displayNameTemplate = displayNameTemplate;
  }

  /**
   * Set expriry activator name.
   *
   * @param expiryActivatorName expriry activator name
   * @throws PersistencyException PersistencyException
   */
  public void setExpiryActivatorName(String expiryActivatorName) {
    this.expiryActivatorName = expiryActivatorName;
  }

  /**
   * Set task id.
   *
   * @param id task id
   * @throws PersistencyException PersistencyException
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Set task expired.
   *
   * @param expired expired or not
   * @throws PersistencyException PersistencyException
   */
  public void setIsExpired(boolean expired) {
    this.expired = expired;
  }

  /**
   * Task task priority.
   *
   * @param priority task priority
   * @throws PersistencyException PersistencyException
   */
  public void setPriority(WorkflowPriority priority) {
    this.priority = priority;
  }

  /**
   * Set full request path.
   *
   * @param fullRequestPath full request path
   * @throws PersistencyException PersistencyException
   */
  public void setFullRequestPath(String fullRequestPath) {
    this.fullRequestPath = fullRequestPath;
  }

  /**
   * Set kind code.
   *
   * @param kindCode kind code
   * @throws PersistencyException PersistencyException
   */
  public void setKindCode(String kindCode) {
    this.kindCode = kindCode;
  }

  /**
   * Set kind name.
   *
   * @param kindName kind name.
   * @throws PersistencyException PersistencyException
   */
  public void setKindName(String kindName) {
    this.kindName = kindName;
  }

  /**
   * Set original activator name.
   *
   * @param originalActivatorName original activator name
   * @throws PersistencyException PersistencyException
   */
  public void setOriginalActivatorName(String originalActivatorName) {
    this.originalActivatorName = originalActivatorName;
  }

  /**
   * Set task state.
   *
   * @param state task state
   * @throws PersistencyException PersistencyException
   */
  public void setState(TaskState state) {
    this.state = state;
  }

  /**
   * Set worker username.
   *
   * @param workerUserName username of worker
   * @throws PersistencyException PersistencyException
   */
  public void setWorkerUserName(String workerUserName) {
    this.workerUserName = workerUserName;
  }

  /**
   * Set sub type code.
   *
   * @param subTypeCode sub type code
   */
  public void setSubTypeCode(String subTypeCode) {
    this.subTypeCode = subTypeCode;
  }

  /**
   * Set sub type name.
   *
   * @param subTypeName sub type name
   */
  public void setSubTypeName(String subTypeName) {
    this.subTypeName = subTypeName;
  }

  /**
   * Set application.
   *
   * @param remoteApplication remote application
   */
  public void setApplication(RemoteApplication remoteApplication) {
    this.remoteApplication = remoteApplication;
  }

  /**
   * Set case.
   *
   * @param remoteCase remote case
   */
  public void setCase(RemoteCase remoteCase) {
    this.remoteCase = remoteCase;
  }

  /**
   * Set expired creator task.
   *
   * @param expiredCreatorTask expired task
   */
  public void setExpiredCreatorTask(RemoteTask expiredCreatorTask) {
    this.expiredCreatorTask = expiredCreatorTask;
  }

  /**
   * Set expired in business duration.
   *
   * @param expiresInBusinessDuration expiresInBusinessDuration
   */
  public void setExpiresInBusinessDuration(BusinessDuration expiresInBusinessDuration) {
    this.expiresInBusinessDuration = expiresInBusinessDuration;
  }

  /**
   * Set expired task start email Pid.
   *
   * @param expiryTaskStartElementPid expiryTaskStartElementPid
   */
  public void setExpiryTaskStartElementPid(String expiryTaskStartElementPid) {
    this.expiryTaskStartElementPid = expiryTaskStartElementPid;
  }

  /**
   * Get label for TaskPriority.
   * 
   * @return cms label of the priority
   */
  public String getPriorityLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + this.priority);
  }

  /**
   * Get label for TaskPriority
   * 
   * @return cms label of the state
   */
  public String getStateLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + this.state);
  }

  /**
   * Get application register.
   *
   * @return application register
   */
  public Application getApplicationRegister() {
    return applicationRegister;
  }

  /**
   * Set application register.
   *
   * @param applicationRegister application
   */
  public void setApplicationRegister(Application applicationRegister) {
    this.applicationRegister = applicationRegister;
  }

  public List<RemoteNote> getRemoteNotes() {
    return remoteNotes;
  }

  public void setRemoteNotes(List<RemoteNote> remoteNotes) {
    this.remoteNotes = remoteNotes;
  }

  public boolean isCanReset() {
    return canReset;
  }

  public void setCanReset(boolean canReset) {
    this.canReset = canReset;
  }

  public boolean isCanDelegate() {
    return canDelegate;
  }

  public void setCanDelegate(boolean canDelegate) {
    this.canDelegate = canDelegate;
  }

  public boolean isCanResume() {
    return canResume;
  }

  public void setCanResume(boolean canResume) {
    this.canResume = canResume;
  }

  public boolean isCanPark() {
    return canPark;
  }

  public void setCanPark(boolean canPark) {
    this.canPark = canPark;
  }

  public boolean canChangePriority() {
    return canChangePriority;
  }

  public void setCanChangePriority(boolean canChangePriority) {
    this.canChangePriority = canChangePriority;
  }

  public boolean canChangeExpiry() {
    return canChangeExpiry;
  }

  public void setCanChangeExpiry(boolean canChangeExpiry) {
    this.canChangeExpiry = canChangeExpiry;
  }

  public String getExpiryActivatorFullName() {
    return expiryActivatorFullName;
  }

  public void setExpiryActivatorFullName(String expiryActivatorFullName) {
    this.expiryActivatorFullName = expiryActivatorFullName;
  }

  public String getOriginalActivatorFullName() {
    return originalActivatorFullName;
  }

  public void setOriginalActivatorFullName(String originalActivatorFullName) {
    this.originalActivatorFullName = originalActivatorFullName;
  }

  public String getActivatorFullName() {
    return activatorFullName;
  }

  public void setActivatorFullName(String activatorFullName) {
    this.activatorFullName = activatorFullName;
  }
  
  public String getActivatorUserName() {
    return activatorUserName;
  }

  public void setActivatorUserName(String activatorUserName) {
    this.activatorUserName = activatorUserName;
  }

  public boolean isCanChangeDescription() {
    return canChangeDescription;
  }

  public void setCanChangeDescription(boolean canChangeDescription) {
    this.canChangeDescription = canChangeDescription;
  }

  public boolean isCanChangeName() {
	return canChangeName;
  }

  public void setCanChangeName(boolean canChangeName) {
	this.canChangeName = canChangeName;
  }

  public boolean isHasMoreActions() {
    return hasMoreActions;
  }

  public void setHasMoreActions(boolean hasMoreActions) {
    this.hasMoreActions = hasMoreActions;
  }

  public RemoteCase getRemoteTechnicalCase() {
    return remoteTechnicalCase;
  }

  public void setRemoteTechnicalCase(RemoteCase remoteTechnicalCase) {
    this.remoteTechnicalCase = remoteTechnicalCase;
  }
}
