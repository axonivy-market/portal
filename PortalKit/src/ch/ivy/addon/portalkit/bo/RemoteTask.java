package ch.ivy.addon.portalkit.bo;

import java.util.Date;
import java.util.List;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.api.IvyScriptVisibility;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.calendar.IBusinessCalendar;
import ch.ivyteam.ivy.application.calendar.IDefaultBusinessCalendar;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.location.ILocationService;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.scripting.objects.BusinessDuration;
import ch.ivyteam.ivy.scripting.objects.CompositeObject;
import ch.ivyteam.ivy.scripting.objects.Duration;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IIntermediateEvent;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.IPageArchive;
import ch.ivyteam.ivy.workflow.IProcessData;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.ITaskEnd;
import ch.ivyteam.ivy.workflow.ITaskStart;
import ch.ivyteam.ivy.workflow.ITaskSwitchEvent;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.ivy.workflow.IWorkflowEvent;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.ResumeTaskResult;
import ch.ivyteam.ivy.workflow.TaskProperty;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

/**
 * Bean for remote task.
 *
 * @author maonguyen
 */
public class RemoteTask implements ITask {

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

  private RemoteApplication remoteApplication;
  private RemoteSecurityMember activator;
  private String activatorUserName;
  private String activatorFullName;
  private RemoteCase remoteCase;
  private List<RemoteNote> remoteNotes;
  private boolean canReset;
  private boolean canDelegate;
  private boolean canResume;
  private boolean canPark;
  private boolean canChangePriority;
  private boolean canChangeDescription;
  private boolean canChangeName;

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
   * @{link {@link ApplicationRegister} object that this task belongs to
   */
  private Application applicationRegister;

  /**
   * Constructor
   *
   */
  public RemoteTask() {
    super();
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public INote createNote(IWorkflowSession session, String message) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void deleteNote(INote note) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<INote> getNotes() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public boolean hasNotes() throws PersistencyException {
    return false;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public String getAdditionalProperty(String name) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<String> getAdditionalPropertyNames() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setAdditionalProperty(String name, String value) throws PersistencyException {

  }

  @Override
  public Object getPropertyValue(TaskProperty arg0) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void destroy() throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public ISecurityMember getActivator() throws PersistencyException {
    return activator;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getActivatorName() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<IUser> getActivatorUserCandidates() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IApplication getApplication() throws PersistencyException {
    return remoteApplication;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.HIDDEN)
  public IDefaultBusinessCalendar getBusinessCalendar() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessCreatorUser() throws PersistencyException {
    return businessCreatorUser;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Integer getBusinessMainContactId() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessMainContactName() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getBusinessMilestoneTimestamp() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessObjectCode() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public BusinessDuration getBusinessRuntime() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getBusinessStartTimestamp() throws PersistencyException {
    return null;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public ICase getCase() throws PersistencyException {
    return remoteCase;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Number getCustomDecimalField1() throws PersistencyException {
    return customDecimalField1;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Number getCustomDecimalField2() throws PersistencyException {
    return customDecimalField2;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Number getCustomDecimalField3() throws PersistencyException {
    return customDecimalField3;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Number getCustomDecimalField4() throws PersistencyException {
    return customDecimalField4;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Number getCustomDecimalField5() throws PersistencyException {
    return customDecimalField5;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Date getCustomTimestampField1() throws PersistencyException {
    return customTimestampField1;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Date getCustomTimestampField2() throws PersistencyException {
    return customTimestampField2;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Date getCustomTimestampField3() throws PersistencyException {
    return customTimestampField3;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Date getCustomTimestampField4() throws PersistencyException {
    return customTimestampField4;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public Date getCustomTimestampField5() throws PersistencyException {
    return customTimestampField5;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public String getCustomVarCharField1() throws PersistencyException {
    return customVarCharField1;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public String getCustomVarCharField2() throws PersistencyException {
    return customVarCharField2;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public String getCustomVarCharField3() throws PersistencyException {
    return customVarCharField3;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public String getCustomVarCharField4() throws PersistencyException {
    return customVarCharField4;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public String getCustomVarCharField5() throws PersistencyException {
    return customVarCharField5;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getDelayTimestamp() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getDescription() throws PersistencyException {
    return description;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getDisplayDescriptionTemplate() throws PersistencyException {
    return displayDescriptionTemplate;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getDisplayNameTemplate() throws PersistencyException {
    return displayNameTemplate;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public ITaskEnd getEnd() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public CompositeObject getEndProcessData() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public ITaskSwitchEvent getEndSwitchEvent() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getEndTimestamp() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public ITask getExpiredCreatorTask() throws PersistencyException {
    return expiredCreatorTask;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public BusinessDuration getExpiresInBusinessDuration() {
    return expiresInBusinessDuration;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public ISecurityMember getExpiryActivator() throws PersistencyException {
    return expiryActivator;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getExpiryActivatorName() throws PersistencyException {
    return expiryActivatorName;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public WorkflowPriority getExpiryPriority() throws PersistencyException {
    return expiryPriority;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getExpiryTaskStartElementPid() throws PersistencyException {
    return expiryTaskStartElementPid;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getExpiryTimestamp() throws PersistencyException {
    return expiryTimestamp;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getFullRequestPath() throws PersistencyException {
    return fullRequestPath;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public long getId() {
    return id;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.HIDDEN)
  @Deprecated
  public int getIdentifier() {
    return 0;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IIntermediateEvent getIntermediateEvent() throws PersistencyException {
    return null;
  }

  @Override
  public IProcessData getInternalProcessData() throws PersistencyException {
    return null;
  }

  @Override
  public IProcessData getInternalProcessElementProcessData(String arg0) throws PersistencyException {
    return null;
  }

  @Override
  public IProcessData getInternalStartProcessData() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getKindCode() throws PersistencyException {
    return kindCode;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getKindName() throws PersistencyException {
    return kindName;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getName() throws PersistencyException {
    return name;
  }

  @Override
  public int getNumberOfFailures() throws PersistencyException {
    return 0;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public Integer getNumberOfResumes() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public ISecurityMember getOriginalActivator() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getOriginalActivatorName() throws PersistencyException {
    return originalActivatorName;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public WorkflowPriority getOriginalPriority() throws PersistencyException {
    return originalPriority;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public IPageArchive getPageArchive(long pageArchiveIdentifier) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.HIDDEN)
  @Deprecated
  public IPageArchive getPageArchive(int arg0) throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<IPageArchive> getPageArchives() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public WorkflowPriority getPriority() throws PersistencyException {
    return priority;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getProcessCategoryCode() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getProcessCategoryName() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getProcessCode() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IProcessModel getProcessModel() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IWorkflowProcessModelVersion getProcessModelVersion() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getProcessName() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getRequestPath() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public ITaskStart getStart() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public CompositeObject getStartProcessData() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public ITaskSwitchEvent getStartSwitchEvent() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getStartTimestamp() throws PersistencyException {
    return startTimestamp;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public TaskState getState() throws PersistencyException {
    return state;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getSubTypeCode() throws PersistencyException {
    return subTypeCode;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getSubTypeName() throws PersistencyException {
    return subTypeName;
  }

  @Override
  public IIntermediateEvent getTimeoutedCreatorIntermediateEvent() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getTypeCode() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getTypeName() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public IWorkflowSession getWorkerSession() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public IUser getWorkerUser() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getWorkerUserName() throws PersistencyException {
    return workerUserName;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IWorkflowContext getWorkflowContext() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public List<IWorkflowEvent> getWorkflowEvents() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public Duration getWorkingTime() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public boolean isExpired() throws PersistencyException {
    return expired;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public boolean isExpiryTask() throws PersistencyException {
    return false;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public boolean isPersistent() {
    return false;
  }

  @Override
  public boolean isUpdatedOnStart() throws PersistencyException {
    return false;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void reset() throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setActivator(ISecurityMember activator) throws PersistencyException {
    this.activator = (RemoteSecurityMember) activator;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setBusinessCalendar(IBusinessCalendar calendar) {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessMilestoneTimestamp(Date businessMilestoneTimestamp) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomDecimalField1(Number customDecimalField1) throws PersistencyException {
    this.customDecimalField1 = customDecimalField1;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomDecimalField2(Number customDecimalField2) throws PersistencyException {
    this.customDecimalField2 = customDecimalField2;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomDecimalField3(Number customDecimalField3) throws PersistencyException {
    this.customDecimalField3 = customDecimalField3;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomDecimalField4(Number customDecimalField4) throws PersistencyException {
    this.customDecimalField4 = customDecimalField4;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomDecimalField5(Number customDecimalField5) throws PersistencyException {
    this.customDecimalField5 = customDecimalField5;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomTimestampField1(Date customTimestampField1) throws PersistencyException {
    this.customTimestampField1 = customTimestampField1;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomTimestampField2(Date customTimestampField2) throws PersistencyException {
    this.customTimestampField2 = customTimestampField2;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomTimestampField3(Date customTimestampField3) throws PersistencyException {
    this.customTimestampField3 = customTimestampField3;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomTimestampField4(Date customTimestampField4) throws PersistencyException {
    this.customTimestampField4 = customTimestampField4;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomTimestampField5(Date customTimestampField5) throws PersistencyException {
    this.customTimestampField5 = customTimestampField5;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomVarCharField1(String customVarCharField1) throws PersistencyException {
    this.customVarCharField1 = customVarCharField1;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomVarCharField2(String customVarCharField2) throws PersistencyException {
    this.customVarCharField2 = customVarCharField2;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomVarCharField3(String customVarCharField3) throws PersistencyException {
    this.customVarCharField3 = customVarCharField3;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomVarCharField4(String customVarCharField4) throws PersistencyException {
    this.customVarCharField4 = customVarCharField4;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setCustomVarCharField5(String customVarCharField5) throws PersistencyException {
    this.customVarCharField5 = customVarCharField5;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setDelayTimestamp(Date newDelayTimestamp) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setDescription(String description) throws PersistencyException {
    this.description = description;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setExpiryActivator(ISecurityMember expiryActivator) throws PersistencyException {
    this.expiryActivator = (RemoteSecurityMember) expiryActivator;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setExpiryPriority(WorkflowPriority expiryPriority) throws PersistencyException {
    this.expiryPriority = expiryPriority;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setExpiryTimestamp(Date newExpiryTimestamp) throws PersistencyException {
    this.expiryTimestamp = newExpiryTimestamp;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setKind(String kindCode, String kindName) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setName(String name) throws PersistencyException {
    this.name = name;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setOriginalActivator(ISecurityMember activator) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setOriginalPriority(WorkflowPriority originalPriority) throws PersistencyException {
    this.originalPriority = originalPriority;

  }

  @Override
  public void updateTask(TaskUpdateDefinition arg0, ISecurityMember arg1) throws PersistencyException {

  }

  @Override
  public void writeInternalStartProcessData(IProcessData arg0) throws PersistencyException {

  }

  /**
   * Set start time stamp.
   *
   * @param startTimestamp start time stamp
   * @throws PersistencyException PersistencyException
   */
  public void setStartTimestamp(Date startTimestamp) throws PersistencyException {
    this.startTimestamp = startTimestamp;
  }

  /**
   * Set business creator user.
   *
   * @param businessCreatorUser business creator user
   * @throws PersistencyException PersistencyException
   */
  public void setBusinessCreatorUser(String businessCreatorUser) throws PersistencyException {
    this.businessCreatorUser = businessCreatorUser;
  }

  /**
   * Set display description template.
   *
   * @param displayDescriptionTemplate display description template
   * @throws PersistencyException PersistencyException
   */
  public void setDisplayDescriptionTemplate(String displayDescriptionTemplate) throws PersistencyException {
    this.displayDescriptionTemplate = displayDescriptionTemplate;
  }

  /**
   * Set display name template.
   *
   * @param displayNameTemplate display name template
   * @throws PersistencyException PersistencyException
   */
  public void setDisplayNameTemplate(String displayNameTemplate) throws PersistencyException {
    this.displayNameTemplate = displayNameTemplate;
  }

  /**
   * Set expriry activator name.
   *
   * @param expiryActivatorName expriry activator name
   * @throws PersistencyException PersistencyException
   */
  public void setExpiryActivatorName(String expiryActivatorName) throws PersistencyException {
    this.expiryActivatorName = expiryActivatorName;
  }

  /**
   * Set task id.
   *
   * @param id task id
   * @throws PersistencyException PersistencyException
   */
  public void setId(Long id) throws PersistencyException {
    this.id = id;
  }

  /**
   * Set task expired.
   *
   * @param expired expired or not
   * @throws PersistencyException PersistencyException
   */
  public void setIsExpired(boolean expired) throws PersistencyException {
    this.expired = expired;
  }

  /**
   * Task task priority.
   *
   * @param priority task priority
   * @throws PersistencyException PersistencyException
   */
  public void setPriority(WorkflowPriority priority) throws PersistencyException {
    this.priority = priority;
  }

  /**
   * Set full request path.
   *
   * @param fullRequestPath full request path
   * @throws PersistencyException PersistencyException
   */
  public void setFullRequestPath(String fullRequestPath) throws PersistencyException {
    this.fullRequestPath = fullRequestPath;
  }

  /**
   * Set kind code.
   *
   * @param kindCode kind code
   * @throws PersistencyException PersistencyException
   */
  public void setKindCode(String kindCode) throws PersistencyException {
    this.kindCode = kindCode;
  }

  /**
   * Set kind name.
   *
   * @param kindName kind name.
   * @throws PersistencyException PersistencyException
   */
  public void setKindName(String kindName) throws PersistencyException {
    this.kindName = kindName;
  }

  /**
   * Set original activator name.
   *
   * @param originalActivatorName original activator name
   * @throws PersistencyException PersistencyException
   */
  public void setOriginalActivatorName(String originalActivatorName) throws PersistencyException {
    this.originalActivatorName = originalActivatorName;
  }

  /**
   * Set task state.
   *
   * @param state task state
   * @throws PersistencyException PersistencyException
   */
  public void setState(TaskState state) throws PersistencyException {
    this.state = state;
  }

  /**
   * Set worker username.
   *
   * @param workerUserName username of worker
   * @throws PersistencyException PersistencyException
   */
  public void setWorkerUserName(String workerUserName) throws PersistencyException {
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
  public void setApplication(IApplication remoteApplication) {
    this.remoteApplication = (RemoteApplication) remoteApplication;
  }

  /**
   * Set case.
   *
   * @param remoteCase remote case
   */
  public void setCase(ICase remoteCase) {
    this.remoteCase = (RemoteCase) remoteCase;
  }

  /**
   * Set expired creator task.
   *
   * @param expiredCreatorTask expired task
   */
  public void setExpiredCreatorTask(ITask expiredCreatorTask) {
    this.expiredCreatorTask = (RemoteTask) expiredCreatorTask;
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

  @Override
  public String getFailReason() {
    return null;
  }

  @Override
  public boolean isOfflineTask() throws PersistencyException {
    return false;
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

  public void setCanChangePriority(boolean canUserChangePriority) {
    this.canChangePriority = canUserChangePriority;
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

  @Override
  public ResumeTaskResult canUserResumeTask(ISession session) throws PersistencyException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ResumeTaskResult canResumeWithRequestPath(String requestPath) throws PersistencyException {
    return null;
  }

  @Override
  public ILocationService locations() {
    return null;
  }
}
