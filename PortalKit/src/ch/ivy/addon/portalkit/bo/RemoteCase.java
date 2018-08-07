package ch.ivy.addon.portalkit.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivyteam.api.IvyScriptVisibility;
import ch.ivyteam.api.PublicAPI;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.calendar.IBusinessCalendar;
import ch.ivyteam.ivy.application.calendar.IDefaultBusinessCalendar;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.scripting.objects.BusinessDuration;
import ch.ivyteam.ivy.scripting.objects.Duration;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.IPageArchive;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.ivy.workflow.IWorkflowEvent;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.document.IDocumentService;

/**
 * Bean for remote case.
 *
 * @author maonguyen
 */
public class RemoteCase implements ICase {

  private Integer businessCorrespondentId;
  private String businessCreatorUser;
  private String businessMainContactDocumentDatabaseCode;
  private String businessMainContactFolderId;
  private Integer businessMainContactId;
  private String businessMainContactName;
  private String businessMainContactType;
  private Date businessMilestoneTimestamp;
  private String businessObjectCode;
  private String businessObjectDocumentDatabaseCode;
  private String businessObjectFolderId;
  private String businessObjectName;
  private String businessPriority;
  private Date businessStartTimestamp;

  private Number customDecimalField1;
  private Number customDecimalField2;
  private Number customDecimalField3;
  private Number customDecimalField4;
  private Number customDecimalField5;

  private Date customTimestampField1;
  private Date customTimestampField2;
  private Date customTimestampField3;
  private Date customTimestampField4;
  private Date customTimestampField5;

  private String customVarCharField1;
  private String customVarCharField2;
  private String customVarCharField3;
  private String customVarCharField4;
  private String customVarCharField5;

  private String description;
  private String name;

  private WorkflowPriority priority;

  private String creatorUserName;
  private String creatorFullName;

  private Date endTimestamp;
  private Long id;

  private String processCategoryCode;
  private String processCategoryName;
  private String processCode;
  private String processName;

  private Date startTimestamp;
  private CaseState state;
  private String subTypeCode;
  private String subTypeName;

  private String typeCode;
  private String typeName;

  private IApplication application;

  private Server server;

  private Map<String, String> additionalProperties = new HashMap<String, String>();

  private String processModelName;
  private int processModelVersionNumber;
  private List<RemoteNote> remoteNotes;

  private boolean canDestroy;

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
    return additionalProperties.get(name);
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<String> getAdditionalPropertyNames() throws PersistencyException {
    List<String> props = new ArrayList<String>();
    for (String name : additionalProperties.keySet()) {
      props.add(name);
    }
    return props;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setAdditionalProperty(String name, String value) throws PersistencyException {
    this.additionalProperties.put(name, value);

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void destroy() throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getActiveEnvironment() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<ITask> getActiveTasks() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IApplication getApplication() throws PersistencyException {
    return this.application;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.HIDDEN)
  public IDefaultBusinessCalendar getBusinessCalendar() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Integer getBusinessCorrespondentId() throws PersistencyException {
    return businessCorrespondentId;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessCreatorUser() throws PersistencyException {
    return businessCreatorUser;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessMainContactDocumentDatabaseCode() throws PersistencyException {
    return businessMainContactDocumentDatabaseCode;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessMainContactFolderId() throws PersistencyException {
    return businessMainContactFolderId;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Integer getBusinessMainContactId() throws PersistencyException {
    return businessMainContactId;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessMainContactName() throws PersistencyException {
    return businessMainContactName;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessMainContactType() throws PersistencyException {
    return businessMainContactType;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getBusinessMilestoneTimestamp() throws PersistencyException {
    return businessMilestoneTimestamp;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessObjectCode() throws PersistencyException {
    return businessObjectCode;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessObjectDocumentDatabaseCode() throws PersistencyException {
    return businessObjectDocumentDatabaseCode;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessObjectFolderId() throws PersistencyException {
    return businessObjectFolderId;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessObjectName() throws PersistencyException {
    return businessObjectName;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getBusinessPriority() throws PersistencyException {
    return businessPriority;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public BusinessDuration getBusinessRuntime() {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getBusinessStartTimestamp() throws PersistencyException {
    return businessStartTimestamp;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public ITask getCreatorTask() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public IUser getCreatorUser() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getCreatorUserName() throws PersistencyException {
    return creatorUserName;
  }

  public String getCreatorFullName() {
    return creatorFullName;
  }

  public void setCreatorFullName(String creatorFullName) {
    this.creatorFullName = creatorFullName;
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
  public String getDescription() throws PersistencyException {
    return description;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getDisplayDescriptionTemplate() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public String getDisplayNameTemplate() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getEndTimestamp() throws PersistencyException {
    return endTimestamp;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
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
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getName() throws PersistencyException {
    return name;
  }

  @Override
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
    return processCategoryCode;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getProcessCategoryName() throws PersistencyException {
    return processCategoryName;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getProcessCode() throws PersistencyException {
    return processCode;
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
    return processName;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public IProcessStart getProcessStart() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public Date getStartTimestamp() throws PersistencyException {
    return startTimestamp;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public CaseState getState() throws PersistencyException {
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
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public List<ITask> getTasks() throws PersistencyException {
    return null;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getTypeCode() throws PersistencyException {
    return typeCode;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.NOVICE)
  public String getTypeName() throws PersistencyException {
    return typeName;
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
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public boolean isPersistent() {
    return false;
  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setActiveEnvironment(String environmentName) throws NoSuchElementException, IllegalArgumentException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setBusinessCalendar(IBusinessCalendar calendar) {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessCorrespondentId(Integer businessCorrespondentId) throws PersistencyException {
    this.businessCorrespondentId = businessCorrespondentId;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessCreatorUser(String businessCreatorUser) throws PersistencyException {
    this.businessCreatorUser = businessCreatorUser;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessMainContactDocumentDatabaseCode(String businessMainContactDocumentDatabaseCode)
      throws PersistencyException {
    this.businessMainContactDocumentDatabaseCode = businessMainContactDocumentDatabaseCode;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessMainContactFolderId(String businessMainContactFolderId) throws PersistencyException {
    this.businessMainContactFolderId = businessMainContactFolderId;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessMainContactId(Integer businessMainContactId) throws PersistencyException {
    this.businessMainContactId = businessMainContactId;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessMainContactName(String businessMainContactName) throws PersistencyException {
    this.businessMainContactName = businessMainContactName;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessMainContactType(String businessMainContactType) throws PersistencyException {
    this.businessMainContactType = businessMainContactType;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessMilestoneTimestamp(Date businessMilestoneTimestamp) throws PersistencyException {
    this.businessMilestoneTimestamp = businessMilestoneTimestamp;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessObjectCode(String businessObjectCode) throws PersistencyException {
    this.businessObjectCode = businessObjectCode;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessObjectDocumentDatabaseCode(String businessObjectDocumentDatabaseCode)
      throws PersistencyException {
    this.businessObjectDocumentDatabaseCode = businessObjectDocumentDatabaseCode;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessObjectFolderId(String businessObjectFolderId) throws PersistencyException {
    this.businessObjectFolderId = businessObjectFolderId;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessObjectName(String businessObjectName) throws PersistencyException {
    this.businessObjectName = businessObjectName;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessPriority(String businessPriority) throws PersistencyException {
    this.businessPriority = businessPriority;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setBusinessStartTimestamp(Date businessStartTimestamp) throws PersistencyException {
    this.businessStartTimestamp = businessStartTimestamp;

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
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setDescription(String description) throws PersistencyException {
    this.description = description;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setName(String name) throws PersistencyException {
    this.name = name;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.EXPERT)
  public void setPriority(WorkflowPriority priority) throws PersistencyException {
    this.priority = priority;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setProcess(String processCode, String processName) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setProcessCategory(String processCategoryCode, String processCategoryName) throws PersistencyException {

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setSubType(String subTypeCode, String subTypeName) throws PersistencyException {
    this.subTypeCode = subTypeCode;
    this.subTypeName = subTypeName;

  }

  @Override
  @PublicAPI(IvyScriptVisibility.ADVANCED)
  public void setType(String typeCode, String typeName) throws PersistencyException {
    this.typeCode = typeCode;
    this.typeName = typeName;

  }

  /**
   * Gets the additionalProperties
   *
   * @return Returns the additionalProperties
   */
  public Map<String, String> getAdditionalProperties() {
    return additionalProperties;
  }

  /**
   * Sets the additionalProperties
   *
   * @param additionalProperties
   *          The additionalProperties to set
   */
  public void setAdditionalProperties(Map<String, String> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }

  /**
   * Sets the creatorUserName
   *
   * @param creatorUserName
   *          The creatorUserName to set
   */
  public void setCreatorUserName(String creatorUserName) {
    this.creatorUserName = creatorUserName;
  }

  /**
   * Sets the endTimestamp
   *
   * @param endTimestamp
   *          The endTimestamp to set
   */
  public void setEndTimestamp(Date endTimestamp) {
    this.endTimestamp = endTimestamp;
  }

  /**
   * Sets the id
   *
   * @param id
   *          The id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Sets the processCategoryCode
   *
   * @param processCategoryCode
   *          The processCategoryCode to set
   */
  public void setProcessCategoryCode(String processCategoryCode) {
    this.processCategoryCode = processCategoryCode;
  }

  /**
   * Sets the processCategoryName
   *
   * @param processCategoryName
   *          The processCategoryName to set
   */
  public void setProcessCategoryName(String processCategoryName) {
    this.processCategoryName = processCategoryName;
  }

  /**
   * Sets the processCode
   *
   * @param processCode
   *          The processCode to set
   */
  public void setProcessCode(String processCode) {
    this.processCode = processCode;
  }

  /**
   * Sets the processName
   *
   * @param processName
   *          The processName to set
   */
  public void setProcessName(String processName) {
    this.processName = processName;
  }

  /**
   * Sets the startTimestamp
   *
   * @param startTimestamp
   *          The startTimestamp to set
   */
  public void setStartTimestamp(Date startTimestamp) {
    this.startTimestamp = startTimestamp;
  }

  /**
   * Sets the state
   *
   * @param state
   *          The state to set
   */
  public void setState(CaseState state) {
    this.state = state;
  }

  /**
   * Sets the subTypeCode
   *
   * @param subTypeCode
   *          The subTypeCode to set
   */
  public void setSubTypeCode(String subTypeCode) {
    this.subTypeCode = subTypeCode;
  }

  /**
   * Sets the subTypeName
   *
   * @param subTypeName
   *          The subTypeName to set
   */
  public void setSubTypeName(String subTypeName) {
    this.subTypeName = subTypeName;
  }

  /**
   * Sets the typeCode
   *
   * @param typeCode
   *          The typeCode to set
   */
  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  /**
   * Sets the typeName
   *
   * @param typeName
   *          The typeName to set
   */
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  /**
   * Sets the application
   *
   * @param application
   *          The application to set
   */
  public void setApplication(IApplication application) {
    this.application = application;
  }

  @Override
  public IDocumentService documents() {
    return null;
  }

  public Server getServer() {
    return server;
  }

  public void setServer(Server server) {
    this.server = server;
  }

  public String getProcessModelName() {
    return processModelName;
  }

  public void setProcessModelName(String processModelName) {
    this.processModelName = processModelName;
  }

  public int getProcessModelVersionNumber() {
    return processModelVersionNumber;
  }

  public void setProcessModelVersionNumber(int processModelVersionNumber) {
    this.processModelVersionNumber = processModelVersionNumber;
  }

  public List<RemoteNote> getRemoteNotes() {
    return remoteNotes;
  }

  public void setRemoteNotes(List<RemoteNote> remoteNotes) {
    this.remoteNotes = remoteNotes;
  }

  public boolean isCanDestroy() {
    return canDestroy;
  }

  public void setCanDestroy(boolean canDestroy) {
    this.canDestroy = canDestroy;
  }

  @Override
  public ITask getFirstTask() {
    throw new UnsupportedOperationException("Not support yet.");
  }
}
