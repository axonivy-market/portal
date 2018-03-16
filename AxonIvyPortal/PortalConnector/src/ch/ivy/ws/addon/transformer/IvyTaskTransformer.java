package ch.ivy.ws.addon.transformer;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.types.IvyCase;
import ch.ivy.ws.addon.types.IvyTask;
import ch.ivy.ws.addon.util.ServerUrlUtils;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Transform a ITask object to an IvyTask object
 */
@SuppressWarnings("deprecation")
public class IvyTaskTransformer {

  private boolean isUrlBuiltFromSystemProperties;

  public IvyTaskTransformer(boolean isUrlBuiltFromSystemProperties) {
    this.isUrlBuiltFromSystemProperties = isUrlBuiltFromSystemProperties;
  }

  public IvyTask transform(ITask t) {
    return transform(t, true);
  }

  public IvyTask transform(ITask t, boolean addCaseToTask) {
    IvyTask result = new IvyTask();

    if (t.getActivator() != null) {
      String activatorName = t.getActivatorName().replaceFirst("#", "");
      result.setActivatorName(activatorName);
      ISecurityMember activator = t.getActivator();
      if (activator != null) {
        String fullName =
            !activator.getDisplayName().isEmpty() ? activator.getDisplayName() : activator.getMemberName();
        if (activator.isUser()) {
          IUser user = (IUser) activator;
          fullName = user.getFullName();
        }
        result.setActivatorFullName(fullName);
      }
    }

    result.setBusinessCreatorUser(t.getBusinessCreatorUser());

    if (t.getCustomDecimalField1() != null) {
      result.setCustomDecimalField1(t.getCustomDecimalField1().floatValue());
    }
    if (t.getCustomDecimalField2() != null) {
      result.setCustomDecimalField2(t.getCustomDecimalField2().floatValue());
    }
    if (t.getCustomDecimalField3() != null) {
      result.setCustomDecimalField3(t.getCustomDecimalField3().floatValue());
    }
    if (t.getCustomDecimalField4() != null) {
      result.setCustomDecimalField4(t.getCustomDecimalField4().floatValue());
    }
    if (t.getCustomDecimalField5() != null) {
      result.setCustomDecimalField5(t.getCustomDecimalField5().floatValue());
    }
    result.setCustomTimestampField1(t.getCustomTimestampField1());
    result.setCustomTimestampField2(t.getCustomTimestampField2());
    result.setCustomTimestampField3(t.getCustomTimestampField3());
    result.setCustomTimestampField4(t.getCustomTimestampField4());
    result.setCustomTimestampField5(t.getCustomTimestampField5());

    result.setCustomVarCharField1(t.getCustomVarCharField1());
    result.setCustomVarCharField2(t.getCustomVarCharField2());
    result.setCustomVarCharField3(t.getCustomVarCharField3());
    result.setCustomVarCharField4(t.getCustomVarCharField4());
    result.setCustomVarCharField5(t.getCustomVarCharField5());

    result.setDelayTimestamp(t.getDelayTimestamp());
    result.setDescription(t.getDescription());
    result.setDisplayDescriptionTemplate(t.getDisplayDescriptionTemplate());
    result.setDisplayNameTemplate(t.getDisplayNameTemplate());
    if (t.getExpiryActivatorName() != null) {
      String expiryActivatorName = t.getExpiryActivatorName().replaceFirst("#", "");
      result.setExpireActivatorName(expiryActivatorName);
      ISecurityMember expiryActivator = t.getExpiryActivator();
      if (expiryActivator != null) {
        String fullName =
            !expiryActivator.getDisplayName().isEmpty() ? expiryActivator.getDisplayName() : expiryActivator
                .getMemberName();
        if (expiryActivator.isUser()) {
          IUser user = (IUser) expiryActivator;
          fullName = user.getFullName();
        }
        result.setExpireActivatorFullName(fullName);
      }
    }
    if (t.getExpiryPriority() != null) {
      result.setExpirePriority(t.getExpiryPriority().name());
    }
    result.setId(t.getId());
    result.setIsExpired(t.isExpired());
    result.setName(t.getName());

    if (t.getPriority() != null) {
      result.setPriority(t.getPriority().name());
    }

    result.setFullRequestPath(getStartLink(t));
    result.setKindCode(t.getKindCode());
    result.setKindName(t.getKindName());
    if (t.getOriginalActivatorName() != null) {
      String originalActivatorName = t.getOriginalActivatorName().replaceFirst("#", "");
      result.setOriginalActivatorName(originalActivatorName);
      ISecurityMember originalActivator = t.getOriginalActivator();
      if (originalActivator != null) {
        String fullName =
            !originalActivator.getDisplayName().isEmpty() ? originalActivator.getDisplayName() : originalActivator
                .getMemberName();
        if (originalActivator.isUser()) {
          IUser user = (IUser) originalActivator;
          fullName = user.getFullName();
        }
        result.setOriginalActivatorFullName(fullName);
      }
    }
    if (t.getOriginalPriority() != null) {
      result.setOriginalPriority(t.getOriginalPriority().name());

    }
    result.setStartTimestamp(t.getStartTimestamp());
    result.setExpireTimestamp(t.getExpiryTimestamp());
    result.setExpireTaskStartElementPid(t.getExpiryTaskStartElementPid());
    result.setEndTimestamp(t.getEndTimestamp());
    if (t.getState() != null) {
      result.setState(t.getState().name());
    }
    if (t.getWorkerUserName() != null) {
      result.setWorkerUserName(t.getWorkerUserName().replaceFirst("#", ""));
      IUser worker = t.getWorkerUser();
      if (worker != null) {
        result.setWorkerFullName(worker.getFullName());
      }
    }

    result.setApplicationName(t.getCase().getApplication().getName());
    IvyNoteTransformer ivyNoteTransformer = new IvyNoteTransformer();
    result.setIvyNotes(ivyNoteTransformer.transform(t.getNotes()));

    // Add case object
    if (addCaseToTask) {
      IvyCaseTransformer caseTransformer = new IvyCaseTransformer();
      IvyCase c = caseTransformer.transform(t.getCase().getBusinessCase());
      result.setCaseBusinessCorrespondentId(c.getBusinessCorrespondentId());
      result.setCaseBusinessCreatorUser(c.getBusinessCreatorUser());
      result.setCaseBusinessMainContactDocumentDatabaseCode(c.getBusinessMainContactDocumentDatabaseCode());
      result.setCaseBusinessMainContactFolderId(c.getBusinessMainContactFolderId());
      result.setCaseBusinessMainContactId(c.getBusinessMainContactId());
      result.setCaseBusinessMainContactName(c.getBusinessMainContactName());
      result.setCaseBusinessMainContactType(c.getBusinessMainContactType());
      result.setCaseBusinessMilestoneTimestamp(c.getBusinessMilestoneTimestamp());
      result.setCaseBusinessObjectCode(c.getBusinessObjectCode());
      result.setCaseBusinessObjectDocumentDatabaseCode(c.getBusinessObjectDocumentDatabaseCode());
      result.setCaseBusinessObjectFolderId(c.getBusinessObjectFolderId());
      result.setCaseBusinessObjectName(c.getBusinessObjectName());
      result.setCaseBusinessPriority(c.getBusinessPriority());
      result.setCaseBusinessStartTimestamp(c.getBusinessStartTimestamp());
      result.setCaseCreatorUserName(c.getCreatorUserName());

      result.setCaseCustomDecimalField1(c.getCustomDecimalField1());
      result.setCaseCustomDecimalField2(c.getCustomDecimalField2());
      result.setCaseCustomDecimalField3(c.getCustomDecimalField3());
      result.setCaseCustomDecimalField4(c.getCustomDecimalField4());
      result.setCaseCustomDecimalField5(c.getCustomDecimalField5());

      result.setCaseCustomTimestampField1(c.getCustomTimestampField1());
      result.setCaseCustomTimestampField2(c.getCustomTimestampField2());
      result.setCaseCustomTimestampField3(c.getCustomTimestampField3());
      result.setCaseCustomTimestampField4(c.getCustomTimestampField4());
      result.setCaseCustomTimestampField5(c.getCustomTimestampField5());

      result.setCaseCustomVarCharField1(c.getCustomVarCharField1());
      result.setCaseCustomVarCharField2(c.getCustomVarCharField2());
      result.setCaseCustomVarCharField3(c.getCustomVarCharField3());
      result.setCaseCustomVarCharField4(c.getCustomVarCharField4());
      result.setCaseCustomVarCharField5(c.getCustomVarCharField5());

      result.setCaseDescription(c.getDescription());
      result.setCaseEndTimestamp(c.getEndTimestamp());
      result.setCaseId(c.getId());
      result.setCaseName(c.getName());
      result.setCasePriority(c.getPriority());

      result.setCaseProcessCategoryCode(c.getProcessCategoryCode());
      result.setCaseProcessCategoryName(c.getProcessCategoryName());
      result.setCaseProcessCode(c.getProcessCode());
      result.setCaseProcessName(c.getProcessName());

      result.setCaseStartTimestamp(c.getStartTimestamp());
      result.setCaseState(c.getState());
      result.setCaseSubTypeCode(c.getSubTypeCode());
      result.setCaseSubTypeName(c.getSubTypeName());

      result.setCaseTypeCode(c.getTypeCode());
      result.setCaseTypeName(c.getTypeName());
    }
    
    // Add technical case object
    if (addCaseToTask) {
      IvyCaseTransformer caseTransformer = new IvyCaseTransformer();
      IvyCase c = caseTransformer.transform(t.getCase());
      result.setTechnicalCaseBusinessCorrespondentId(c.getBusinessCorrespondentId());
      result.setTechnicalCaseBusinessCreatorUser(c.getBusinessCreatorUser());
      result.setTechnicalCaseBusinessMainContactDocumentDatabaseCode(c.getBusinessMainContactDocumentDatabaseCode());
      result.setTechnicalCaseBusinessMainContactFolderId(c.getBusinessMainContactFolderId());
      result.setTechnicalCaseBusinessMainContactId(c.getBusinessMainContactId());
      result.setTechnicalCaseBusinessMainContactName(c.getBusinessMainContactName());
      result.setTechnicalCaseBusinessMainContactType(c.getBusinessMainContactType());
      result.setTechnicalCaseBusinessMilestoneTimestamp(c.getBusinessMilestoneTimestamp());
      result.setTechnicalCaseBusinessObjectCode(c.getBusinessObjectCode());
      result.setTechnicalCaseBusinessObjectDocumentDatabaseCode(c.getBusinessObjectDocumentDatabaseCode());
      result.setTechnicalCaseBusinessObjectFolderId(c.getBusinessObjectFolderId());
      result.setTechnicalCaseBusinessObjectName(c.getBusinessObjectName());
      result.setTechnicalCaseBusinessPriority(c.getBusinessPriority());
      result.setTechnicalCaseBusinessStartTimestamp(c.getBusinessStartTimestamp());
      result.setTechnicalCaseCreatorUserName(c.getCreatorUserName());

      result.setTechnicalCaseCustomDecimalField1(c.getCustomDecimalField1());
      result.setTechnicalCaseCustomDecimalField2(c.getCustomDecimalField2());
      result.setTechnicalCaseCustomDecimalField3(c.getCustomDecimalField3());
      result.setTechnicalCaseCustomDecimalField4(c.getCustomDecimalField4());
      result.setTechnicalCaseCustomDecimalField5(c.getCustomDecimalField5());

      result.setTechnicalCaseCustomTimestampField1(c.getCustomTimestampField1());
      result.setTechnicalCaseCustomTimestampField2(c.getCustomTimestampField2());
      result.setTechnicalCaseCustomTimestampField3(c.getCustomTimestampField3());
      result.setTechnicalCaseCustomTimestampField4(c.getCustomTimestampField4());
      result.setTechnicalCaseCustomTimestampField5(c.getCustomTimestampField5());

      result.setTechnicalCaseCustomVarCharField1(c.getCustomVarCharField1());
      result.setTechnicalCaseCustomVarCharField2(c.getCustomVarCharField2());
      result.setTechnicalCaseCustomVarCharField3(c.getCustomVarCharField3());
      result.setTechnicalCaseCustomVarCharField4(c.getCustomVarCharField4());
      result.setTechnicalCaseCustomVarCharField5(c.getCustomVarCharField5());

      result.setTechnicalCaseDescription(c.getDescription());
      result.setTechnicalCaseEndTimestamp(c.getEndTimestamp());
      result.setTechnicalCaseId(c.getId());
      result.setTechnicalCaseName(c.getName());
      result.setTechnicalCasePriority(c.getPriority());

      result.setTechnicalCaseProcessCategoryCode(c.getProcessCategoryCode());
      result.setTechnicalCaseProcessCategoryName(c.getProcessCategoryName());
      result.setTechnicalCaseProcessCode(c.getProcessCode());
      result.setTechnicalCaseProcessName(c.getProcessName());

      result.setTechnicalCaseStartTimestamp(c.getStartTimestamp());
      result.setTechnicalCaseState(c.getState());
      result.setTechnicalCaseSubTypeCode(c.getSubTypeCode());
      result.setTechnicalCaseSubTypeName(c.getSubTypeName());

      result.setTechnicalCaseTypeCode(c.getTypeCode());
      result.setTechnicalCaseTypeName(c.getTypeName());
    }
    
    return result;
  }

  public List<IvyTask> transform(List<ITask> tasks, boolean addCaseToTask) {
    List<IvyTask> result = new ArrayList<>();

    if (tasks != null) {
      for (ITask t : tasks) {
        result.add(transform(t, addCaseToTask));
      }
    }
    return result;

  }

  public List<IvyTask> transform(List<ITask> tasks) {
    return transform(tasks, true);
  }

  private String getStartLink(ITask task) {
    IApplicationConfigurationManager appConfig = ServerFactory.getServer().getApplicationConfigurationManager();
    URI taskUri = RequestUriFactory.createTaskStartUri(appConfig, task);
    return ServerUrlUtils.getStartLink(taskUri.toString(), isUrlBuiltFromSystemProperties);
  }
}
