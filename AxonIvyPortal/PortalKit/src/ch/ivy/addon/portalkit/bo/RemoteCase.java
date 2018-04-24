package ch.ivy.addon.portalkit.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

/**
 * Bean for remote case.
 *
 */
public class RemoteCase {

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

  private RemoteApplication application;

  private Server server;

  private Map<String, String> additionalProperties = new HashMap<String, String>();

  private String processModelName;
  private int processModelVersionNumber;
  private List<RemoteNote> remoteNotes;

  private boolean canDestroy;
  private boolean canChangeDescription;
  private boolean canChangeName;
  private boolean isBusinessCase;
  
  private String serverUrl;

  public String getAdditionalProperty(String name) throws PersistencyException {
    return additionalProperties.get(name);
  }

  public List<String> getAdditionalPropertyNames() throws PersistencyException {
    List<String> props = new ArrayList<String>();
    for (String name : additionalProperties.keySet()) {
      props.add(name);
    }
    return props;
  }

  public void setAdditionalProperty(String name, String value) throws PersistencyException {
    this.additionalProperties.put(name, value);
  }

  public RemoteApplication getApplication() throws PersistencyException {
    return this.application;
  }

  public Integer getBusinessCorrespondentId() throws PersistencyException {
    return businessCorrespondentId;
  }

  public String getBusinessCreatorUser() throws PersistencyException {
    return businessCreatorUser;
  }

  public String getBusinessMainContactDocumentDatabaseCode() throws PersistencyException {
    return businessMainContactDocumentDatabaseCode;
  }

  public String getBusinessMainContactFolderId() throws PersistencyException {
    return businessMainContactFolderId;
  }

  public Integer getBusinessMainContactId() throws PersistencyException {
    return businessMainContactId;
  }

  public String getBusinessMainContactName() throws PersistencyException {
    return businessMainContactName;
  }

  public String getBusinessMainContactType() throws PersistencyException {
    return businessMainContactType;
  }

  public Date getBusinessMilestoneTimestamp() throws PersistencyException {
    return businessMilestoneTimestamp;
  }

  public String getBusinessObjectCode() throws PersistencyException {
    return businessObjectCode;
  }

  public String getBusinessObjectDocumentDatabaseCode() throws PersistencyException {
    return businessObjectDocumentDatabaseCode;
  }

  public String getBusinessObjectFolderId() throws PersistencyException {
    return businessObjectFolderId;
  }

  public String getBusinessObjectName() throws PersistencyException {
    return businessObjectName;
  }

  public String getBusinessPriority() throws PersistencyException {
    return businessPriority;
  }

  public Date getBusinessStartTimestamp() throws PersistencyException {
    return businessStartTimestamp;
  }

  public String getCreatorUserName() throws PersistencyException {
    return creatorUserName;
  }

  public String getCreatorFullName() {
    return creatorFullName;
  }

  public void setCreatorFullName(String creatorFullName) {
    this.creatorFullName = creatorFullName;
  }

  public Number getCustomDecimalField1() throws PersistencyException {
    return customDecimalField1;
  }

  public Number getCustomDecimalField2() throws PersistencyException {
    return customDecimalField2;
  }

  public Number getCustomDecimalField3() throws PersistencyException {
    return customDecimalField3;
  }

  public Number getCustomDecimalField4() throws PersistencyException {
    return customDecimalField4;
  }

  public Number getCustomDecimalField5() throws PersistencyException {
    return customDecimalField5;
  }

  public Date getCustomTimestampField1() throws PersistencyException {
    return customTimestampField1;
  }

  public Date getCustomTimestampField2() throws PersistencyException {
    return customTimestampField2;
  }

  public Date getCustomTimestampField3() throws PersistencyException {
    return customTimestampField3;
  }

  public Date getCustomTimestampField4() throws PersistencyException {
    return customTimestampField4;
  }

  public Date getCustomTimestampField5() throws PersistencyException {
    return customTimestampField5;
  }

  public String getCustomVarCharField1() throws PersistencyException {
    return customVarCharField1;
  }

  public String getCustomVarCharField2() throws PersistencyException {
    return customVarCharField2;
  }

  public String getCustomVarCharField3() throws PersistencyException {
    return customVarCharField3;
  }

  public String getCustomVarCharField4() throws PersistencyException {
    return customVarCharField4;
  }

  public String getCustomVarCharField5() throws PersistencyException {
    return customVarCharField5;
  }

  public String getDescription() throws PersistencyException {
    return description;
  }

  public Date getEndTimestamp() throws PersistencyException {
    return endTimestamp;
  }

  public long getId() {
    return id;
  }

  public String getName() throws PersistencyException {
    return name;
  }

  public WorkflowPriority getPriority() throws PersistencyException {
    return priority;
  }

  public String getProcessCategoryCode() throws PersistencyException {
    return processCategoryCode;
  }

  public String getProcessCategoryName() throws PersistencyException {
    return processCategoryName;
  }

  public String getProcessCode() throws PersistencyException {
    return processCode;
  }

  public String getProcessName() throws PersistencyException {
    return processName;
  }

  public Date getStartTimestamp() throws PersistencyException {
    return startTimestamp;
  }

  public CaseState getState() throws PersistencyException {
    return state;
  }

  public String getSubTypeCode() throws PersistencyException {
    return subTypeCode;
  }

  public String getSubTypeName() throws PersistencyException {
    return subTypeName;
  }

  public String getTypeCode() throws PersistencyException {
    return typeCode;
  }

  public String getTypeName() throws PersistencyException {
    return typeName;
  }

  public boolean isPersistent() {
    return false;
  }

  public void setBusinessCorrespondentId(Integer businessCorrespondentId) throws PersistencyException {
    this.businessCorrespondentId = businessCorrespondentId;

  }

  public void setBusinessCreatorUser(String businessCreatorUser) throws PersistencyException {
    this.businessCreatorUser = businessCreatorUser;

  }

  public void setBusinessMainContactDocumentDatabaseCode(String businessMainContactDocumentDatabaseCode)
      throws PersistencyException {
    this.businessMainContactDocumentDatabaseCode = businessMainContactDocumentDatabaseCode;

  }

  public void setBusinessMainContactFolderId(String businessMainContactFolderId) throws PersistencyException {
    this.businessMainContactFolderId = businessMainContactFolderId;

  }

  public void setBusinessMainContactId(Integer businessMainContactId) throws PersistencyException {
    this.businessMainContactId = businessMainContactId;

  }

  public void setBusinessMainContactName(String businessMainContactName) throws PersistencyException {
    this.businessMainContactName = businessMainContactName;

  }

  public void setBusinessMainContactType(String businessMainContactType) throws PersistencyException {
    this.businessMainContactType = businessMainContactType;

  }

  public void setBusinessMilestoneTimestamp(Date businessMilestoneTimestamp) throws PersistencyException {
    this.businessMilestoneTimestamp = businessMilestoneTimestamp;

  }

  public void setBusinessObjectCode(String businessObjectCode) throws PersistencyException {
    this.businessObjectCode = businessObjectCode;

  }

  public void setBusinessObjectDocumentDatabaseCode(String businessObjectDocumentDatabaseCode)
      throws PersistencyException {
    this.businessObjectDocumentDatabaseCode = businessObjectDocumentDatabaseCode;

  }

  public void setBusinessObjectFolderId(String businessObjectFolderId) throws PersistencyException {
    this.businessObjectFolderId = businessObjectFolderId;

  }

  public void setBusinessObjectName(String businessObjectName) throws PersistencyException {
    this.businessObjectName = businessObjectName;

  }

  public void setBusinessPriority(String businessPriority) throws PersistencyException {
    this.businessPriority = businessPriority;

  }

  public void setBusinessStartTimestamp(Date businessStartTimestamp) throws PersistencyException {
    this.businessStartTimestamp = businessStartTimestamp;

  }

  public void setCustomDecimalField1(Number customDecimalField1) throws PersistencyException {
    this.customDecimalField1 = customDecimalField1;

  }

  public void setCustomDecimalField2(Number customDecimalField2) throws PersistencyException {
    this.customDecimalField2 = customDecimalField2;

  }

  public void setCustomDecimalField3(Number customDecimalField3) throws PersistencyException {
    this.customDecimalField3 = customDecimalField3;

  }

  public void setCustomDecimalField4(Number customDecimalField4) throws PersistencyException {
    this.customDecimalField4 = customDecimalField4;

  }

  public void setCustomDecimalField5(Number customDecimalField5) throws PersistencyException {
    this.customDecimalField5 = customDecimalField5;

  }

  public void setCustomTimestampField1(Date customTimestampField1) throws PersistencyException {
    this.customTimestampField1 = customTimestampField1;

  }

  public void setCustomTimestampField2(Date customTimestampField2) throws PersistencyException {
    this.customTimestampField2 = customTimestampField2;

  }

  public void setCustomTimestampField3(Date customTimestampField3) throws PersistencyException {
    this.customTimestampField3 = customTimestampField3;

  }

  public void setCustomTimestampField4(Date customTimestampField4) throws PersistencyException {
    this.customTimestampField4 = customTimestampField4;

  }

  public void setCustomTimestampField5(Date customTimestampField5) throws PersistencyException {
    this.customTimestampField5 = customTimestampField5;

  }

  public void setCustomVarCharField1(String customVarCharField1) throws PersistencyException {
    this.customVarCharField1 = customVarCharField1;

  }

  public void setCustomVarCharField2(String customVarCharField2) throws PersistencyException {
    this.customVarCharField2 = customVarCharField2;

  }

  public void setCustomVarCharField3(String customVarCharField3) throws PersistencyException {
    this.customVarCharField3 = customVarCharField3;

  }

  public void setCustomVarCharField4(String customVarCharField4) throws PersistencyException {
    this.customVarCharField4 = customVarCharField4;

  }

  public void setCustomVarCharField5(String customVarCharField5) throws PersistencyException {
    this.customVarCharField5 = customVarCharField5;

  }

  public void setDescription(String description) throws PersistencyException {
    this.description = description;

  }

  public void setName(String name) throws PersistencyException {
    this.name = name;

  }

  public void setPriority(WorkflowPriority priority) throws PersistencyException {
    this.priority = priority;

  }

  @SuppressWarnings("unused")
  public void setProcess(String processCode, String processName) throws PersistencyException {

  }

  @SuppressWarnings("unused")
  public void setProcessCategory(String processCategoryCode, String processCategoryName) throws PersistencyException {

  }

  public void setSubType(String subTypeCode, String subTypeName) throws PersistencyException {
    this.subTypeCode = subTypeCode;
    this.subTypeName = subTypeName;

  }

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
  public void setApplication(RemoteApplication application) {
    this.application = application;
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

	public boolean isBusinessCase() throws PersistencyException {
		return isBusinessCase;
	}
	
	public void setBusinessCase(boolean isBusinessCase) {
		this.isBusinessCase = isBusinessCase;
	}

	public String getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(String serverUrl) {
    this.serverUrl = serverUrl;
  }

}
