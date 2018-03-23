package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteApplication;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.ws.addon.IvyCase;
import ch.ivy.ws.addon.IvyTask;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.List;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;


/**
 * This class creates Book objects from a given recordset.<br>
 * 
 * @author tauser
 */
public class RemoteCaseMapper {


  /**
   * Convert web service case to remote case.
   * 
   * @param ivyCase web service case
   * @return remote case.
   * @see IvyCase
   * @see RemoteCase
   */
  protected static RemoteCase mapCase(IvyCase ivyCase) {
    RemoteCase result = new RemoteCase();

    result.setBusinessCorrespondentId(ivyCase.getBusinessCorrespondentId());
    result.setBusinessCreatorUser(ivyCase.getBusinessCreatorUser());
    result.setBusinessMainContactDocumentDatabaseCode(ivyCase.getBusinessMainContactDocumentDatabaseCode());
    result.setBusinessMainContactFolderId(ivyCase.getBusinessMainContactFolderId());
    result.setBusinessMainContactId(ivyCase.getBusinessMainContactId());
    result.setBusinessMainContactName(ivyCase.getBusinessMainContactName());
    result.setBusinessMainContactType(ivyCase.getBusinessMainContactType());
    result.setBusinessMilestoneTimestamp(ivyCase.getBusinessMilestoneTimestamp() == null ? null : ivyCase
        .getBusinessMilestoneTimestamp().getTime());
    result.setBusinessObjectCode(ivyCase.getBusinessObjectCode());
    result.setBusinessObjectDocumentDatabaseCode(ivyCase.getBusinessObjectDocumentDatabaseCode());
    result.setBusinessObjectFolderId(ivyCase.getBusinessObjectFolderId());
    result.setBusinessObjectName(ivyCase.getBusinessObjectName());
    result.setBusinessPriority(ivyCase.getBusinessPriority());
    result.setBusinessStartTimestamp(ivyCase.getBusinessStartTimestamp() == null ? null : ivyCase
        .getBusinessStartTimestamp().getTime());

    result.setCreatorUserName(ivyCase.getCreatorUserName());
    result.setCreatorFullName(ivyCase.getCreatorFullName());

    result.setCustomDecimalField1(ivyCase.getCustomDecimalField1());
    result.setCustomDecimalField2(ivyCase.getCustomDecimalField2());
    result.setCustomDecimalField3(ivyCase.getCustomDecimalField3());
    result.setCustomDecimalField4(ivyCase.getCustomDecimalField4());
    result.setCustomDecimalField5(ivyCase.getCustomDecimalField5());
    
    result.setCustomTimestampField1(ivyCase.getCustomTimestampField1() == null ? null : ivyCase
        .getCustomTimestampField1().getTime());
    result.setCustomTimestampField2(ivyCase.getCustomTimestampField2() == null ? null : ivyCase
        .getCustomTimestampField2().getTime());
    result.setCustomTimestampField3(ivyCase.getCustomTimestampField3() == null ? null : ivyCase
        .getCustomTimestampField3().getTime());
    result.setCustomTimestampField4(ivyCase.getCustomTimestampField4() == null ? null : ivyCase
        .getCustomTimestampField4().getTime());
    result.setCustomTimestampField5(ivyCase.getCustomTimestampField5() == null ? null : ivyCase
        .getCustomTimestampField5().getTime());

    result.setCustomVarCharField1(ivyCase.getCustomVarCharField1());
    result.setCustomVarCharField2(ivyCase.getCustomVarCharField2());
    result.setCustomVarCharField3(ivyCase.getCustomVarCharField3());
    result.setCustomVarCharField4(ivyCase.getCustomVarCharField4());
    result.setCustomVarCharField5(ivyCase.getCustomVarCharField5());

    result.setDescription(ivyCase.getDescription());
    result.setEndTimestamp(ivyCase.getEndTimestamp() == null ? null : ivyCase.getEndTimestamp().getTime());
    result.setId(ivyCase.getId());
    result.setName(ivyCase.getName());
    if (ivyCase.getPriority() != null) {
      result.setPriority(WorkflowPriority.valueOf(ivyCase.getPriority()));
    }

    result.setProcessCategoryCode(ivyCase.getProcessCategoryCode());
    result.setProcessCategoryName(ivyCase.getProcessCategoryName());
    result.setProcessCode(ivyCase.getProcessCode());
    result.setProcessName(ivyCase.getProcessName());

    result.setStartTimestamp(ivyCase.getStartTimestamp() == null ? null : ivyCase.getStartTimestamp().getTime());
    if (ivyCase.getState() != null) {
      result.setState(CaseState.valueOf(ivyCase.getState()));
    }
    result.setSubTypeCode(ivyCase.getSubTypeCode());
    result.setSubTypeName(ivyCase.getSubTypeName());

    result.setTypeCode(ivyCase.getTypeCode());
    result.setTypeName(ivyCase.getTypeName());

    // ch.ivyteam.ivy.application.IApplication a = c.getApplication();
    // String name = a != null ? a.getName() : "";
    // result.setApplicationName(c.getApplicationName());
    RemoteApplication ra = new RemoteApplication();
    ra.setName(ivyCase.getApplicationName());
    result.setApplication(ra);

    result.setProcessModelName(ivyCase.getProcessModelName());
    result.setProcessModelVersionNumber(ivyCase.getProcessModelVersionNumber());
    result.setRemoteNotes(RemoteNoteMapper.mapNoteArray(ivyCase.getIvyNotes()));
    result.setCanDestroy(ivyCase.getCanDestroy());
    result.setCanChangeDescription(ivyCase.getCanChangeDescription());
    result.setCanChangeName(ivyCase.getCanChangeName());
    result.setBusinessCase(ivyCase.getIsBusinessCase());
    result.setServerUrl(ivyCase.getServerUrl());
    
    return result;

  }

  /**
   * Convert web service task to remote technical case 
   * 
   * @param ivyTask web service task
   * @return remote task
   * @see IvyTask
   * @see RemoteCase
   */
  public static RemoteCase mapRemoteTechnicalCaseFromTask(IvyTask ivyTask) {
    RemoteCase result = new RemoteCase();

    /* 1. GETTER + SETTER must be implemented in RemoteTask (overrides) */

    result.setBusinessCorrespondentId(ivyTask.getTechnicalCaseBusinessCorrespondentId());
    result.setBusinessCreatorUser(ivyTask.getTechnicalCaseBusinessCreatorUser());
    result.setBusinessMainContactDocumentDatabaseCode(ivyTask.getTechnicalCaseBusinessMainContactDocumentDatabaseCode());
    result.setBusinessMainContactFolderId(ivyTask.getTechnicalCaseBusinessMainContactFolderId());
    result.setBusinessMainContactId(ivyTask.getTechnicalCaseBusinessMainContactId());
    result.setBusinessMainContactName(ivyTask.getTechnicalCaseBusinessMainContactName());
    result.setBusinessMainContactType(ivyTask.getTechnicalCaseBusinessMainContactType());
    result.setBusinessMilestoneTimestamp(ivyTask.getTechnicalCaseBusinessMilestoneTimestamp() == null ? null : ivyTask
        .getTechnicalCaseBusinessMilestoneTimestamp().getTime());
    result.setBusinessObjectCode(ivyTask.getTechnicalCaseBusinessObjectCode());
    result.setBusinessObjectDocumentDatabaseCode(ivyTask.getTechnicalCaseBusinessObjectDocumentDatabaseCode());
    result.setBusinessObjectFolderId(ivyTask.getTechnicalCaseBusinessObjectFolderId());
    result.setBusinessObjectName(ivyTask.getTechnicalCaseBusinessObjectName());
    result.setBusinessPriority(ivyTask.getTechnicalCaseBusinessPriority());
    result.setBusinessStartTimestamp(ivyTask.getTechnicalCaseBusinessStartTimestamp() == null ? null : ivyTask
        .getTechnicalCaseBusinessStartTimestamp().getTime());


    result.setCustomDecimalField1(ivyTask.getTechnicalCaseCustomDecimalField1());
    result.setCustomDecimalField2(ivyTask.getTechnicalCaseCustomDecimalField2());
    result.setCustomDecimalField3(ivyTask.getTechnicalCaseCustomDecimalField3());
    result.setCustomDecimalField4(ivyTask.getTechnicalCaseCustomDecimalField4());
    result.setCustomDecimalField5(ivyTask.getTechnicalCaseCustomDecimalField5());

    result.setCustomTimestampField1(ivyTask.getTechnicalCaseCustomTimestampField1() == null ? null : ivyTask
        .getTechnicalCaseCustomTimestampField1().getTime());
    result.setCustomTimestampField2(ivyTask.getTechnicalCaseCustomTimestampField2() == null ? null : ivyTask
        .getTechnicalCaseCustomTimestampField2().getTime());
    result.setCustomTimestampField3(ivyTask.getTechnicalCaseCustomTimestampField3() == null ? null : ivyTask
        .getTechnicalCaseCustomTimestampField3().getTime());
    result.setCustomTimestampField4(ivyTask.getTechnicalCaseCustomTimestampField4() == null ? null : ivyTask
        .getTechnicalCaseCustomTimestampField4().getTime());
    result.setCustomTimestampField5(ivyTask.getTechnicalCaseCustomTimestampField5() == null ? null : ivyTask
        .getTechnicalCaseCustomTimestampField5().getTime());

    result.setCustomVarCharField1(ivyTask.getTechnicalCaseCustomVarCharField1());
    result.setCustomVarCharField2(ivyTask.getTechnicalCaseCustomVarCharField2());
    result.setCustomVarCharField3(ivyTask.getTechnicalCaseCustomVarCharField3());
    result.setCustomVarCharField4(ivyTask.getTechnicalCaseCustomVarCharField4());
    result.setCustomVarCharField5(ivyTask.getTechnicalCaseCustomVarCharField5());

    result.setDescription(ivyTask.getTechnicalCaseDescription());
    result.setName(ivyTask.getTechnicalCaseName());


    try {
      result.setPriority(WorkflowPriority.valueOf(ivyTask.getTechnicalCasePriority()));
    } catch (Exception e) {
      Ivy.log().error(e);
    }

    /*
     * 2. - setters must be implemented as NEW METHODS in RemoteCase
     */
    /* - getters must be implemented (override) */


    result.setCreatorUserName(ivyTask.getTechnicalCaseCreatorUserName());

    result.setEndTimestamp(ivyTask.getTechnicalCaseEndTimestamp() == null ? null : ivyTask.getTechnicalCaseEndTimestamp().getTime());
    result.setId(ivyTask.getTechnicalCaseId());

    result.setProcessCategoryCode(ivyTask.getTechnicalCaseProcessCategoryCode());
    result.setProcessCategoryName(ivyTask.getTechnicalCaseProcessCategoryName());
    result.setProcessCode(ivyTask.getTechnicalCaseProcessCode());
    result.setProcessName(ivyTask.getTechnicalCaseProcessName());

    result
        .setStartTimestamp(ivyTask.getTechnicalCaseStartTimestamp() == null ? null : ivyTask.getTechnicalCaseStartTimestamp().getTime());
    result.setState(CaseState.valueOf(ivyTask.getCaseState()));
    result.setSubTypeCode(ivyTask.getTechnicalCaseSubTypeCode());
    result.setSubTypeName(ivyTask.getTechnicalCaseSubTypeName());


    result.setTypeCode(ivyTask.getTechnicalCaseTypeCode());
    result.setTypeName(ivyTask.getTechnicalCaseTypeName());

    return result;
  }
  
  
  /**
   * Convert web service task to remote business case.
   * 
   * @param ivyTask web service task
   * @return remote task
   * @see IvyTask
   * @see RemoteCase
   */
  public static RemoteCase mapRemoteBusinessCaseFromTask(IvyTask ivyTask) {
    RemoteCase result = new RemoteCase();

    /* 1. GETTER + SETTER must be implemented in RemoteTask (overrides) */

    result.setBusinessCorrespondentId(ivyTask.getCaseBusinessCorrespondentId());
    result.setBusinessCreatorUser(ivyTask.getCaseBusinessCreatorUser());
    result.setBusinessMainContactDocumentDatabaseCode(ivyTask.getCaseBusinessMainContactDocumentDatabaseCode());
    result.setBusinessMainContactFolderId(ivyTask.getCaseBusinessMainContactFolderId());
    result.setBusinessMainContactId(ivyTask.getCaseBusinessMainContactId());
    result.setBusinessMainContactName(ivyTask.getCaseBusinessMainContactName());
    result.setBusinessMainContactType(ivyTask.getCaseBusinessMainContactType());
    result.setBusinessMilestoneTimestamp(ivyTask.getCaseBusinessMilestoneTimestamp() == null ? null : ivyTask
        .getCaseBusinessMilestoneTimestamp().getTime());
    result.setBusinessObjectCode(ivyTask.getCaseBusinessObjectCode());
    result.setBusinessObjectDocumentDatabaseCode(ivyTask.getCaseBusinessObjectDocumentDatabaseCode());
    result.setBusinessObjectFolderId(ivyTask.getCaseBusinessObjectFolderId());
    result.setBusinessObjectName(ivyTask.getCaseBusinessObjectName());
    result.setBusinessPriority(ivyTask.getCaseBusinessPriority());
    result.setBusinessStartTimestamp(ivyTask.getCaseBusinessStartTimestamp() == null ? null : ivyTask
        .getCaseBusinessStartTimestamp().getTime());


    result.setCustomDecimalField1(ivyTask.getCaseCustomDecimalField1());
    result.setCustomDecimalField2(ivyTask.getCaseCustomDecimalField2());
    result.setCustomDecimalField3(ivyTask.getCaseCustomDecimalField3());
    result.setCustomDecimalField4(ivyTask.getCaseCustomDecimalField4());
    result.setCustomDecimalField5(ivyTask.getCaseCustomDecimalField5());

    result.setCustomTimestampField1(ivyTask.getCaseCustomTimestampField1() == null ? null : ivyTask
        .getCaseCustomTimestampField1().getTime());
    result.setCustomTimestampField2(ivyTask.getCaseCustomTimestampField2() == null ? null : ivyTask
        .getCaseCustomTimestampField2().getTime());
    result.setCustomTimestampField3(ivyTask.getCaseCustomTimestampField3() == null ? null : ivyTask
        .getCaseCustomTimestampField3().getTime());
    result.setCustomTimestampField4(ivyTask.getCaseCustomTimestampField4() == null ? null : ivyTask
        .getCaseCustomTimestampField4().getTime());
    result.setCustomTimestampField5(ivyTask.getCaseCustomTimestampField5() == null ? null : ivyTask
        .getCaseCustomTimestampField5().getTime());

    result.setCustomVarCharField1(ivyTask.getCaseCustomVarCharField1());
    result.setCustomVarCharField2(ivyTask.getCaseCustomVarCharField2());
    result.setCustomVarCharField3(ivyTask.getCaseCustomVarCharField3());
    result.setCustomVarCharField4(ivyTask.getCaseCustomVarCharField4());
    result.setCustomVarCharField5(ivyTask.getCaseCustomVarCharField5());

    result.setDescription(ivyTask.getCaseDescription());
    result.setName(ivyTask.getCaseName());


    try {
      result.setPriority(WorkflowPriority.valueOf(ivyTask.getCasePriority()));
    } catch (Exception e) {

    }

    /*
     * 2. - setters must be implemented as NEW METHODS in RemoteCase
     */
    /* - getters must be implemented (override) */


    result.setCreatorUserName(ivyTask.getCaseCreatorUserName());

    result.setEndTimestamp(ivyTask.getCaseEndTimestamp() == null ? null : ivyTask.getCaseEndTimestamp().getTime());
    result.setId(ivyTask.getCaseId());

    result.setProcessCategoryCode(ivyTask.getCaseProcessCategoryCode());
    result.setProcessCategoryName(ivyTask.getCaseProcessCategoryName());
    result.setProcessCode(ivyTask.getCaseProcessCode());
    result.setProcessName(ivyTask.getCaseProcessName());

    result
        .setStartTimestamp(ivyTask.getCaseStartTimestamp() == null ? null : ivyTask.getCaseStartTimestamp().getTime());
    result.setState(CaseState.valueOf(ivyTask.getCaseState()));
    result.setSubTypeCode(ivyTask.getCaseSubTypeCode());
    result.setSubTypeName(ivyTask.getCaseSubTypeName());


    result.setTypeCode(ivyTask.getCaseTypeCode());
    result.setTypeName(ivyTask.getCaseTypeName());

    return result;
  }
  
  public static RemoteCase mapCaseFromTask(RemoteTask ivyTask) {
    RemoteCase remoteCase = new RemoteCase();

    /* 1. GETTER + SETTER must be implemented in RemoteTask (overrides) */

    RemoteCase ivyCase = ivyTask.getCase();
    remoteCase.setBusinessCorrespondentId(ivyCase.getBusinessCorrespondentId());
    remoteCase.setBusinessCreatorUser(ivyCase.getBusinessCreatorUser());
    remoteCase.setBusinessMainContactDocumentDatabaseCode(ivyCase.getBusinessMainContactDocumentDatabaseCode());
    remoteCase.setBusinessMainContactFolderId(ivyCase.getBusinessMainContactFolderId());
    remoteCase.setBusinessMainContactId(ivyCase.getBusinessMainContactId());
    remoteCase.setBusinessMainContactName(ivyCase.getBusinessMainContactName());
    remoteCase.setBusinessMainContactType(ivyCase.getBusinessMainContactType());
    remoteCase.setBusinessMilestoneTimestamp(ivyCase.getBusinessMilestoneTimestamp() == null ? null : ivyCase
        .getBusinessMilestoneTimestamp());
    remoteCase.setBusinessObjectCode(ivyCase.getBusinessObjectCode());
    remoteCase.setBusinessObjectDocumentDatabaseCode(ivyCase.getBusinessObjectDocumentDatabaseCode());
    remoteCase.setBusinessObjectFolderId(ivyCase.getBusinessObjectFolderId());
    remoteCase.setBusinessObjectName(ivyCase.getBusinessObjectName());
    remoteCase.setBusinessPriority(ivyCase.getBusinessPriority());
    remoteCase.setBusinessStartTimestamp(ivyCase.getBusinessStartTimestamp() == null ? null : ivyCase
        .getBusinessStartTimestamp());


    remoteCase.setCustomDecimalField1(ivyCase.getCustomDecimalField1());
    remoteCase.setCustomDecimalField2(ivyCase.getCustomDecimalField2());
    remoteCase.setCustomDecimalField3(ivyCase.getCustomDecimalField3());
    remoteCase.setCustomDecimalField4(ivyCase.getCustomDecimalField4());
    remoteCase.setCustomDecimalField5(ivyCase.getCustomDecimalField5());

    remoteCase.setCustomTimestampField1(ivyCase.getCustomTimestampField1() == null ? null : ivyCase
        .getCustomTimestampField1());
    remoteCase.setCustomTimestampField2(ivyCase.getCustomTimestampField2() == null ? null : ivyCase
        .getCustomTimestampField2());
    remoteCase.setCustomTimestampField3(ivyCase.getCustomTimestampField3() == null ? null : ivyCase
        .getCustomTimestampField3());
    remoteCase.setCustomTimestampField4(ivyCase.getCustomTimestampField4() == null ? null : ivyCase
        .getCustomTimestampField4());
    remoteCase.setCustomTimestampField5(ivyCase.getCustomTimestampField5() == null ? null : ivyCase
        .getCustomTimestampField5());

    remoteCase.setCustomVarCharField1(ivyCase.getCustomVarCharField1());
    remoteCase.setCustomVarCharField2(ivyCase.getCustomVarCharField2());
    remoteCase.setCustomVarCharField3(ivyCase.getCustomVarCharField3());
    remoteCase.setCustomVarCharField4(ivyCase.getCustomVarCharField4());
    remoteCase.setCustomVarCharField5(ivyCase.getCustomVarCharField5());

    remoteCase.setDescription(ivyCase.getDescription());
    remoteCase.setName(ivyCase.getName());
    remoteCase.setBusinessCase(ivyCase.isBusinessCase());


    try {
      remoteCase.setPriority(ivyCase.getPriority());
    } catch (Exception e) {
      Ivy.log().error(e);
    }

    /*
     * 2. - setters must be implemented as NEW METHODS in RemoteCase
     */
    /* - getters must be implemented (override) */


    remoteCase.setCreatorUserName(ivyCase.getCreatorUserName());

    remoteCase.setEndTimestamp(ivyCase.getEndTimestamp() == null ? null : ivyCase.getEndTimestamp());
    remoteCase.setId(ivyCase.getId());

    remoteCase.setProcessCategoryCode(ivyCase.getProcessCategoryCode());
    remoteCase.setProcessCategoryName(ivyCase.getProcessCategoryName());
    remoteCase.setProcessCode(ivyCase.getProcessCode());
    remoteCase.setProcessName(ivyCase.getProcessName());

    remoteCase.setStartTimestamp(ivyCase.getStartTimestamp() == null ? null : ivyCase.getStartTimestamp());
    remoteCase.setState(ivyCase.getState());
    remoteCase.setSubTypeCode(ivyCase.getSubTypeCode());
    remoteCase.setSubTypeName(ivyCase.getSubTypeName());


    remoteCase.setTypeCode(ivyCase.getTypeCode());
    remoteCase.setTypeName(ivyCase.getTypeName());

    return remoteCase;
  }

  /**
   * Map list of IvyCase to list of RemoteCase.
   * 
   * @param ivyCases list of {@link IvyCase}
   * @param server 
   * @return list of {@link RemoteCase}
   * @see IvyCase
   * @see RemoteCase
   */
  public static List<RemoteCase> mapCases(List<IvyCase> ivyCases, Server server) {
    List<RemoteCase> remoteCases = List.create(RemoteCase.class);

    for (IvyCase ivyCase : ivyCases) {
      RemoteCase remoteCase = mapCase(ivyCase);
      remoteCase.setServer(server);
      remoteCases.add(remoteCase);
    }

    return remoteCases;
  }

  /**
   * Map IvyCase to RemoteCase.
   * 
   * @param ivyCase {@link IvyCase}
   * @param server 
   * @return {@link RemoteCase}
   * @see IvyCase
   * @see RemoteCase
   */
  public static RemoteCase mapCase(IvyCase ivyCase, Server server) {
    RemoteCase remoteCase = mapCase(ivyCase);
    remoteCase.setServer(server);
    return remoteCase;
  }

}
